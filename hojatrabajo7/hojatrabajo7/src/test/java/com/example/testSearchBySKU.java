package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.objecthomeappliance.HomeApplianceRecord;
import com.example.objecthomeappliance.Isku;
import com.example.objecthomeappliance.MapSkuCSV;

public class testSearchBySKU {
    @Test
public void testInsertion() throws Exception {
    String csvContent = """
    CATEGORY,PRODUCT NAME,SKU,PRICE CURRENT,PRICE RETAIL
    Appliances,Toaster,SKU123,75.0,90.0
    Kitchen,Blender,SKU456,80.0,100.0
    Living Room,TV,SKU789,500.0,600.0
    """;
    Path tempFile = Files.createTempFile("testSearch", ".csv");
    Files.write(tempFile, csvContent.getBytes());

    // Instantiate the model using MapSkuCSV.
    Isku model = new MapSkuCSV(new HashMap<>());
    model.loadFrom(tempFile.toAbsolutePath().toString());

    // Test searching for an existing SKU.
    List<HomeApplianceRecord> results = model.searchBySku("SKU123");
    assertNotNull(results, "Results for SKU123 should not be null.");
    assertFalse(results.isEmpty(), "Results for SKU123 should not be empty.");
    HomeApplianceRecord record = results.get(0);
    assertEquals("SKU123", record.getSKU(), "The SKU should match.");
    assertEquals("Toaster", record.getProductName(), "The product name should match.");

    // Test searching for a non-existent SKU.
    List<HomeApplianceRecord> noResults = model.searchBySku("NONEXISTENT");
    assertNotNull(noResults, "Results for a non-existent SKU should not be null.");
    assertTrue(noResults.isEmpty(), "Results for a non-existent SKU should be empty.");

    // Clean up the temporary file.
    Files.deleteIfExists(tempFile);
}
}
