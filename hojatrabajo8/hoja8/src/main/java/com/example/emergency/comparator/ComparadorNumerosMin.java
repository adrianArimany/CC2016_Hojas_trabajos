package com.example.emergency.comparator;

import java.util.Comparator;
/**
 * Extracted from the original code to be used as a generic class for the:
 * @author moises.alonso (Original author) (source code: https://github.com/malonso-uvg/uvg2025ed/blob/main/e14_BinaryHeap/src/TreeNode.java)
 * 
 */
public class ComparadorNumerosMin implements Comparator<Integer>{

/**
 * Compares two Integer objects for order.
 *
 * @param o1 the first Integer to compare
 * @param o2 the second Integer to compare
 * @return a positive integer if o1 is less than o2, zero if they are equal,
 *         or a negative integer if o1 is greater than o2
 */

    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1.equals(o2)){
            return 0;
        } if (o1 < o2){
            return 1;
        } else {
            return -1;
        }
    }
    
}
