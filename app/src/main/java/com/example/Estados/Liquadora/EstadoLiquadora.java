package com.example.Estados.Liquadora;

import java.util.Map;

import com.example.Data.LiquadoraData;
import com.example.Estados.Estado;

public class EstadoLiquadora extends Estado implements Iliquadora {
    private float currentSpeed = 0.0f;
    private final LiquadoraData data;
    private final Map<Integer, String> speedMap;
    
    public EstadoLiquadora() {
        this.data = new LiquadoraData();
        this.speedMap = data.getSpeedMap();
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
    public String addToLiquiadora(int index) { 
        return "";
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
