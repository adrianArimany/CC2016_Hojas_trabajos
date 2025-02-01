package com.example.utils;

import java.io.IOException;
import java.lang.System.Logger.Level;
import java.util.logging.SimpleFormatter;

import com.example.data_manager.FileHandler;

public class Logger {
    private static Logger instance;
    private static java.util.logging.Logger logger;

    private Logger() {
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
            logger.setLevel(java.util.logging.Level.INFO);
            FileHandler fileHandler = new FileHandler(logFilePath, true);
            logger.addHandler(fileHandler);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up logger", e);
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
}
