package com.example;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

    /**
    * The code was based on the support of GeeksforGeeks (2025). Huffman Coding in Java [Online]. 
    * Available at: https://www.geeksforgeeks.org/huffman-coding-in-java/.
    * 
    * 
    */


public class HuffmanCompressor {
    /**
     * Atributo del compresor
     */
    private final Map<Character, String> huffmanCodes = new HashMap<>(); //Mapa que servirá para guardar la codificación de cada caracter

    
    /**
     * Método para comprimir un archivo de texto
     * @param inputFilePath //Ruta del archivo de texto
     * @param treeOutputPath //Ruta donde se quiere guardar el hufftree
     * @param binaryOutputPath //Ruta donde se quiere guardar el texto codificado
     * @throws IOException
     */
    public void compress(String inputFilePath, String treeOutputPath, String binaryOutputPath) throws IOException {
        String text = new String(Files.readAllBytes(new File(inputFilePath).toPath()));
        Map<Character, Integer> frequencyMap = buildFrequencyMap(text);
        HuffmanNode root = buildHuffmanTree(frequencyMap);
        buildCodeMap(root, "");

        writeTreeToFile(root, treeOutputPath);
        writeEncodedText(text, binaryOutputPath);
    }

    /**
     * Método para calcular y guardar la frecuencia de cada caracter
     * @param text
     * @return mapa con cada uno los carácteres y sus frecuencias
     */
    private Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    /**
     *Método para crear el árbol y guardarlo a partir de su raíz
     * @param freqMap
     * @return la ráiz del árbol
     */
    private HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> var : freqMap.entrySet()) {
            queue.offer(new HuffmanNode(var.getKey(), var.getValue()));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode('\0', left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            queue.offer(parent);
        }
        return queue.poll();
    }

    /**
     * Método para codificar cada carácter a partir de los "pasos" que se tienen que seguir en el árbol para llegar a el desde la raíz
     * @param node
     * @param code
     */
    private void buildCodeMap(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code);
        } else {
            buildCodeMap(node.left, code + '0');
            buildCodeMap(node.right, code + '1');
        }
    }

    /**
     * Guardar el árbol en un archivo .hufftree
     * @param root
     * @param treeOutputPath
     * @throws IOException
     */
    private void writeTreeToFile(HuffmanNode root, String treeOutputPath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(treeOutputPath))) {
            oos.writeObject(root);
        }
    }

    /**
     * Guardar el texto codificado en un archivo .huff
     * @param text
     * @param binaryOutputPath
     * @throws IOException
     */
    private void writeEncodedText(String text, String binaryOutputPath) throws IOException {
        StringBuilder encoded = new StringBuilder();
        for (char c : text.toCharArray()) {
            encoded.append(huffmanCodes.get(c));
        }

        BitSet bitSet = new BitSet(encoded.length());
        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == '1') {
                bitSet.set(i);
            }
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(binaryOutputPath))) {
            dos.writeInt(encoded.length()); // número de bits significativos
            byte[] bytes = bitSet.toByteArray();
            dos.writeInt(bytes.length);     // longitud real en bytes
            dos.write(bytes);
        }
    }
}
