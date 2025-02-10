package com.example.sort_methods;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class QuickSortProfiler {

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
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
