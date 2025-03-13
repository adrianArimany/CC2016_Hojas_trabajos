package com.example;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.data.Config;
import com.example.data.FileHandler;

public class dataTests {
    @Test
    void testwriteResultdeletion() throws IOException {
        String fileName = "testFile.txt";
        String result = "This is a test";
        FileHandler.writeResult(fileName, result);
        String content = FileHandler.readFile(Config.POKEMONDATA_DIR + fileName);
        assertEquals(result, content);
        new File(Config.POKEMONDATA_DIR + fileName).delete();
    }

    @Test
    void testingwriteResult() throws IOException {
        String fileName = "testFile.txt";
        String result = "This is a test";
        FileHandler.writeResult(fileName, result);
        String content = FileHandler.readFile(Config.POKEMONDATA_DIR + fileName);
        assertEquals(result, content);
    }

    @Test
    void testWriteResultCSV() throws IOException {
        String fileName = "testFile.csv";
        String result = "name,age\nJohn,30\nDoe,40";
        FileHandler.writeResult(fileName, result);
        String content = FileHandler.readFile(Config.POKEMONDATA_DIR + fileName);
        assertEquals(result, content);
    }
}
