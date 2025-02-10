package com.example.sort_methods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class InsertionSortProfiler {

    /**
     * Sorts the given array using the insertion sort algorithm. This algorithm
     * iterates over the array, starting from the second element. For each
     * element, it compares it with the previous elements and shifts them as
     * necessary to insert the current element in its correct sorted position.
     *
     * @param arr the array to be sorted
     */
    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
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

    public static void main(String[] args) {
        int[] sizes = {6, 66, 666, 6666, 66666, 666666};
        try (FileWriter writer = new FileWriter("insertion_sort_times.csv")) {
            writer.write("InputSize,TimeNano\n");

            for (int size : sizes) {
                int[] arr = generateRandomArray(size);

                long startTime = System.nanoTime();
                insertionSort(arr);
                long endTime = System.nanoTime();

                long elapsed = endTime - startTime;
                writer.write(size + "," + elapsed + "\n");

                System.out.println("Size " + size + " => Time (ns): " + elapsed);
            }
            System.out.println("Results written to insertion_sort_times.csv");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}