package com.example;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.example.sort_methods.BucketSortProfiler;

public class BuscketSortProfilerTest {
    @Test
    public void testBucketSort_EmptyArray() {
        int[] arr = new int[0];
        BucketSortProfiler.bucketSort(arr);
        assertEquals(0, arr.length);
    }
    @Test
    public void testBucketSort_SingleElementArray() {
        int[] arr = new int[] {5};
        BucketSortProfiler.bucketSort(arr);
        assertArrayEquals(new int[] {5}, arr);
    }
    @Test
    public void testBucketSort_AlreadySortedArray() {
        int[] arr = new int[] {1, 2, 3, 4, 5};
        BucketSortProfiler.bucketSort(arr);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, arr);
    }
    @Test
    public void testBucketSort_ReverseSortedArray() {
        int[] arr = new int[] {5, 4, 3, 2, 1};
        BucketSortProfiler.bucketSort(arr);
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, arr);
    }
    @Test
    public void testBucketSort_ArrayWithDuplicates() {
        int[] arr = new int[] {4, 2, 9, 6, 5, 1, 8, 3, 7, 4, 2};
        BucketSortProfiler.bucketSort(arr);
        assertArrayEquals(new int[] {1, 2, 2, 3, 4, 4, 5, 6, 7, 8, 9}, arr);
    }
    @Test
    public void testBucketSort_LargeArray() {
        int[] arr = new int[100];
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(1000);
        }
        BucketSortProfiler.bucketSort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            assertTrue(arr[i] <= arr[i + 1]);
        }
    }
}
