package com.example.utils;
import java.util.ArrayList;
import java.util.List;
/**
 * This class exist to ensure that whatever the FileHandler reads, is first validated here before being sent to the RPNCalculator.java
 */
public class FileReaderUtil {
    private static Logger log = Logger.getInstance();
    public static List<String> readFile(String fileContent) {
        
        List<String> validExpression = new ArrayList<>();
        try {
            String[] lines = fileContent.split("\n");
            for (String line : lines) {
                if (isValidExpression(line)) { 
                    validExpression.add(line);
                } else {
                    log.logSevere("Invalid expression in line: " + line);
                }
            }
        } catch (Exception e) {
            log.logSevere("Error while reading file in FileReaderUtil: " + e.getMessage());
        }
        return validExpression;
    }

    private static boolean isValidExpression(String line) {
        return line.matches("[-?\\d+\\.\\d*\\s+]+[+\\-*/mod\\s]+");
    }
}
