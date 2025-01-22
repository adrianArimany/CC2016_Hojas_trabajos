package com.example.Estados.Liquadora;

import java.util.Map;
import java.util.Scanner;

import com.example.Data.LiquadoraData;
import com.example.Estados.Estado;

public class EstadoLiquadora extends Estado implements Iliquadora {
    private int velocidadActual = 0;
    private final LiquadoraData data;
    private final Map<Integer, String> speedMap;
    private Map<Double, Object> volumeMap;
    private final Scanner scanner;
    
        public EstadoLiquadora() {
            this.data = new LiquadoraData();
            this.speedMap = data.getSpeedMap();
            volumeMap = data.getVolumeMap();
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
            menu.append("| -1. Apagar                                          |\n");
            menu.append("======================================================\n");
            menu.append("Velocidad Actual: " + speedMap.get(velocidadActual) + "\n");
            menu.append("Volumen Actual: " + volumeMap.keySet().stream().mapToDouble(Double::doubleValue).sum() + "\n");
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
                        System.out.println("Entrada inválida. Por favor intente de nuevo.");
                        scanner.nextLine();
                    }
                    return this;
            case 2:
                incrementarVelocidad();
                return this;
            case 3:
                decrementarVelocidad();
                return this;
            case 4:
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
     * Este metodo no se implementa en esta clase debido a que el sistema incorpora su propio sistema de apagado
     * lo cual es externo a esta clase.
     */
    @Override
    public void apagar() {
        throw new UnsupportedOperationException("Este metodo no se implementa en esta clase");
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
    public double llenar(double volumen) {
        double currentVolume = volumeMap.keySet().stream().mapToDouble(Double::doubleValue).sum();
        double maxCapacity = 100.0; // Assuming max capacity is 100.0
        double availableCapacity = maxCapacity - currentVolume;
        
        if (volumen > availableCapacity) {
            System.out.println("No hay suficiente capacidad para agregar el volumen solicitado.");
            return currentVolume;
        } else {
            volumeMap.put(volumen, new Object()); // Assuming new Object() represents the material added
            System.out.println("Volumen Actual: " + currentVolume);
            return currentVolume + volumen;
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

    @Override
    public boolean estaLlena() {
        return false;
    }

    @Override
    public double vaciar() {
        return 0.0f;
    }

    @Override
    public double servir(double volumenRestado) {
        return 0.0f;
    }
}
