package com.example.gui;
import javax.swing.*;
/**
 * The main GUI window (JFrame/JavaFX).
 * This runs the graphical interface, which is launched in App.java
 */

 public class CalculadoraGUI {
    
    public void runGUI() {
        SwingUtilities.invokeLater(() -> {
            CalculadoraView view = new CalculadoraView();
            new CalculadoraController(view); 
            view.setVisible(true);
        });
    }

}

