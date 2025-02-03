package com.example.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;

/**
 * This class is exclusively to manage logging in the application.
 * This way I don't have to import love logger in every class of the application.
 * 
 * 
 */
public class Logger {
    private static Logger instance;
    private static java.util.logging.Logger logger;

    public Logger() {
        logger = java.util.logging.Logger.getLogger(Logger.class.getName());
        setupLogger();
    }

    /**
     * Gets the instance of the Logger class. If the instance is null, it creates a new Logger and assigns it to the instance variable.
     * 
     * @return the instance of the Logger class.
     */
    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Configures the logger. This method is private and should only be called from the constructor.
     * 
     * It creates a new FileHandler, sets the log level to ALL and adds it to the logger. It also sets the logger to not use parent handlers.
     * 
     * If an IOException is thrown, it is caught and the stack trace is printed to the console.
     */
    private void setupLogger() {
        try {
            String logFilePath = "logs/app.log";
            
            File logDir = new File("../logs");
            if (!logDir.exists()) {
                logDir.mkdirs();
            }

            FileHandler fileHandler = new FileHandler(logFilePath, true);
            fileHandler.setFormatter(new SimpleFormatter()); // Format log messages
            
            logger.setLevel(Level.ALL);
            fileHandler.setLevel(Level.ALL);
            
            logger.addHandler(fileHandler);

            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs a message with the INFO level.
     * 
     * @param message the message to be logged
     */
    public void logInfo(String message) {
        logger.info(message);
    }


    /**
     * Logs a message with the WARNING level.
     * 
     * @param message the message to be logged
     */

    /**
     * Logs a message with the WARNING level.
     * 
     * @param message the message to be logged
     */
    public void logWarning(String message) {
        logger.warning(message);
    }

    /**
     * Logs a message with the SEVERE level.
     * 
     * @param message the message to be logged
     */

    public void logSevere(String message) {
        logger.severe(message);
    }

    /**
     * Logs a message indicating that an operation is currently unsupported for the given data type.
     * 
     * @param clazz the class object representing the unsupported data type
     */

    public void logUnsupportedOperation(Class<?> clazz) {
        logger.info(String.format("Currently Unsupported operation with this data type: %s", clazz.getName()));
    }
}
