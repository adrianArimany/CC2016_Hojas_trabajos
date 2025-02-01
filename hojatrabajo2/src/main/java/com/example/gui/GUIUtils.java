package com.example.gui;
import javax.swing.*;

/**
 * 	Utility class for displaying messages & dialogs.
 */
public class GUIUtils {

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
