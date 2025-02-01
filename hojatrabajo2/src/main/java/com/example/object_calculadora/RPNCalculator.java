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
                } else if (isLetter(token)) {
                    removeLetter(operandStack, token);
                    throw new IllegalArgumentException("Unwanted letter encountered: " + token);
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

    private boolean isNumber(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean isValidOperator(String str) {
        return str.matches("[+\\-*/]") || str.equals("mod");
    }
    
    private boolean isLetter(String str) {
        return str.matches("[a-zA-Z]+");
    }
    private void removeLetter(Vector<T> stack, String token) {
        if (isLetter(token)) {
            stack.remove(stack.size() - 1);
        }
    }
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
