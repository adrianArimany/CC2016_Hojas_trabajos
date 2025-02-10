package com.example.factory;

import com.example.operations.Addition;
import com.example.operations.Division;
import com.example.operations.Modulus;
import com.example.operations.Multiplication;
import com.example.operations.Operation;
import com.example.operations.Substraction;

public class OperationFactory {
    public static Operation getOperation(String operator) {
        return switch (operator) {
            case "+" -> (Operation) new Addition();
            case "-" -> (Operation) new Substraction();
            case "*" -> (Operation) new Multiplication();
            case "/" -> (Operation) new Division();
            case "%" -> (Operation) new Modulus();
            default -> throw new IllegalArgumentException(); 
        };
    }
}
