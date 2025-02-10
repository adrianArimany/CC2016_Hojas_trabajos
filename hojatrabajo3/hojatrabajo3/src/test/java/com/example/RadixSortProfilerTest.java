package com.example;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.example.sort_methods.RadixSortProfiler;
/**
 * Unit test for simple App.
 */


public class RadixSortProfilerTest {

    @Test
    public void testCountSort_EmptyArray() {
        int[] arr = new int[0];
        RadixSortProfiler.countSort(arr, 1);
        assertEquals(0, arr.length);
    }

    @Test
    public void testCountSort_SingleElementArray() {
        int[] arr = new int[] {5};
        RadixSortProfiler.countSort(arr, 1);
        assertEquals(5, arr[0]);
    }

    @Test
    public void testCountSort_MultiElementArray_SameDigits() {
        int[] arr = new int[] {111, 222, 333, 444};
        RadixSortProfiler.countSort(arr, 1);
        int[] expected = new int[] {111, 222, 333, 444};
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testCountSort_ArrayWithZeros() {
        int[] arr = new int[] {0, 5, 0, 3, 0, 1};
        RadixSortProfiler.countSort(arr, 1);
        int[] expected = new int[] {0, 0, 0, 1, 3, 5};
        assertArrayEquals(expected, arr);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testCountSort_ArrayWithNegativeNumbers() {
        int[] arr = new int[] {-1, 5, 0, 3, -2, 1};
        RadixSortProfiler.countSort(arr, 1);
    }
}







