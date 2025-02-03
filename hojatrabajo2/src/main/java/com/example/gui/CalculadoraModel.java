package com.example.gui;

import java.util.ArrayList;
import java.util.List;

import com.example.data_manager.FileHandler;
import com.example.object_calculadora.RPNCalculator;
import com.example.utils.FileReaderUtil;


/**
 * Calls RPNCalculator<T> and processes results.
 */
public class CalculadoraModel {
/**
 * Processes a file containing RPN expressions, evaluates each expression, 
 * and returns a list of results. Each result is a string in the format 
 * "expression = result" or "expression = ERROR" if evaluation fails.
 *
 * @param filePath the path to the file containing RPN expressions
 * @return a list of strings representing the evaluated results of each expression
 */


     public List<String> processFile(String filePath) {
        String fileContent = FileHandler.readFile(filePath);
        List<String> expressions = FileReaderUtil.readFile(fileContent);

        RPNCalculator calculator = new RPNCalculator();
        List<String> results = new ArrayList<>();

        for (String expression : expressions) {
            try {
                int result = calculator.evaluate(expression);
                results.add(expression + " = " + result);
            } catch (Exception e) {
                results.add(expression + " = ERROR");
            }
        }

        return results;
    }
    
}
