package com.example.sort_methods;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class MergeSortProfiler {
    // 1. Merge Sort Implementation
    public static void mergeSort(int[] arr) {
        if (arr.length <= 1) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, mid);
        System.arraycopy(arr, mid, right, 0, arr.length - mid);

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        // Copy remaining elements
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    // 2. Method to generate a random array of given size
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);  // random int in [0..99999]
        }
        return arr;
    }

    public static void main(String[] args) {
        // Define the input sizes you want to test
        int[] sizes = {1000, 2000, 5000, 10000, 20000, 50000};

        // Prepare CSV file
        try (FileWriter writer = new FileWriter("merge_sort_times.csv")) {
            // Write CSV header
            writer.write("InputSize,TimeNano\n");

            // 3. Loop over input sizes, time the algorithm, and record
            for (int size : sizes) {
                int[] arr = generateRandomArray(size);

                long startTime = System.nanoTime();
                mergeSort(arr);
                long endTime = System.nanoTime();

                long elapsed = endTime - startTime;

                // Write data row to CSV
                writer.write(size + "," + elapsed + "\n");

                System.out.println("Size " + size + " => Time (ns): " + elapsed);
            }
            System.out.println("Results written to merge_sort_times.csv");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}

