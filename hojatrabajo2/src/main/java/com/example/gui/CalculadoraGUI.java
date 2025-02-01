package com.example.gui;
import javax.swing.*;
/**
 * The main GUI window (JFrame/JavaFX).
 */

 public class CalculadoraGUI {
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraView view = new CalculadoraView();
            new CalculadoraController(view); 
            view.setVisible(true);
        });
    }

    public void runGUI() {
        SwingUtilities.invokeLater(() -> {
            CalculadoraView view = new CalculadoraView();
            new CalculadoraController(view); 
            view.setVisible(true);
        });
    }

}

