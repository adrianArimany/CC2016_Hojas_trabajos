package com.example.Estados.ELiquadora;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.Data.AppConfig;
import com.example.Data.LiquadoraData;
import com.example.Estados.Estado;

/**
 * Esta clase esta hecha para Manejar la liquiadora y sus interfazes.
 * @velocidadActual indica la velocidad actual de la liquiadora, esta definida en 0 que indica en el archivo json liquadoraSpeed.json como "off". 
 * @speedMap son las diferentes velocidades que se encuentra en el archivo json liquadoraSpeed.json
 * @volumeList son los diferentes volumenes que el usuario va a estar agregando a la liquiadora
 * @maxCapacity es la capacidad maxima de la liquiadora
 * 
 */
public class EstadoLiquadora extends Estado implements Liquadora {
    private int velocidadActual;
    private final LiquadoraData data;
    private final Map<Integer, String> speedMap;
    private final Scanner scanner;
    private final double maxCapacity;
    private static final Logger logger = Logger.getLogger(LiquadoraData.class.getName());
    
        public EstadoLiquadora() {
            AppConfig prop = new AppConfig();;
            this.velocidadActual = prop.getVelocidadActual(); 
            this.maxCapacity = prop.getMaxCapacity();
            this.data = new LiquadoraData();
            this.speedMap = data.getSpeedMap();
            data.getVolume();
            this.scanner = new Scanner(System.in);
        }
    
        /**
         * Muestra el menu de la liquiadora con las diferentes opciones.
         * 
         * @return Un string con el menu de la liquiadora.
         */
        @Override
        public String showMenu() {
            StringBuilder menu = new StringBuilder();
            menu.append("============ Systema de la LIQUIDADORA ============== \n");
            menu.append("| 1. Agregar a la liquadora                           |\n");
            menu.append("| 2. Aumentar Velocidad                               |\n");
            menu.append("| 3. Disminuir Velocidad                              |\n");
            menu.append("| 4. Vaciar liquiadora                                |\n");
            menu.append("| 5. Servir liquiadora                                |\n");
            menu.append("| -1. Apagar                                          |\n");
            menu.append("======================================================\n");
            menu.append("Velocidad Actual: "); 
            menu.append(speedMap.get(velocidadActual));
            menu.append("\n");
            menu.append("Volumen Actual: "); 
            menu.append(data.getVolume()); 
            menu.append("/");
            menu.append(maxCapacity); 
            menu.append(" ml \n");
            return menu.toString();
        } 
    
        /**
         * Cambia el estado de la liquiadora segun la accion que se le envia.
         * 
         * @param action La accion que se le envia a la liquiadora.
         * @return El estado que se debe mostrar despues de la accion.
         */
        @Override
        public Estado transition(int action) {
            switch (action) {
                case 1:
                    System.out.print("Ingrese el volumen: ");
                    try {
                        double inputQuantity = scanner.nextDouble();
                        scanner.nextLine();
                        llenar(inputQuantity);
                    } catch (Exception e) {
                        logger.log(Level.WARNING, "Solo se permiten numeros. Tipo de error: %s", e.getMessage());
                        scanner.nextLine();
                    }
                    return this;
            case 2:
                if (data.getVolume() > 0) {
                    incrementarVelocidad();
                } else {
                    System.out.println("No se puede aumentar la velocidad ya que no hay liquido en la liquiadora");
                }
                return this;
            case 3:
                decrementarVelocidad();
                return this;
            case 4:
                vaciar();
                return this;
            case 5:
                if (data.getVolume() == 0) {
                    System.out.println("No se puede servir ya que no hay liquido en la liquiadora");
                    return this;
                }
                System.out.println("Cuanto volumen quiere servir? ");
                try {
                    double inputQuantity = scanner.nextDouble();
                    scanner.nextLine();
                    servir(inputQuantity);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Error al servir la liquiadora: {0}", e.getMessage());
                    scanner.nextLine();
                }
                return this;
            default:
                System.out.println("Error: Accion invalida.");
                return this;
        }
    } 
    
    /**
     * Este metodo no se implementa en esta clase debido a que el sistema incorpora su propio sistema de encendido
     * lo cual es externo a esta clase.
     */
    @Override
    public void encender() {
        throw new UnsupportedOperationException("Este metodo no se implementa en esta clase");
    }

    /**
     * Note que este metodo se utiliza en MandejadorDeEstados para apagar la liquiadora de manera externa a esta clase cuando el usuario
     * presiona -1.
     * 
     * Ademas se utiliza en los metodos vaciar() y servir() para apagar la liquiadora de esa manera se puede evitar riesgos al usuario sirviendo la liquadora.
     */
    @Override
    public void apagar() {
        velocidadActual = 0;
    }

    /**
     * Este metodo no se implementa en esta clase debido a que el sistema incorpora su propio sistema de encendido si esta encendido
     * lo cual es externo a esta clase.
     */
    @Override
    public boolean estaEncendida() {
        throw new UnsupportedOperationException("Este metodo no se implementa en esta clase");
    }

    
    /**
     * Adds a volume of liquid to the blender.
     *
     * If the volume to add exceeds the available space in the blender,
     * an error message is displayed and the current volume is returned without adding anything.
     *
     * @param volumeToAdd the volume of liquid to add.
     * @return the total current volume of the blender.
     */
    @Override
    public double llenar(double volumeToAdd) {
        double currentVolume = data.getVolume();
        double availableSpace = maxCapacity - currentVolume;

        if (volumeToAdd > availableSpace) {
            logger.log(Level.WARNING, "No hay suficiente espacio en la liquadora.");
            return currentVolume;
        }

        double newTotalVolume = currentVolume + volumeToAdd;
        try {
            data.setVolume(newTotalVolume);
            logger.log(Level.INFO, String.format("Se agrego un total de %.2f ml A la liquiadora.", newTotalVolume));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error en agregar liquido a la liquiadora: {0}", e.getMessage());
        }
        return newTotalVolume;
    }
    

/**
 * Incrementa la velocidad actual de la liquiadora en una unidad.
 * 
 * Si la nueva velocidad está dentro del rango de velocidades definidas en el mapa de velocidades,
 * actualiza la velocidad actual y devuelve la nueva velocidad. 
 * Si no hay un valor definido para la nueva velocidad en el mapa, 
 * muestra un mensaje indicando que no se puede aumentar la velocidad y devuelve la velocidad actual sin cambios.
 * 
 * @return la nueva velocidad actual si se incrementa exitosamente, 
 *         de lo contrario, devuelve la velocidad actual sin cambios.
 */

    @Override
    public int incrementarVelocidad() {
        int newSpeed = velocidadActual + 1;
        if (speedMap.containsKey((int) newSpeed)) {
            velocidadActual = newSpeed;
            System.out.println("La velocidad actual es: "+ speedMap.get((int) velocidadActual));
            return (int) velocidadActual;
        } else {
            System.out.println("No se puede aumentar la velocidad.");
            return (int) velocidadActual;
        }
        
    }   

/**
 * Decreases the current speed of the liquadora by one unit.
 * 
 * If the new speed is within the range of speeds defined in the speed map,
 * updates the current speed and returns the new speed. 
 * If there is no defined value for the new speed in the map,
 * displays a message indicating that the speed cannot be decreased and returns the current speed unchanged.
 * 
 * @return the new current speed if successfully decreased,
 *         otherwise, returns the current speed unchanged.
 */

    @Override
    public int decrementarVelocidad() {
        int newSpeed = velocidadActual - 1;
        if (speedMap.containsKey((int) newSpeed)) {
            velocidadActual = newSpeed;
            System.out.println("La velocidad actual es: "+ speedMap.get((int) velocidadActual));
            return (int) velocidadActual;
        } else {
            System.out.println("No se puede disminuir la velocidad.");
            return (int) velocidadActual;
        }
    }   


    /**
     * Este metodo no se implementa en esta clase debido a que el sistema ya le deja saber al usuario en el menu principal.
     */
    @Override
    public int consultarVelocidad() {
        throw new UnsupportedOperationException("Este metodo no se implementa en esta clase");
    }
    /**
     * Este metodo no se implementa en esta clase debido a que el sistema ya le deja saber al usuario en el menu principal.
     */
    @Override
    public boolean estaLlena() {
        throw new UnsupportedOperationException("Este metodo no se implementa en esta clase");
    }

    /**
     * Vacua la liquiadora y la apaga.
     * 
     * @return 0.0 si se vacia correctamente, -1.0 si ocurre un error.
     */
    @Override
    public double vaciar() {
        try {
            data.deleteVolume();
            apagar();
            return 0.0;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error vaciando la liquiadora", e);
            return -1.0;
        }
    }

    /**
     * Sirve un volumen de liquido de la liquiadora y la apaga.
     * 
     * Si el volumen a servir es mayor que el espacio disponible en la liquiadora, 
     * se muestra un mensaje de error y se devuelve 0.0.
     * 
     * @param volumenRestado el volumen de liquido a servir.
     * @return el volumen total servido si se puede servir, 0.0 si no se puede servir.
     */
    @Override
    public double servir(double volumenRestado) {
        double totalVolume = data.getVolume();
        if (totalVolume >= volumenRestado) {
            double newTotalVolume = totalVolume - volumenRestado;
            if (newTotalVolume > 0) {
                try {
                    data.setVolume(newTotalVolume);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Error sirviendo la liquiadora: {0}", e.getMessage());
                }
            } else {
                try {
                    data.deleteVolume();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Error sirviendo la liquiadora: {0}", e.getMessage());
                }
            }
            logger.log(Level.INFO, String.format("Se sirvio %.2f ml de liquido de la liquiadora.", volumenRestado));
            apagar();
            return volumenRestado;
        } else {
            logger.log(Level.SEVERE, "No hay suficiente liquido en la liquiadora. No se puede servir.");
            return 0.0;
        }
    }
}
