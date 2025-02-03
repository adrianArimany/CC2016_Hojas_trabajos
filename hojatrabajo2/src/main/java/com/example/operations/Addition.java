package com.example.operations;

import com.example.utils.Logger;

public class Addition implements Operation {

    
    private static final Logger log = Logger.getInstance();
    
    @Override
    public int execute(int n, int m) {
        try {
        return (int) (n + m);
        } catch (Exception e) {
            log.logUnsupportedOperation(Number.class);
            throw new UnsupportedOperationException();
        }
    }

/**
 * IGNORE ME
 * Executes the addition operation between two given numbers.
 * 
 * @param n the first operand
 * @param m the second operand
 * @return the sum of n and m
 * @throws UnsupportedOperationException if the given numbers are not integers
 */

    // @SuppressWarnings("unchecked") //Remove this if in the future we use something else than just an int.
    // @Override
    // public int execute(T n, T m) {
    //     if (n instanceof Integer && m instanceof Integer) {
    //         return (T) Integer.valueOf(n.intValue() + m.intValue());
    //     } 
    //     //Use an elseif to add other data types, for now we only support integers 
    //     else {
    //         log.logUnsupportedOperation(Number.class);
    //         throw new UnsupportedOperationException();
    //     }

    // }




}

