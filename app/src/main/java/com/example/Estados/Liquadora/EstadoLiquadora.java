package com.example.Estados.Liquadora;

import java.util.Map;
import java.util.Scanner;

import com.example.Data.LiquadoraData;
import com.example.Estados.Estado;

public class EstadoLiquadora extends Estado implements Iliquadora {
    private float currentSpeed = 0.0f;
    private final LiquadoraData data;
    private final Map<Integer, String> speedMap;
    private final Scanner scanner;
    private final float maxQuantity = 100.0f;
    
        public EstadoLiquadora() {
            this.data = new LiquadoraData();
            this.speedMap = data.getSpeedMap();
            data.getMaterialMap();
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
            return menu.toString();
        } 
    
        @Override
        public Estado transition(int action) {
            switch (action) {
                case 1:
                    System.out.print("Ingrese la cantidad: ");
                    try {
                        Float inputQuantity = scanner.nextFloat();
                        scanner.nextLine();
                        addToLiquiadora(inputQuantity);
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor intente de nuevo.");
                        scanner.nextLine();
                    }
                    return this;
            case 2:
                System.out.println(increaseVelocity());
                return this;
            case 3:
                System.out.println(decreaseVelocity());
                return this;
            case 4:
                return this;
            default:
                System.out.println("Error: Accion invalida.");
                return this;
        }
    }  

    /**
     * Los datos todavia no se estan guardando en el archivo JSON, sigue guardandose en memoria.....
     */
    @Override
    public Float addToLiquiadora(Float materialQuantity) {
        // Map<String, Float> materialMap = data.getMaterialMap();

        // float updatedQuantity = materialQuantity;
        // if (materialMap.containsKey(materialName)) {
        //     updatedQuantity += materialMap.get(materialName);
        // }

        // if (updatedQuantity > maxQuantity) {
        //     return maxQuantity;
        // }

        // materialMap.put(updatedQuantity);
        // try {
        //     data.setMaterialMap(materialMap);
        //     System.out.println("Cantidad: " + updatedQuantity);
        // } catch (IOException e) {
        //     materialMap.put(updatedQuantity - materialQuantity);
        // }

        // float totalQuantity = materialMap.values().stream().reduce(0.0f, Float::sum);
        // return totalQuantity;
        return 0.0f;
    }


        

    @Override
    public String increaseVelocity() {
        float newSpeed = currentSpeed + 1.0f;
        if (speedMap.containsKey((int) newSpeed)) {
            currentSpeed = newSpeed;
            return "Velocidad actual: " + currentSpeed + " - " + speedMap.get((int) newSpeed);
        } else {
            return "No se puede aumentar la velocidad.";
        }
        
    }   

    @Override
    public String decreaseVelocity() {
        float newSpeed = currentSpeed - 1.0f;
        if (speedMap.containsKey((int) newSpeed)) {
            currentSpeed = newSpeed;
            return "Velocidad actual: " + currentSpeed + " - " + speedMap.get((int) newSpeed);
        }
        return "No se puede diminiuir la velocidad.";
    }   

    @Override
    public String emptyLiquiadora() {
        return "";
    }   

}
