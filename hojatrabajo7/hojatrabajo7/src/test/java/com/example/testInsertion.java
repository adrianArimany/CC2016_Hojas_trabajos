package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.objecthomeappliance.Isku;
import com.example.objecthomeappliance.MapSkuCSV;

public class testInsertion {
    @Test
    public void testInsertionMethod() throws Exception {
    // Create a temporary CSV file with sample records.
    String csvContent = """
    CATEGORY,PRODUCT NAME,SKU,PRICE CURRENT,PRICE RETAIL
    Appliances,Toaster,SKU123,75.0,90.0
    Kitchen,Blender,SKU456,80.0,100.0
    Living Room,TV,SKU789,500.0,600.0
    """;
    Path tempFile = Files.createTempFile("testInsertion", ".csv");
    Files.write(tempFile, csvContent.getBytes());

    // Instantiate the model using MapSkuCSV.
    Isku model = new MapSkuCSV(new HashMap<>());
    model.loadFrom(tempFile.toAbsolutePath().toString());

    // Verify that all records have been inserted by checking the output from getAllHomeAppliaceRecord.
    String allRecords = model.getAllHomeAppliaceRecord();
    assertNotNull(allRecords, "The returned record list should not be null.");
    assertTrue(allRecords.contains("SKU123"), "Record with SKU123 should be present.");
    assertTrue(allRecords.contains("SKU456"), "Record with SKU456 should be present.");
    assertTrue(allRecords.contains("SKU789"), "Record with SKU789 should be present.");

    // Clean up the temporary file.
    Files.deleteIfExists(tempFile);
}

}
