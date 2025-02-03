package com.example.gui;
import javax.swing.*;

/**
 * 	Utility class for displaying messages & dialogs.
 */
public class GUIUtils {

/**
 * Displays an error message dialog with the given message.
 *
 * @param message the message to be displayed in the error dialog
 */

    public static void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Displays an information message dialog with the given message.
     *
     * @param message the message to be displayed in the information dialog
     */
    public static void showInfoMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
