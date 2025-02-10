package com.example.data_manager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.example.utils.Logger;

/**
 * Handles file I/O
 */
public class FileHandler {
    private static final Logger log = Logger.getInstance();
/**
 * Reads the content of a file specified by the given file path.
 * Logs an error and returns an empty string if the file path is null or if an IOException occurs.
 *
 * @param filePath the path to the file to be read
 * @return the content of the file as a String, or an empty string if an error occurs
 */

    public static String readFile(String filePath)  {
        if (filePath == null) {
            log.logSevere("Cannot read file with null path");
            return "";
        }
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            log.logSevere("Failed to read file: " + e.getMessage() + " " + filePath);
            
        }
        return content.toString().trim();
    }
/**
 * Writes the given result to a file specified by the given file name in the result directory.
 * Logs an error if an IOException occurs during the writing process.
 *
 * @param fileName the name of the file to write the result to
 * @param result the content to be written to the file
 */

    public static void writeResult(String fileName, String result) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(Config.RESULT_DIR + fileName))) {
                writer.write(result);
            } catch (IOException e) {
                log.logSevere("Failed to write file: " + e.getMessage() + " " + fileName);
            }
        }
    }

