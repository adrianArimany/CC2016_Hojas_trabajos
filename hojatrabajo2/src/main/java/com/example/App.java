package com.example;

import com.example.data_manager.DataStorage;
import com.example.gui.CalculadoraGUI;
import com.example.utils.Logger;


/**
 * @author Adrian Arimany Zamora
 * 
 * @version 1.0
 * 
 * 
 * @TODO 
 * -Fix the case where a letter shows up in the txt file
 * 
 * -For some reason the mod or % operator doesn't worl
 * 
 * -I think the logger is not logging anything into the log file
 * 
 * - 
 * 
 */
public class App 
{
    private static Logger log = Logger.getInstance();

    public static void main(String[] args)
    {
        try {
            DataStorage.initialize();
            new CalculadoraGUI().runGUI();
        } catch (Exception e) {
            log.logSevere("An error has occurred, please check the logs for more information.");
            log.logInfo("This is the error message: " + e.getMessage());
        }
    }
}
