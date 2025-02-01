package com.example.operations;

public class Modulus implements Operation<Number> {
    @Override
    public Number execute(Number n, Number m) {
        return n.intValue() % m.intValue();
    }
}
