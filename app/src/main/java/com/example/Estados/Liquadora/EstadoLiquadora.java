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
    private final Map<String, Float> materialMap;
    private final Scanner scanner;
    private float maxQuantity = 100.0f;

    public EstadoLiquadora() {
        this.data = new LiquadoraData();
        this.speedMap = data.getSpeedMap();
        this.materialMap = data.getMaterialMap();
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
                System.out.println(addToLiquiadora("", 0.0f));
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
     * Still working on this...
     * Current issues:
     * The data isn't being loaded into the json file
     * The data isn't being saved into the json file
     * 
     * The sum of the quantities aren't being displayed.
     * 
     * The loop is not exiting correctly.
     * 
     * The material is being added to the map, but the quantity is not being updated.
     */
    @Override
    public String addToLiquiadora(String name, Float quantity) {
        int working = 1;
        while (working != 0) {
            System.out.print("Ingrese el nombre del material (0 para salir): ");
            name = scanner.nextLine();
            if (name.equals("0")) {
                working = 0;
                return "Proceso de agregar materiales completado.";
            }
            System.out.print("Ingrese la cantidad: ");
            quantity = scanner.nextFloat();
            scanner.nextLine(); 

            if (materialMap.containsKey(name)) {
                float newQuantity = materialMap.get(name) + quantity;
                if (newQuantity > maxQuantity) {
                    System.out.println("No se puede agregar esa cantidad, la cantidad maxima es " + maxQuantity);
                    continue;
                } else {
                    materialMap.put(name, newQuantity);
                }
            } else {
                if (quantity > maxQuantity) {
                    System.out.println("No se puede agregar esa cantidad, la cantidad maxima es " + maxQuantity);
                    continue;
                } else {
                    materialMap.put(name, quantity);
                }
            }
            
            System.out.println("Material agregado: " + name + " - Cantidad: " + materialMap.get(name));
        }
        
        try {
            data.setMaterialMap(materialMap);
        } catch (IOException e) {
            return "Error al guardar los datos en el archivo JSON.";
        }
        
        float sum = 0;
        for (Float value : materialMap.values()) {
            sum += value;
        }
        return "Proceso de agregar materiales completado. Suma total de cantidades: " + sum;
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
