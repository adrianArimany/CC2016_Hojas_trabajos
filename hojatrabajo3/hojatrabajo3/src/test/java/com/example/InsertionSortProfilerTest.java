package com.example;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.example.sort_methods.InsertionSortProfiler;

public class InsertionSortProfilerTest {
     @Test
    public void testEmptyArray() {
        int[] arr = new int[0];
        InsertionSortProfiler.insertionSort(arr);
        assertEquals(0, arr.length);
    }
    @Test
    public void testSingleElementArray() {
        int[] arr = new int[] {5};
        InsertionSortProfiler.insertionSort(arr);
        assertEquals(5, arr[0]);
    }
    @Test
    public void testAlreadySortedArray() {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        InsertionSortProfiler.insertionSort(arr);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, arr);
    }
    @Test
    public void testReverseSortedArray() {
        int[] arr = new int[] {5, 4, 3, 2, 1};
        InsertionSortProfiler.insertionSort(arr);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, arr);
    }
    @Test
    public void testArrayWithDuplicates() {
        int[] arr = new int[] {4, 2, 9, 6, 5, 1, 8, 3, 7, 5};
        InsertionSortProfiler.insertionSort(arr);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5, 5, 6, 7, 8, 9}, arr);
    }
    @Test
    public void testArrayWithNegativeNumbers() {
        int[] arr = new int[] {-5, 2, -9, 6, -1, 8, -3, 7};
        InsertionSortProfiler.insertionSort(arr);
        assertArrayEquals(new int[] {-9, -5, -3, -1, 2, 6, 7, 8}, arr);
    }
    @Test
    public void testLargeArray() {
        int[] arr = new int[100];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }
        InsertionSortProfiler.insertionSort(arr);
        for (int i = 1; i < arr.length; i++) {
            assertTrue(arr[i - 1] <= arr[i]);
        }
    }
}
