package com.example.data_manager;
import java.io.File;

import com.example.utils.Logger;


/**
 * Manages directory & file creation
 */
public class DataStorage {
    private static Logger log = Logger.getInstance();
    public static void initialize() {
        createDirectory(Config.EXPRESSION_DIR);
        createDirectory(Config.RESULT_DIR);
    }
    private static void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            log.logInfo( "Directory created at: " + path);
            directory.mkdirs();
        } else {
            log.logSevere("Failed to create directory: " + path);
        }
    }
        
}
