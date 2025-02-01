package com.example.operations;

public class Modulus implements Operation {
    @Override
    public int execute(int n, int m) {
        return n % m;
    }
}
