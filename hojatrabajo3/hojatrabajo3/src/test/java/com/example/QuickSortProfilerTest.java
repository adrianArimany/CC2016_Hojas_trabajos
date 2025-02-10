package com.example;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import com.example.sort_methods.QuickSortProfiler;

public class QuickSortProfilerTest {
        @Test
    public void testQuickSort_AlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        QuickSortProfiler.quickSort(arr, 0, arr.length - 1);
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testQuickSort_ReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        QuickSortProfiler.quickSort(arr, 0, arr.length - 1);
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testQuickSort_DuplicateElements() {
        int[] arr = {2, 4, 1, 3, 2, 4};
        QuickSortProfiler.quickSort(arr, 0, arr.length - 1);
        int[] expected = {1, 2, 2, 3, 4, 4};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testQuickSort_NegativeNumbers() {
        int[] arr = {-3, 2, -1, 4, 0};
        QuickSortProfiler.quickSort(arr, 0, arr.length - 1);
        int[] expected = {-3, -1, 0, 2, 4};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testQuickSort_SingleElement() {
        int[] arr = {5};
        QuickSortProfiler.quickSort(arr, 0, arr.length - 1);
        int[] expected = {5};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testQuickSort_EmptyArray() {
        int[] arr = {};
        QuickSortProfiler.quickSort(arr, 0, arr.length - 1);
        int[] expected = {};
        assertArrayEquals(expected, arr);
    }
}



