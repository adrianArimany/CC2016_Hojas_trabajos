package com.example.Estados.Liquadora;

import java.io.IOException;
import java.util.logging.Level;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import com.example.Data.LiquadoraData;
import com.example.Estados.Estado;

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
            return 0.0;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error vaciando la liquiadora: " + e.getMessage());
            return -1.0; 
    }
    }

    @Override
    public double servir(double volumenRestado) {
        return 0.0f;
    }
}
