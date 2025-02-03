package com.example.utils;

import java.util.Vector;
/**
 * IGNORE THIS CLASS, this is incase of using vector in RPNCalculator you want to use STACKS directly,
 * I didn't end up using this, because every other group would have put this util in their program to run RPNCalculator.
 * 
 * I will keep it just in case.
 * 
 * THIS CLASS IS NOT BEING IMPLEMENTED AS OF THIS VERSION.
 * 
 * So my Desktop Computer has a different compiler version of java, which if you find wanrning of potential conflict then is because you probably have the newest version of
 * Java compiler.
 * 
 */


public class StackADT<T> {
    private final Vector<T> stack;

    public StackADT() {
        this.stack = new Vector<>();
    }

    public void push(T value) {
        stack.add(value);
    }

    public T pop() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.remove(stack.size() - 1);
    }

    public T peek() {
        if (stack.isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return stack.get(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}

