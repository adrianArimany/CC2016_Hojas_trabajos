package com.example;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.fail;
import org.junit.Test;

import com.example.sort_methods.BogoSortProfiler;

public class BOGOSORTPROFILER {
    @Test
    public void testAlreadySortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        BogoSortProfiler.bogoSort(arr);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, arr);
    }
    @Test
    public void testReversedSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        BogoSortProfiler.bogoSort(arr);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, arr);
    }
    @Test
    public void testDuplicateElements() {
        int[] arr = {4, 2, 2, 1, 3};
        BogoSortProfiler.bogoSort(arr);
        assertArrayEquals(new int[] {1, 2, 2, 3, 4}, arr);
    }
    @Test
    public void testNegativeNumbers() {
        int[] arr = {-3, 2, -1, 4, 0};
        BogoSortProfiler.bogoSort(arr);
        assertArrayEquals(new int[] {-3, -1, 0, 2, 4}, arr);
    }
    @Test
    public void testEmptyArray() {
        int[] arr = {};
        BogoSortProfiler.bogoSort(arr);
        assertArrayEquals(new int[] {}, arr);
    }
    @Test
    public void testNullArray() {
        int[] arr = null;
        try {
            BogoSortProfiler.bogoSort(arr);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected
        }
    }
}
