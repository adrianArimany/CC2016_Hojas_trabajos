package com.example.sort_methods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class QuickSortProfiler {

    /**
     * This function sorts an array of integers using the quick sort algorithm.
     * 
     * @param arr the array to be sorted
     * @param low the starting index of the array to be sorted
     * @param high the ending index of the array to be sorted
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * This function partitions the given array for the quick sort algorithm.
     * 
     * @param arr the array to be partitioned
     * @param low the starting index of the array to be partitioned
     * @param high the ending index of the array to be partitioned
     * @return the index of the pivot element after partitioning
     */
    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    /**
     * Generates an array of random integers of the given size.
     * 
     * @param size the size of the array to be generated
     * @return an array of random integers of the given size
     */
    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100000);
        }
        return arr;
    }

    /**
     * Runs the quick sort algorithm on arrays of different sizes and prints the
     * execution time to the console and a CSV file. The sizes of the arrays are
     * 1000, 2000, 5000, 10000, 20000, and 50000. The execution times are in
     * nanoseconds.
     */
    public static void main(String[] args) {
        int[] sizes = {1000, 5053, 55222, 994200, 952220, 9935200};

        try (FileWriter writer = new FileWriter("quick_sort_times.csv")) {
            writer.write("InputSize,TimeNano\n");

            for (int size : sizes) {
                int[] arr = generateRandomArray(size);

                long startTime = System.nanoTime();
                quickSort(arr, 0, arr.length - 1);
                long endTime = System.nanoTime();

                long elapsed = endTime - startTime;
                writer.write(size + "," + elapsed + "\n");

                System.out.println("Size " + size + " => Time (ns): " + elapsed);
            }
            System.out.println("Results written to quick_sort_times.csv");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
