package com.example.operations;

/**
 * This interface defines a mathematical operation that takes two parameters of type <T extends Number> and returns a result of the same type.
 * The operation is defined in the implementing class and can be any valid mathematical operation.
 * All the operations in the com.example.operations package implement this interface.
 */
public interface  Operation {
    int execute(int n, int m);
}
