package com.example.operations;

import com.example.utils.Logger;

public class Multiplication<T extends Number> implements Operation<T> {
    private static Logger log = Logger.getInstance();
    /**
     * Executes the multiplication operation between two given numbers.
     * 
     * @param n the multiplicand
     * @param m the multiplier
     * @return the result of the multiplication
     * @throws UnsupportedOperationException if the given numbers are not integers
     */
    @SuppressWarnings("unchecked") //Remove this if in the future we use something else than just an int.
    @Override
    public T execute(T n, T m) {
        
        if (n instanceof Integer && m instanceof Integer) {
            return (T) Integer.valueOf(n.intValue() * m.intValue());
        } 
        //Use an elseif to add other data types, for now we only support integers 
        else {
            log.logUnsupportedOperation(Number.class);
            throw new UnsupportedOperationException();
        }
    }
}
