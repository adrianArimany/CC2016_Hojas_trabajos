package com.example;

import java.util.Scanner;

import com.example.Estados.ManejadorDeEstados;

/**
 * Hello world!
 *
 */
public class App 
{
    private static final boolean RUNNING = true;
    
    public static void main( String[] args )
    {
        ManejadorDeEstados mEstados = new ManejadorDeEstados();
        Scanner sc = new Scanner(System.in);

        try {
            while (RUNNING) {
                if (mEstados.isSystemOn()) {
                    try {
                        System.out.println(mEstados.showMenu());
                        String input = sc.nextLine();
                        int action = Integer.parseInt(input);
                        mEstados.transition(action);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: solo se permiten numeros enteros.");
                    }
                } else {
                    System.out.println("Precione cualquier tecla para encender el sistema.");
                    sc.nextLine();
                    mEstados.setSystemOn(true);
                }
            }
        } finally {
            sc.close();
        }
    }
}
