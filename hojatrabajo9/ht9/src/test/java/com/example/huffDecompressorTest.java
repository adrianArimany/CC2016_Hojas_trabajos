package com.example;


import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class huffDecompressorTest {


    @Test
    public void testDecompressOnly() throws Exception {
        // Primero, generar datos comprimidos
        String inputPath = "sample.txt";
        String text = "another example text";
        Files.writeString(new File(inputPath).toPath(), text);

        String treePath = inputPath + ".hufftree";
        String binPath  = inputPath + ".huff";
        String outputPath = "out_" + inputPath;

        // Usar compresor para crear los archivos
        HuffmanCompressor compressor = new HuffmanCompressor();
        compressor.compress(inputPath, treePath, binPath);

        // Ahora probar solo el descompresor
        HuffmanDecompressor decompressor = new HuffmanDecompressor();
        decompressor.decompress(treePath, binPath, outputPath);

        // Leer resultado
        String result = Files.readString(new File(outputPath).toPath());
        assertEquals(text, result, "another example text");

        // Limpiar
        new File(inputPath).delete();
        new File(treePath).delete();
        new File(binPath).delete();
        new File(outputPath).delete();
    }
}

