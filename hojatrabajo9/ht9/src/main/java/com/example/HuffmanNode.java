package com.example;
import java.io.Serializable;
/**
    * The code was based on the support of GeeksforGeeks (2025). Huffman Coding in Java [Online]. 
    * Available at: https://www.geeksforgeeks.org/huffman-coding-in-java/.
    * 
    * 
    */

public class HuffmanNode implements Serializable, Comparable<HuffmanNode> {
    /**
     * Atributos para los nodos que se utilizarán en el hufftree
     */
    public char character; //Caracter que representará el nodo
    public int frequency; //Número de veces que aparece el caracter en el texto
    public HuffmanNode left,right; //Hijos del nodo

    /**
     * Constructor
     * @param character
     * @param frequency
     */
    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    /**
     * Método para determinar si un nodo es hoja del árbol
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }

    /**
     *
     * @param other otro nodo para comparar frecuencias
     * @return -1, 0 ó 1 (dependiendo si es menor, igual o mayor que el otro nodo)
     */
    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}
