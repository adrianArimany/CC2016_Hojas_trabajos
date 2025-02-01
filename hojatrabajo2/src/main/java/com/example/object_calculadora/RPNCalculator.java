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

    @Override
    public T evaluate(String expression) {
        Vector<T> stack = new Vector<>();
        String[] tokens = expression.split("\\s+");

        log.logInfo("Evaluating expression: " + expression);

        for (String token : tokens) {
            log.logInfo("Processing token: " + token);
            if (isNumber(token)) {
                stack.add(parseNumber(token));
            } else {
                log.logInfo("Found operator: " + token);
                Operation<T> operation = OperationFactory.getOperation(token);
                if (stack.size() < 2) {
                    log.logSevere("Not enough operands for operation: " + token);
                    throw new IllegalStateException("Not enough operands for operation: " + token);
                }
                T b = stack.remove(stack.size() - 1);
                T a = stack.remove(stack.size() - 1);
                stack.add(operation.execute(a, b));
            }
        }
        if (stack.size() != 1) {
            log.logSevere("Invalid RPN expression");
            throw new IllegalStateException("Invalid RPN expression");
        }
        return stack.get(0);
    }

    private boolean isNumber(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private T parseNumber(String str) {
        if (type == Integer.class) {
            return type.cast(Integer.valueOf(str));
        } else if (type == Double.class) {
            return type.cast(Double.valueOf(str));
        } else {
            log.logSevere("Unsupported type: " + type.getName());
            throw new UnsupportedOperationException("Unsupported type: " + type.getName());
        }
    }
}

