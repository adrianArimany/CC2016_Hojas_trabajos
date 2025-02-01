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
     * @param expression a string containing a valid RPN expression.
     * @return the result of the RPN expression as a number of type T.
     * @throws IllegalStateException if the expression is invalid or results in an arithmetic exception.
     * @throws IllegalArgumentException if an invalid token is encountered.
     * 
     * @TODO the part isLetter(token) is not handling letter correctly
     */
    @Override
    public T evaluate(String expression) {
        Vector<T> operandStack = new Vector<>();
        String[] expressionTokens = expression.split("\\s+");
        try {
            for (String token : expressionTokens) {
                if (isNumber(token)) {
                    operandStack.add(parseNumber(token));
                } else if (isValidOperator(token)) {
                    if (operandStack.size() < 2) {
                        log.logSevere("Not enough operands for operator: " + token);
                        throw new IllegalStateException("Not enough operands for operator: " + token);
                    }
                    T secondOperand = operandStack.remove(operandStack.size() - 1);
                    T firstOperand = operandStack.remove(operandStack.size() - 1);
                    Operation<T> operation = OperationFactory.getOperation(token);
                    operandStack.add(operation.execute(firstOperand, secondOperand));
                } else if (isLetter(token)) { //This is still not hadnling letter correctly.
                    removeLetter(operandStack, token);
                    log.logSevere("Unwanted letter encountered: " + token);
                    throw new IllegalArgumentException("Unwanted letter encountered: " + token);
                } else {
                    log.logSevere("Invalid token encountered: " + token);
                    throw new IllegalArgumentException("Invalid token encountered: " + token);
                }
            }
            if (operandStack.size() != 1) {
                log.logSevere("Invalid RPN expression");
                throw new IllegalStateException("Invalid RPN expression");
            }
            return operandStack.get(0);
        } catch (ArithmeticException e) {
            log.logSevere("Arithmetic exception: " + e.getMessage());
            throw new IllegalStateException("Arithmetic exception: " + e.getMessage());
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
