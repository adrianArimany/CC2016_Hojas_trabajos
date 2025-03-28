package com.example.gui;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GuiUtil {
    /**
     * Opens a file chooser dialog and returns the path of the selected file.
     *
     * @param parent the parent frame of the file chooser dialog
     * @return the path of the selected file, or null if no file was selected
     */
    public static String chooseFile(JFrame parent) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            return file.getAbsolutePath();
        }
        return null;
    }
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

