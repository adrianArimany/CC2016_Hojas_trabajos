package com.example.datahandler;

import java.io.File;

public class DataHandler {
/**
 * Initializes the data handler by creating the necessary directory
 * for storing Pokemon data files.
 */

    public static void initialize() {
        createDicectory(Config.HOMEAPPLIACE_DIR);
    }

    /**
     * Creates a directory if it does not exist.
     * 
     * @param path the path of the directory to create
     */
    public static void createDicectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            System.out.println("Creating directory: " + directory.getName());
            directory.mkdirs();
        }
    }

    /**
     * Creates a subdirectory within the POKEMONDATA_DIR.
     * 
     * @param subDirName the name of the subdirectory to create
     */

    public static void createSubDirectory(String subDirName) {
        String subDirPath = Config.HOMEAPPLIACE_DIR + "/" + subDirName;
        createDicectory(subDirPath);
    }

}
