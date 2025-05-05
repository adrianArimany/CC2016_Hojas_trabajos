package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Aplicación de ejemplo para compresión y descompresión Huffman.
 * Al ejecutarse sin argumentos, genera un archivo de prueba,
 * luego lo comprime y posteriormente lo descomprime automáticamente.
 */
public class App {

    public static void main(String[] args) {
        if (args.length == 0) {
            // Modo automático: generar, comprimir y descomprimir
            automaticRun("test.txt");
            return;
        }

        switch (args[0]) {
            case "-c" -> handleCompression(args);
            case "-d" -> handleDecompression(args);
            default -> {
                System.out.println("Opción desconocida: " + args[0]);
                printUsage();
            }
        }
    }

    private static void printUsage() {
        System.out.println("Uso:");
        System.out.println("  (sin args)  -> Genera test.txt, comprime y descomprime");
        System.out.println("  Comprimir:  java com.example.App -c archivo.txt");
        System.out.println("  Descomprimir: java com.example.App -d archivo.txt.hufftree archivo.txt.huff");
    }

    /**
     * Ejecución automática: crea test.txt, comprime y descomprime.
     */
    private static void automaticRun(String inputPath) {
        try {
            createTestFile(inputPath);
        } catch (IOException e) {
            System.err.println("Error creando archivo de prueba: " + e.getMessage());
            return;
        }

        String treePath = inputPath + ".hufftree";
        String binaryPath = inputPath + ".huff";
        String outputDecoded = "decoded_" + inputPath;

        HuffmanCompressor compressor = new HuffmanCompressor();
        HuffmanDecompressor decompressor = new HuffmanDecompressor();
        try {
            System.out.println("--- Compresión automática ---");
            compressor.compress(inputPath, treePath, binaryPath);
            System.out.println("Compresión completada: ");
            System.out.println("  Árbol -> " + treePath);
            System.out.println("  Binario -> " + binaryPath);

            System.out.println("--- Descompresión automática ---");
            decompressor.decompress(treePath, binaryPath, outputDecoded);
            System.out.println("Descompresión completada:");
            System.out.println("  Salida -> " + outputDecoded);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en proceso automático: " + e.getMessage());
        }
    }

    private static void handleCompression(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso para comprimir: java com.example.App -c archivo.txt");
            return;
        }
        String inputPath = args[1];
        try {
            createTestFile(inputPath);
        } catch (IOException e) {
            System.err.println("Error creando archivo de prueba: " + e.getMessage());
            return;
        }

        String treePath = inputPath + ".hufftree";
        String binaryPath = inputPath + ".huff";
        HuffmanCompressor compressor = new HuffmanCompressor();
        try {
            compressor.compress(inputPath, treePath, binaryPath);
            System.out.println("Compresión completada.");
            System.out.println("  Árbol: " + treePath);
            System.out.println("  Binario: " + binaryPath);
        } catch (IOException e) {
            System.err.println("Error al comprimir: " + e.getMessage());
        }
    }

    private static void handleDecompression(String[] args) {
        if (args.length != 3) {
            System.out.println("Uso para descomprimir: java com.example.App -d archivo.txt.hufftree archivo.txt.huff");
            return;
        }
        String treeInput = args[1];
        String binInput = args[2];
        String outputPath = args[1].replace(".hufftree", ".decoded.txt");
        HuffmanDecompressor decompressor = new HuffmanDecompressor();
        try {
            decompressor.decompress(treeInput, binInput, outputPath);
            System.out.println("Descompresión completada.");
            System.out.println("  Salida: " + outputPath);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al descomprimir: " + e.getMessage());
        }
    }

    /**
     * Crea o sobrescribe un archivo con contenido aleatorio.
     */
    private static void createTestFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("Archivo existente: " + filePath);
            return;
        }
        if (!file.createNewFile()) {
            throw new IOException("No se pudo crear " + filePath);
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Random random = new Random();
            String alphabet = "abcdefghijklmnopqrstuvwxyz ";
            int length = 100;
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
            }
            writer.write(sb.toString());
        }
        System.out.println("Archivo de prueba creado: " + filePath);
    }
}
