package com.example.gui;
import javax.swing.*;
import java.awt.*;
/**
 * Defines layout components (buttons, labels, text area).
 * 
 * @TODO Find a use of controller in this class.
 */
public class CalculadoraView extends JFrame {
    private final JButton btnSelectFile;
    private final JTextArea resultArea;
   
    public CalculadoraView() {
        setTitle("Calculadora");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        btnSelectFile = new JButton("Select .txt File");
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(btnSelectFile, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);
    }

    public JButton getBtnSelectFile() {
        return btnSelectFile;
    }
    public JTextArea getResultArea() {
        return resultArea;
    }
}
