package com.example.sort_methods;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RadixSortProfiler {

    /**
     * Radix sort implementation
     * 
     * This function sorts an array of integers using radix sort. It works by
     * sorting the array based on the digits of the numbers, starting from the
     * least significant digit and moving up to the most significant digit.
     * 
     * @param arr the array to be sorted
     */
    public static void radixSort(int[] arr) {
        int max = getMax(arr);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    /**
     * This function finds the maximum element in the given array.
     * 
     * @param arr the array to search for the maximum element
     * @return the maximum element in the array
     */
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i : arr) {
            if (i > max) max = i;
        }
        return max;
    }

    /**
     * This function performs a count sort on the given array, using the given
     * exponent to determine which digit of the numbers to sort on.
     * 
     * @param arr the array to be sorted
     * @param exp the exponent to be used for sorting
     */
    public static void countSort(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        System.arraycopy(output, 0, arr, 0, n);
    }

    /**
     * Generates a random array of integers of the specified size.
     * Each integer in the array is randomly generated and can
     * range from 0 to 99999 inclusive.
     *
     * @param size the size of the array to generate
     * @return an array of randomly generated integers
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
     * Main method to profile the performance of the radix sort algorithm.
     * Generates random arrays of varying sizes, sorts each using radix sort, 
     * and records the time taken for each sort operation in nanoseconds.
     * Results are written to a CSV file named "radix_sort_times.csv".
     *
     * @param args command line arguments (not used)
     */

    public static void main(String[] args) {
        int[] sizes = {1000, 2000, 5000, 10000, 20000, 50000};

        try (FileWriter writer = new FileWriter("radix_sort_times.csv")) {
            writer.write("InputSize,TimeNano\n");

            for (int size : sizes) {
                int[] arr = generateRandomArray(size);

                long startTime = System.nanoTime();
                radixSort(arr);
                long endTime = System.nanoTime();

                long elapsed = endTime - startTime;
                writer.write(size + "," + elapsed + "\n");

                System.out.println("Size " + size + " => Time (ns): " + elapsed);
            }
            System.out.println("Results written to radix_sort_times.csv");

        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
