package com.example.object_calculadora;

import java.util.Stack;

import com.example.factory.OperationFactory;
import com.example.operations.Operation;


/**
 * This is my calculator logic, that uses the interface Icalculator.
 * 
 * @note THis class will not use logger because other people need to reuse this class, so to avoid any conflict, I will not use logger.
 * 
 */

public class RPNCalculator implements ICalculadora {  
    @Override
    public int evaluate(String expressionString) {
        final Stack<Integer> operandStack = new Stack<>();
        final String[] expressionTokens = expressionString.split("\\s+");

        try {
            for (final String token : expressionTokens) {
                if (isNumber(token)) {
                    operandStack.push(parseNumber(token));  // Push numbers onto the stack
                } else if (isValidOperator(token)) {
                    if (operandStack.size() < 2) {
                        throw new IllegalStateException("Not enough operands for operator: " + token);
                    }
                    
                    // Pop two operands from the stack
                    final int secondOperand = operandStack.pop();
                    final int firstOperand = operandStack.pop();
                    
                    // Get the correct operation from the factory
                    final Operation operation = (Operation)OperationFactory.getOperation(token); //here is the issue....
                    
                    // Execute the operation and push the result back
                    operandStack.push(operation.execute(firstOperand, secondOperand));
                } else {
                    throw new IllegalArgumentException("Invalid token encountered: " + token);
                }
            }
            
            if (operandStack.size() != 1) {
                throw new IllegalStateException("Invalid RPN expression. Stack should contain exactly one element at the end.");
            }

            return operandStack.pop(); // The final result
        } catch (final ArithmeticException e) {
            throw new IllegalStateException("Arithmetic exception: " + e.getMessage(), e);
        }
    }

    







    /**
     * Returns true if the given string matches the pattern of a number.
     * A number is defined as a string containing an optional minus sign followed by one or more digits,
     * optionally followed by a decimal point and one or more digits.
     * <p>
     * This method is used to check if a given string is a valid token in an RPN expression.
     * @param str the string to check
     * @return true if the string matches the pattern of a number, false otherwise
     */
    private boolean isNumber(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Checks if the given string is a valid operator in an RPN expression.
     * 
     * @param str the string to check
     * @return true if the string is a valid operator, false otherwise
     */
    private boolean isValidOperator(String str) {
        return str.matches("[+\\-*/]") || str.equals("mod");
    }
    
    /**
     * Parses the given string as a number of the specified type.
     * If the type is Integer, it casts the string to an Integer.
     * For any unsupported types, it logs an error and throws an UnsupportedOperationException.
     * 
     * @param str the string to parse as a number
     * @return the parsed number of type T
     * @throws UnsupportedOperationException if the type is not supported
     */

    private int parseNumber(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format: " + str, e);
        }
    }
}
