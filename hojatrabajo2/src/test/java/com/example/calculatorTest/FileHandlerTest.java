package com.example.calculatorTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import com.example.data_manager.FileHandler;


/**
 * @TODO 
 * 
 * 1. Add a null exeption to fileHandler readFile (FIXED)
 * 
 * 
 */
public class FileHandlerTest {

    @Test
    public void testReadFileWithContent() throws IOException {
        // Create a test file with content
        File file = new File("test.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("Hello World!");
        }

        // Read the file using FileHandler
        String content = FileHandler.readFile(file.getAbsolutePath());

        // Verify the content
        assertEquals("Hello World!", content);

        // Clean up
        file.delete();
    }

    @Test
    public void testReadFileWithNoContent() throws IOException {
        // Create an empty test file
        File file = new File("test.txt");
        file.createNewFile();

        // Read the file using FileHandler
        String content = FileHandler.readFile(file.getAbsolutePath());

        // Verify the content
        assertEquals("", content);

        // Clean up
        file.delete();
    }

    @Test
    public void testReadNonExistentFile() {
        // Try to read a non-existent file
        String content = FileHandler.readFile("non_existent_file.txt");

        // Verify that an error is logged and content is empty
        assertEquals("", content);
    }

    @Test
    public void testReadFileWithInvalidPath() {
        // Try to read a file with invalid path (null)
        String content = FileHandler.readFile(null);

        // Verify that an error is logged and content is empty
        assertEquals("", content);

        // Try to read a file with invalid path (empty string)
        content = FileHandler.readFile("");

        // Verify that an error is logged and content is empty
        assertEquals("", content);
    } 
}
