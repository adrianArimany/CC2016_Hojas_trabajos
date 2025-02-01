package com.example.operations;


public interface  Operation<T extends Number> {
    T execute(T n, T m);
}
