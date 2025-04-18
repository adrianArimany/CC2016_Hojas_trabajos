package com.example.emergency.comparator;
import java.util.Comparator;

/**
 * 
 */

/**
 * Extracted from the original code to be used as a generic class for the:
 * @author moises.alonso (Original author) (source code: https://github.com/malonso-uvg/uvg2025ed/blob/main/e14_BinaryHeap/src/TreeNode.java)
 * 
 */
public class ComparadorNumeros<Integer> implements Comparator<Integer> {

/**
 * Compares two Integer objects numerically.
 * 
 * @param o1 the first Integer to compare
 * @param o2 the second Integer to compare
 * @return a negative integer, zero, or a positive integer as the first argument
 *         is less than, equal to, or greater than the second.
 */

	@Override
	public int compare(Integer o1, Integer o2) {
		if ((int)o1 == (int)o2)
			return 0;
		else if ((int)o1 < (int)o2)
			return -1;
		else 
			return 1;
	}

}
