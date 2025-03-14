package com.example.sort_methods;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class BogoSortProfiler {

/**
 * Sorts the given array using the bogo sort algorithm. This algorithm 
 * repeatedly shuffles the array until it is sorted. It is highly 
 * inefficient for large arrays due to its factorial time complexity.
 * 
 * @param arr the array to be sorted
 */

    public static void bogoSort(int[] arr) {
        Random rand = new Random();
        while (!isSorted(arr)) {
            shuffle(arr, rand);
        }
    }

    /**
     * Checks if the given array is sorted in ascending order.
     * 
     * @param arr the array to be checked
     * @return true if the array is sorted, false otherwise
     */
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Shuffles the given array using the Fisher-Yates shuffle algorithm.
     * 
     * @param arr the array to be shuffled
     * @param rand the random number generator to be used
     */
    private static void shuffle(int[] arr, Random rand) {
        for (int i = arr.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] sizes = {4, 5, 8, 9, 10, 11}; 

        try (FileWriter writer = new FileWriter("bogo_sort_times.csv")) {
            writer.write("InputSize,TimeNano\n");

            for (int size : sizes) {
                int[] arr = generateRandomArray(size);

                long startTime = System.nanoTime();
                bogoSort(arr);
                long endTime = System.nanoTime();

                long elapsed = endTime - startTime;
                writer.write(size + "," + elapsed + "\n");

                System.out.println("Size " + size + " => Time (ns): " + elapsed);
            }
            System.out.println("Results written to bogo_sort_times.csv");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
