package com.example;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.Estados.ManejadorDeEstados;
/**
 * @author Adrian Arimany Zamora
 * 
 * @version 1.0
 * 
 * Este programa esta hecho para manejar una liquadora. Implementa un finite state machine para manejar los diferentes estados 
 * Cual en este caso solo hay 2 estados: encendida y apagada de la liquadora.
 * 
 */
public class App 
{
    private static final boolean RUNNING = true;
    private static final Logger logger = Logger.getLogger(App.class.getName());
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
    
}