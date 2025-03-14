package com.example.data;

import java.io.File;

public class DataHandler {
    public static void initialize() {
        createDicectory(Config.POKEMONDATA_DIR);
    }

    public static void createDicectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            System.out.println("Creating directory: " + directory.getName());
            directory.mkdirs();
        }
    }

    
}
