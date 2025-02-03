package com.example.operations;

import com.example.utils.Logger;

public class Multiplication implements Operation {
    private static final Logger log = Logger.getInstance();
    
    /**
     * Returns the product of n and m.
     * 
     * @param n the first number
     * @param m the second number
     * @return the product of n and m
     * @throws UnsupportedOperationException if the operation is not supported
     */
    @Override
    public int execute(int n, int m) {
        try {
        return (int) (n * m);
        } catch (Exception e) {
            log.logUnsupportedOperation(Number.class);
            throw new UnsupportedOperationException();
        }
    }
    
    
    
    
    
    
    
    /**
     * Executes the multiplication operation between two given numbers.
     * 
     * @param n the multiplicand
     * @param m the multiplier
     * @return the result of the multiplication
     * @throws UnsupportedOperationException if the given numbers are not integers
     */
    // @SuppressWarnings("unchecked") //Remove this if in the future we use something else than just an int.
    // @Override
    // public T execute(T n, T m) {
        
    //     if (n instanceof Integer && m instanceof Integer) {
    //         return (T) Integer.valueOf(n.intValue() * m.intValue());
    //     } 
    //     //Use an elseif to add other data types, for now we only support integers 
    //     else {
    //         log.logUnsupportedOperation(Number.class);
    //         throw new UnsupportedOperationException();
    //     }
    // }
}
