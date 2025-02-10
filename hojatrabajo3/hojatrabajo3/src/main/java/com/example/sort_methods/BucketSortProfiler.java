package com.example.sort_methods;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BucketSortProfiler {

/**
 * Sorts an array of integers using the bucket sort algorithm.
 * 
 * This function divides the array into a number of buckets determined by the
 * square root of the array's length. Each element is distributed into a
 * bucket based on its value. Each bucket is then sorted individually using
 * the Collections.sort method, and the sorted buckets are merged back into
 * the original array.
 * 
 * @param arr the array to be sorted
 */

    public static void bucketSort(int[] arr) {
        int n = arr.length;
        int maxVal = Arrays.stream(arr).max().orElse(1);
        int bucketCount = (int) Math.sqrt(n);
        @SuppressWarnings("unchecked")
        List<Integer>[] buckets = new List[bucketCount];

        // Initialize buckets
        for (int i = 0; i < bucketCount; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Distribute elements into buckets
        for (int num : arr) {
            int index = (num * bucketCount) / (maxVal + 1);
            buckets[index].add(num);
        }

        // Sort each bucket and merge back
        int index = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int num : bucket) {
                arr[index++] = num;
            }
        }
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 5000, 10000, 20000, 50000};

        try (FileWriter writer = new FileWriter("bucket_sort_times.csv")) {
            writer.write("InputSize,TimeNano\n");

            for (int size : sizes) {
                int[] arr = generateRandomArray(size);

                long startTime = System.nanoTime();
                bucketSort(arr);
                long endTime = System.nanoTime();

                long elapsed = endTime - startTime;
                writer.write(size + "," + elapsed + "\n");

                System.out.println("Size " + size + " => Time (ns): " + elapsed);
            }
            System.out.println("Results written to bucket_sort_times.csv");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
