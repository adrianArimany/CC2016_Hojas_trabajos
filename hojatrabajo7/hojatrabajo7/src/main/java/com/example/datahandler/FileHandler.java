package com.example.datahandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileHandler {
    /**
     * Reads the content of a file specified by the file path.
     *
     * @param filePath The path of the file to be read.
     * @return The content of the file as a string. If the file path is null, 
     * returns "Error: File path is null". If an error occurs during reading, an empty string is returned.
     */
    public static String readFile(String filePath) {
        if (filePath == null) {
            return "Error: File path is null";
        }
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            
        }
        return content.toString().trim();
    }

    /**
     * Writes the given result string to a file with the specified file name.
     * The file is created in the subdirectory "updates" under the directory specified by POKEMONDATA_DIR.
     * If the subdirectory does not exist, it is created. If the file already exists, it is overwritten.
     * 
     * @param fileName The name of the file to be created or overwritten.
     * @param result The content to be written into the file.
     * If an error occurs during writing, an error message is printed to the console.
     */
     public static void writeResult(String fileName, String result) {
        // Define the subdirectory, e.g., "updates"
        String subDir = Config.HOMEAPPLIACE_DIR + "/updates";
        // Ensure the subdirectory exists
        DataHandler.createDicectory(subDir);
        
        // Write to the file in the subdirectory.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(subDir + "/" + fileName))) {
            writer.write(result);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
    * Copies a file from the specified source file path to a destination path.
    * The destination path is constructed using the POKEMONDATA_DIR directory
    * and the name of the source file. If the file already exists at the
    * destination, it is replaced.
    *
    * @param filePath The path of the source file to be copied.
    * @param destinationPath The destination path where the file should be copied.
    * If an error occurs during the copy process, an error message is printed
    * to the console.
    */
    public static void copyFile(String filePath, String destinationPath) {
        try {
                File sourceFile = new File(filePath);
                Path sourcePath = sourceFile.toPath();
                Path targetPath = Paths.get(Config.HOMEAPPLIACE_DIR, sourceFile.getName());
                Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File copied to internal directory: " + targetPath.toString());
            } catch (IOException e) {
                System.err.println("Error copying file: " + e.getMessage());
            }
    }


    
}
    
     
