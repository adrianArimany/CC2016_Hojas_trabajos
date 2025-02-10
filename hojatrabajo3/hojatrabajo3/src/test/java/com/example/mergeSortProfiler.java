package com.example;

import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;

import com.example.sort_methods.MergeSortProfiler;

public class mergeSortProfiler {
     @Test
    public void testMergeSort_EmptyArray() {
        int[] arr = {};
        MergeSortProfiler.mergeSort(arr);
        int[] expected = {};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testMergeSort_SingleElement() {
        int[] arr = {5};
        MergeSortProfiler.mergeSort(arr);
        int[] expected = {5};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testMergeSort_AlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        MergeSortProfiler.mergeSort(arr);
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testMergeSort_ReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        MergeSortProfiler.mergeSort(arr);
        int[] expected = {1, 2, 3, 4, 5};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testMergeSort_UnsortedArrayWithDuplicates() {
        int[] arr = {4, 2, 9, 6, 5, 1, 8, 3, 7, 5};
        MergeSortProfiler.mergeSort(arr);
        int[] expected = {1, 2, 3, 4, 5, 5, 6, 7, 8, 9};
        assertArrayEquals(expected, arr);
    }
    @Test
    public void testMergeSort_UnsortedArrayWithNegativeNumbers() {
        int[] arr = {-5, 2, -9, 6, -1, 8, -3, 7, 4};
        MergeSortProfiler.mergeSort(arr);
        int[] expected = {-9, -5, -3, -1, 2, 4, 6, 7, 8};
        assertArrayEquals(expected, arr);
    }
}
