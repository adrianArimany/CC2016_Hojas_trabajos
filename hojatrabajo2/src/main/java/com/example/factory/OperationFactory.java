package com.example.factory;

import com.example.operations.Addition;
import com.example.operations.Division;
import com.example.operations.Modulus;
import com.example.operations.Multiplication;
import com.example.operations.Operation;
import com.example.operations.Substraction;

public class OperationFactory<T extends Number> {
    public static <T extends Number> Operation<T> getOperation(String operator) {
        return switch (operator) {
            case "+" -> new Addition<>();
            case "-" -> new Substraction<>();
            case "*" -> new Multiplication<>();
            case "/" -> new Division<>();
            case "%" -> new Modulus<>();
            default -> throw new IllegalArgumentException(); 
        };
    }
}
