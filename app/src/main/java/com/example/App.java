package com.example;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.Data.LiquadoraData;
import com.example.Estados.ManejadorDeEstados;
/**
 * 
 *
 */
public class App 
{
    private static final boolean RUNNING = true;
    private static final Logger logger = Logger.getLogger(LiquadoraData.class.getName());  
    public static void main(String[] args)
    {
        ManejadorDeEstados mEstados = new ManejadorDeEstados();
        try (Scanner sc = new Scanner(System.in)) {
            while (RUNNING) {
                if (mEstados.isSystemOn()) {
                    try {
                        System.out.println(mEstados.showMenu());
                        String input = sc.nextLine();
                        int action = Integer.parseInt(input);
                        mEstados.transition(action);
                    } catch (NumberFormatException e) {
                        logger.log(Level.WARNING, "Solo se permiten numeros enterors", e);
                    }
                } else {
                    logger.log(Level.OFF , "Precione cualquier tecla para encender el sistema.");
                    sc.nextLine();
                    mEstados.setSystemOn(true);
                }
            }
        }
    }

    /**
     * I can't find the right place to clearConsole without bugging out the whole system, so for now the method will be chilling in this corner.
     */
    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            logger.log(Level.SEVERE, "Error Clearing Console", e);
        }
    }
    
}
