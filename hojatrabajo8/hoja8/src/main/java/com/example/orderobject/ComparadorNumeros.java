package com.example.orderobject;
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
