package com.example;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App {

    public static void app(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso:");
            System.out.println("  Comprimir:    java Main -c archivo.txt");
            System.out.println("  Descomprimir: java Main -d archivo.txt.hufftree archivo.txt.huff");
            return;
        }

        switch (args[0]) {
            case "-c" -> {
                // Comprimir
                if (args.length != 2) {
                    System.out.println("Uso para comprimir: java Main -c archivo.txt");
                    return;
                }
                String inputPath = args[1];
                String treePath = inputPath + ".hufftree";
                String binaryPath = inputPath + ".huff";

                HuffmanCompressor compressor = new HuffmanCompressor();
                try {
                    compressor.compress(inputPath, treePath, binaryPath);
                    System.out.println("Compresión completada.");
                    System.out.println("Archivo del árbol: " + treePath);
                    System.out.println("Archivo binario:   " + binaryPath);
                } catch (IOException e) {
                    System.err.println("Error al comprimir: " + e.getMessage());
                }
            }

            case "-d" -> {
                // Descomprimir
                if (args.length != 3) {
                    System.out.println("Uso para descomprimir: java Main -d archivo.hufftree archivo.huff");
                    return;
                }

                String treeInput = args[1];
                String binInput = args[2];
                String outputPath = treeInput.replace(".hufftree", ".decoded.txt");

                HuffmanDecompressor decompressor = new HuffmanDecompressor();
                try {
                    decompressor.decompress(treeInput, binInput, outputPath);
                    System.out.println("Descompresión completada.");
                    System.out.println("Texto recuperado en: " + outputPath);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Error al descomprimir: " + e.getMessage());
                }
            }

            default -> {
                System.out.println("Opción desconocida: " + args[0]);
                System.out.println("Uso:");
                System.out.println("Comprimir:    java Main -c archivo.txt");
                System.out.println("Descomprimir: java Main -d archivo.hufftree archivo.huff");
            }
        }
    }
}