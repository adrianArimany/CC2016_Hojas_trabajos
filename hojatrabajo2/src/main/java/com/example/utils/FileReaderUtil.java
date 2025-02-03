package com.example.utils;
import java.util.ArrayList;
import java.util.List;
/**
 * This class exist to ensure that whatever the FileHandler reads, is first validated here before being sent to the RPNCalculator.java
 */
public class FileReaderUtil {
    private static Logger log = Logger.getInstance();
    /**
     * This method takes a file content and filters out any invalid expressions.
     * If an expression has a letter, it will be removed and then checked again.
     * If the expression is still invalid, it will be logged in the log file.
     * 
     * @param fileContent the content of the file to process
     * @return a list of valid expressions
     */
    public static List<String> readFile(String fileContent) {
        
        List<String> validExpression = new ArrayList<>();
        try {
            String[] lines = fileContent.split("\n");
            for (String line : lines) {
                if (isValidExpression(line)) { 
                    validExpression.add(line);
                } else {
                    line = removeLetter(line);
                    if (isValidExpression(line)) {
                        validExpression.add(line);
                    } else {
                        log.logSevere("Invalid expression in line: " + line);
                    }
                }
            }
        } catch (Exception e) {
            log.logSevere("Error while reading file in FileReaderUtil: " + e.getMessage());
        }
        return validExpression;
    }

    /**
     * This method takes a line of text and removes any letter from it.
     * 
     * This is used to handle the case where a letter shows up in the txt file.
     * 
     * @param line the line of text to remove any letter from
     * @return the line of text with any letters removed
     */
    private static String removeLetter(String line) {
        return line.replaceAll("[a-zA-Z]+", "");
    }


/**
 * Checks if the given line is a valid expression.
 * A valid expression is defined by a sequence of numbers (which may include decimals)
 * and operators (+, -, *, /, mod) separated by spaces.
 *
 * @param line the line of text to check
 * @return true if the line is a valid expression, false otherwise
 */

    private static boolean isValidExpression(String line) {
        return line.matches("[-?\\d+\\.\\d*\\s+]+[+\\-*/mod\\s]+");
    }
}


