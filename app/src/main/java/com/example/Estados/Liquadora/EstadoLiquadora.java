package com.example.Estados.Liquadora;

import java.io.IOException;
import java.util.logging.Level;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

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
public class EstadoLiquadora extends Estado implements Iliquadora {
    private int velocidadActual = 0;
    private final LiquadoraData data;
    private final Map<Integer, String> speedMap;
    private List<Double> volumeList;
    private final Scanner scanner;
    private final double maxCapacity = 100.0;
    private static final Logger logger = Logger.getLogger(LiquadoraData.class.getName());
    
        public EstadoLiquadora() {
            this.data = new LiquadoraData();
            this.speedMap = data.getSpeedMap();
            this.volumeList = data.getVolumeList();
            this.scanner = new Scanner(System.in);
        }
    
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
            menu.append("Velocidad Actual: " + speedMap.get(velocidadActual) + "\n");
            menu.append("Volumen Actual: " + volumeList.stream().mapToDouble(Double::doubleValue).sum() + "/" + maxCapacity + " ml \n");
            return menu.toString();
        } 
    
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
                        logger.log(Level.WARNING, "Error al llenar la liquiadora: " + e.getMessage());
                        scanner.nextLine();
                    }
                    return this;
            case 2:
                if (volumeList.stream().mapToDouble(Double::doubleValue).sum() > 0) {
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
                if (volumeList.stream().mapToDouble(Double::doubleValue).sum() == 0) {
                    System.out.println("No se puede servir ya que no hay liquido en la liquiadora");
                    return this;
                }
                System.out.println("Cuanto volumen quiere servir? ");
                try {
                    double inputQuantity = scanner.nextDouble();
                    scanner.nextLine();
                    servir(inputQuantity);
                } catch (Exception e) {
                    logger.log(Level.WARNING, "Error al servir la liquiadora: " + e.getMessage());
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

    
    @Override
    public double llenar(double volumeToFill) {
        double currentVolume = volumeList.stream().mapToDouble(Double::doubleValue).sum();
        
        double availableCapacity = maxCapacity - currentVolume;

        if (volumeToFill > availableCapacity) {
            System.out.println("No hay suficiente espacio disponible en la liquiadora.");
            return currentVolume;
        } else {
            volumeList.add(volumeToFill);
            double addedVolume = volumeToFill + currentVolume;
            System.out.println("Se lleno a la liquadora un total de " + addedVolume + " ml.");
            return addedVolume;
        }
    }    
    

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

    @Override
    public double vaciar() {
        double totalVolume = volumeList.stream().mapToDouble(Double::doubleValue).sum();
        try {
            data.deleteVolume(totalVolume);
            volumeList.clear();
            apagar();
            return 0.0;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error vaciando la liquiadora: " + e.getMessage());
            return -1.0; 
    }
    }

    @Override
    public double servir(double volumenRestado) {
        double totalVolume = volumeList.stream().mapToDouble(Double::doubleValue).sum();
        if (totalVolume >= volumenRestado) {
            double newTotalVolume = totalVolume - volumenRestado;
            if (newTotalVolume > 0) {
                try {
                    data.setVolumeList(newTotalVolume);
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Error sirviendo la liquiadora: " + e.getMessage());
                }
            } else {
                try {
                    data.deleteVolume(totalVolume);
                    volumeList.clear();
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Error sirviendo la liquiadora: " + e.getMessage());
                }
            }
            logger.log(Level.INFO, "Se sirvio " + volumenRestado + " ml de liquido de la liquiadora.");
            apagar();
            return volumenRestado;
        } else {
            logger.log(Level.SEVERE, "No hay suficiente liquido en la liquiadora. No se puede servir.");
            return 0.0;
        }
    }
}
