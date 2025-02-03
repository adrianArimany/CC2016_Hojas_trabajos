package com.example.object_calculadora;

import java.util.Vector;

import com.example.factory.OperationFactory;
import com.example.operations.Operation;
import com.example.utils.Logger;

public class RPNCalculator<T extends Number> implements Icalculadora<T> {
    private static Logger log = Logger.getInstance();
    private final Class<T> type; // Store type for casting results

    public RPNCalculator(Class<T> type) {
        this.type = type;
    }

    /**
     * Evaluates a given RPN expression and returns the result as a strongly typed number.
     * 
     * @param expression a string containing a valid RPN expression
     * @return the result of the RPN expression as a number of type T
     * @throws IllegalStateException if the expression is invalid or results in an arithmetic exception
     * @throws IllegalArgumentException if an invalid token is encountered
     */
    @Override
    public T evaluate(String expressionString) {
        log.logInfo("Start evaluating expression: " + expressionString);
        final Vector<T> operandStack = new Vector<>();
        final String[] expressionTokens = expressionString.split("\\s+");

        try {
            for (final String token : expressionTokens) {
                if (isNumber(token)) {
                    operandStack.add(parseNumber(token));
                } else if (isValidOperator(token)) {
                    if (operandStack.size() < 2) {
                        throw new IllegalStateException("Not enough operands for operator: " + token);
                    }
                    final T secondOperand = operandStack.remove(operandStack.size() - 1);
                    final T firstOperand = operandStack.remove(operandStack.size() - 1);
                    final Operation<T> operation = OperationFactory.getOperation(token);
                    operandStack.add(operation.execute(firstOperand, secondOperand));
                } else {
                    throw new IllegalArgumentException("Invalid token encountered: " + token);
                }
            }
            if (operandStack.size() != 1) {
                throw new IllegalStateException("Invalid RPN expression");
            }
            final T result = operandStack.get(0);
            log.logInfo("Result of expression: " + result);
            return result;
        } catch (final ArithmeticException e) {
            log.logSevere("Arithmetic exception: " + e.getMessage());
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

    private T parseNumber(String str) {
        if (type == Integer.class) {
            return type.cast(Integer.valueOf(str));
        } // remember that if you want to add other data types, you need to add them here as well..
        else {
            log.logSevere("Unsupported type: " + type.getName());
            throw new UnsupportedOperationException("Unsupported type: " + type.getName());
        }
    }
}
