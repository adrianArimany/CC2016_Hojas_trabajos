package com.example;


import java.io.File;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class huffmanCompressorTest {

    @Test
    public void testCompressAndDecompressRoundTrip() throws Exception {
        // Preparar archivo de prueba
        String inputPath = "test_input.txt";
        String content = "this is a test of huffman compression";
        Files.writeString(new File(inputPath).toPath(), content);

        // Rutas de salida
        String treePath = inputPath + ".hufftree";
        String binPath  = inputPath + ".huff";
        String decodedPath = "decoded_" + inputPath;

        // Ejecutar compresión
        HuffmanCompressor compressor = new HuffmanCompressor();
        compressor.compress(inputPath, treePath, binPath);

        // Verificar que se crearon los archivos
        assertTrue("El archivo .hufftree no fue creado", new File(treePath).exists());
        assertTrue("El archivo .huff no fue creado", new File(binPath).exists());

        // Ejecutar descompresión usando HuffmanDecompressor
        HuffmanDecompressor decompressor = new HuffmanDecompressor();
        decompressor.decompress(treePath, binPath, decodedPath);

        // Verificar contenido resultante
        String decoded = Files.readString(new File(decodedPath).toPath());
        assertEquals(content, decoded, "this is a test of huffman compression");

        // Limpiar archivos generados
        new File(inputPath).delete();
        new File(treePath).delete();
        new File(binPath).delete();
        new File(decodedPath).delete();
    }
}
