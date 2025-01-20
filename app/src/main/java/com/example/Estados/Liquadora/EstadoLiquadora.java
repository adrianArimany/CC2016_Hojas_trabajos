package com.example.Estados.Liquadora;

import java.io.IOException;
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
                addToLiquiadora("", 0.0f, scanner);
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

    @Override
    public String addToLiquiadora(String materialName, Float materialQuantity, Scanner scanner) {
        if (materialName == null || materialQuantity == null) {
            return "";
        }

        Map<String, Float> materialMap = data.getMaterialMap();
        if (materialMap == null) {
            return "";
        }   

        boolean exit = false;
        while (!exit) {
            System.out.print("Ingrese el nombre del material (escriba EXIT para salir): ");
            String inputName = scanner.nextLine();

            if ("EXIT".equalsIgnoreCase(inputName)) {
                exit = true;
                break;
            }

            System.out.print("Ingrese la cantidad: ");
            try {
                materialQuantity = scanner.nextFloat();
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor intente de nuevo.");
                scanner.nextLine(); 
                continue;
            }

            // Check and update material quantities
            float newQuantity = materialQuantity;
            if (materialMap.containsKey(inputName)) {
                newQuantity += materialMap.get(inputName);
            }

            if (newQuantity > maxQuantity) {
                System.out.println("No se puede agregar esa cantidad, la cantidad máxima es " + maxQuantity);
                continue;
            }

            materialMap.put(inputName, newQuantity);
            try {
                data.setMaterialMap(materialMap);
                System.out.println("Material agregado: " + inputName + " - Cantidad: " + newQuantity);
            } catch (IOException e) {
                System.out.println("Error al guardar los datos en el archivo JSON.");
                // Optionally revert the change if save failed
                if (materialMap.get(inputName) == newQuantity) {
                    materialMap.put(inputName, newQuantity - materialQuantity);
                }
            }
        }

        float totalQuantity = materialMap.values().stream().reduce(0.0f, Float::sum);
        return "Proceso de agregar materiales completado. Suma total de cantidades: " + totalQuantity;
        
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
