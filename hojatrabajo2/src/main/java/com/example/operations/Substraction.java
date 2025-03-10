package com.example.operations;

import com.example.utils.Logger;

public class Substraction implements Operation {
    private static final Logger log = Logger.getInstance();
    
    /**
     * Subtracts the second parameter from the first one.
     * 
     * @param n the minuend
     * @param m the subtrahend
     * @return the difference of n and m
     * @throws UnsupportedOperationException if the given numbers are not integers
     */
    @Override
    public int execute(int n, int m) {
        try {
        return (int) (n - m);
        } catch (Exception e) {
            log.logUnsupportedOperation(Number.class);
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * Subtracts the second parameter from the first one.
     * 
     * @param n the minuend
     * @param m the subtrahend
     * @return the difference of n and m
     * @throws UnsupportedOperationException if the given numbers are not integers
     */
    // @SuppressWarnings("unchecked") //Remove this if in the future we use something else than just an int.
    // @Override
    // public T execute(T n, T m) {
    //     if (n instanceof Integer && m instanceof Integer) {
    //         return (T) Integer.valueOf(n.intValue() - m.intValue());
    //     } 
    //     //Use an elseif to add other data types, for now we only support integers 
    //     else {
    //         log.logUnsupportedOperation(Number.class);
    //         throw new UnsupportedOperationException();
    //     }
    // }
}
