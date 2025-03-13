package com.example.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
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

    public static void writeResult(String fileName, String result) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Config.POKEMONDATA_DIR + "/" + fileName))) {
            writer.write(result);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

    
     
