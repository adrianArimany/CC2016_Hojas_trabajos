package com.example;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.objecthomeappliance.HomeApplianceRecord;
import com.example.objecthomeappliance.Isku;
import com.example.objecthomeappliance.MapSkuCSV;
public class MapSkuTest {
    
private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
private PrintStream originalOut;

@BeforeAll
public void setUpStreams() {
    // Capture System.out to verify printed output.
    originalOut = System.out;
    System.setOut(new PrintStream(outContent));
}

@AfterAll
public void restoreStreams() {
    System.setOut(originalOut);
}

@Test
public void testLoadFrom_MissingRequiredColumns() {
    // Create a CSV string missing some required columns.
    // For instance, only including SKU and PRICE CURRENT.
    String csvContent = """
    SKU,PRICE CURRENT
    ABC123,100.0
    """;

    // Create a temporary file and write the csvContent into it.
    Path tempFile = null;
    try {
        tempFile = Files.createTempFile("test_missing_columns", ".csv");
        Files.write(tempFile, csvContent.getBytes());

        // Instantiate the model.
        Isku model = new MapSkuCSV(new HashMap<String, HomeApplianceRecord>());

        // Call loadFrom using the path to the temporary file.
        model.loadFrom(tempFile.toAbsolutePath().toString());

        // Capture the output and verify that the error message is present.
        String output = outContent.toString();
        assertTrue(output.contains("File missing required columns"), "Expected error message for missing required columns");

    } catch (IOException | NullPointerException e) {
        fail("Exception during test: " + e.getMessage());
    } finally {
        // Clean up the temporary file.
        try {
            if (tempFile != null) {
                Files.deleteIfExists(tempFile);
            }
        } catch (IOException | SecurityException e) {
            // ignore cleanup exceptions
        }
    }
}
}
