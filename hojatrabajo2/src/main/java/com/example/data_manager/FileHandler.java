package com.example.data_manager;
import java.io.*;
import com.example.utils.Logger;

/**
 * Handles file I/O
 */
public class FileHandler {
    private static Logger log = Logger.getInstance();
    public static String readFile(String filePath)  {
        
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();    
        } catch (IOException e) {
            log.logSevere("Failed to read file: " + e.getMessage() + " " + filePath);
            e.printStackTrace();
        }
        return content.toString().trim();
    }
    public static void writeResult(String fileName, String result) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Config.RESULT_DIR + fileName));
            writer.write(result);
            writer.close();
        } catch (IOException e) {
            log.logSevere("Failed to read file: " + e.getMessage() + " " + fileName);
            e.printStackTrace();
        }
    }
}
