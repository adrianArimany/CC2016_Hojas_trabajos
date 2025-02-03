package com.example.data_manager;
import java.io.File;

import com.example.utils.Logger;


/**
 * Manages directory & file creation
 */
public class DataStorage {
    public static Logger log = Logger.getInstance();
    /**
     * Initializes the data storage system by creating the necessary directories.
     * <p>
     * This method should be called at least once before using any other methods in
     * this class.
     */
    public static void initialize() {
        createDirectory(Config.EXPRESSION_DIR);
        createDirectory(Config.RESULT_DIR);
    }
    /**
     * Creates a directory at the specified path if it does not already exist.
     *
     * <p>
     * If the directory is created successfully, a log message is written with the
     * path of the created directory. If the directory is not created, a log message
     * is written with the reason for failure.
     *
     * @param path the path to the directory to be created
     */
    public static void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            log.logInfo( "Directory created at: " + path);
            directory.mkdirs();
        } else {
            log.logSevere("Failed to create directory: " + path);
        }
    }
        
}
