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

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    private void setupLogger() {
        try {
            String logFilePath = "../logs/app.log";
            
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

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logWarning(String message) {
        logger.warning(message);
    }

    public void logSevere(String message) {
        logger.severe(message);
    }

    public void logUnsupportedOperation(Class<?> clazz) {
        logger.info(String.format("Currently Unsupported operation with this data type: %s", clazz.getName()));
    }
}
