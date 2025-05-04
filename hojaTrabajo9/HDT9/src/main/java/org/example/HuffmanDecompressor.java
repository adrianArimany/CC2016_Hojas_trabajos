package org.example;
import java.io.*;
import java.util.BitSet;

public class HuffmanDecompressor {
    /**
     * Método para decodificar un texto codificado a partir de su hufftree y código huff
     * @param treePath ruta del arbol
     * @param binaryPath ruta del texto codificado
     * @param outputPath ruta donde se guardará el texto
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void decompress(String treePath, String binaryPath, String outputPath) throws IOException, ClassNotFoundException {
        HuffmanNode root = readTreeFromFile(treePath);
        String encodedBits = readEncodedBits(binaryPath);
        String decodedText = decode(encodedBits, root);
        writeDecodedText(outputPath, decodedText);
    }

    /**
     * Método para re-armar el árbol
     * @param treePath
     * @return raíz del árbol
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private HuffmanNode readTreeFromFile(String treePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(treePath))) {
            return (HuffmanNode) ois.readObject();
        }
    }

    /**
     * Método para obtener el texto codificado
     * @param binaryPath
     * @return String del texto codificado
     * @throws IOException
     */
    private String readEncodedBits(String binaryPath) throws IOException {
        try (DataInputStream dis = new DataInputStream(new FileInputStream(binaryPath))) {
            int bitLength = dis.readInt();      // Número de bits significativos
            int byteLength = dis.readInt();     // Número real de bytes
            byte[] bytes = new byte[byteLength];
            dis.readFully(bytes);

            BitSet bitSet = BitSet.valueOf(bytes);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bitLength; i++) {
                sb.append(bitSet.get(i) ? '1' : '0');
            }
            return sb.toString();
        }
    }

    /**
     * Método para decodificar el texto a partir del árbol (la raíz)
     * @param encodedBits
     * @param root
     * @return
     */
    private String decode(String encodedBits, HuffmanNode root) {
        StringBuilder result = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedBits.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.isLeaf()) {
                result.append(current.character);
                current = root;
            }
        }

        return result.toString();
    }

    /**
     * Método para guardar en un archivo de texto según la ruta indicada el texto que se descodificó a partir del código 
     * @param outputPath
     * @param text
     * @throws IOException
     */
    private void writeDecodedText(String outputPath, String text) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            writer.write(text);
        }
    }
}
