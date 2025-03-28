package com.example.objecthomeappliance;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.datahandler.FileHandler;
import com.example.searchstructure.BST;
import com.example.searchstructure.Ibst;

public class MapSkuCSV implements Isku {

    private final Map<String, HomeApplianceRecord> homeAppliaceMap;
    private final Ibst<String, HomeApplianceRecord> bst;

    public MapSkuCSV(Map<String, HomeApplianceRecord> homeAppliaceMap) {
        this.homeAppliaceMap = homeAppliaceMap;
        this.bst = new BST<>();
        for (HomeApplianceRecord record : homeAppliaceMap.values()) {
            bst.insert(record.getSKU(), record);
        }
    }

    @Override
public void loadFrom(String filePath) {
    String fileContent = FileHandler.readFile(filePath);
    if (fileContent == null || fileContent.isEmpty()) {
        System.out.println("File is empty or could not be read.");
        return;
    }
    
    // Split on both Windows and Unix line breaks.
    String[] lines = fileContent.split("\\r?\\n");
    System.out.println("Total lines read: " + lines.length);
    if (lines.length < 2) {
        System.out.println("File has no data.");
        return;
    }
    
    // Parse header. Normalize headers by replacing underscores with spaces.
    String[] headers = lines[0].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    int categoryIndex = -1, nameIndex = -1, skuIndex = -1, priceIndex = -1, retailIndex = -1;
    for (int i = 0; i < headers.length; i++) {
        String header = headers[i].trim().toLowerCase().replace("_", " ");
        switch (header) {
            case "category" -> categoryIndex = i;
            case "product name" -> nameIndex = i;
            case "sku" -> skuIndex = i;
            case "price current" -> priceIndex = i;
            case "price retail" -> retailIndex = i;
            default -> { }
        }
    }
    
    if (categoryIndex == -1 || nameIndex == -1 || skuIndex == -1 || priceIndex == -1 || retailIndex == -1) {
        System.out.println("File missing required columns. Columns found: " 
                + categoryIndex + ", " + nameIndex + ", " + skuIndex + ", " + priceIndex + ", " + retailIndex);
        return;
    }
    
    // Clear any existing data.
    homeAppliaceMap.clear();
    
    // Process each record.
    for (int i = 1; i < lines.length; i++) {
        String line = lines[i];
        // Use regex-based split for the entire line.
        String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        if (parts.length <= Math.max(categoryIndex, Math.max(nameIndex, Math.max(skuIndex, Math.max(priceIndex, retailIndex))))) {
            System.out.println("Skipping line " + i + ": insufficient parts.");
            continue;
        }
        String category = parts[categoryIndex].trim();
        String name = parts[nameIndex].trim();
        String sku = parts[skuIndex].trim();
        
        // Normalize the numeric strings by removing quotes.
        String priceStr = parts[priceIndex].trim().replace("\"", "");
        String retailStr = parts[retailIndex].trim().replace("\"", "");
        // Replace empty numeric fields with "0".
        if (priceStr.isEmpty()) {
            priceStr = "0";
        }
        if (retailStr.isEmpty()) {
            retailStr = "0";
        }
        
        float price;
        float retail;
        try {
            price = Float.parseFloat(priceStr);
            retail = Float.parseFloat(retailStr);
        } catch (NumberFormatException nfe) {
            System.out.println("Skipping line " + i + " due to number format exception: " + nfe.getMessage());
            continue;
        }
        
        HomeApplianceRecord record = new HomeApplianceRecord(sku, retail, price, name, category);
        homeAppliaceMap.put(sku, record);
        // Insert into BST (with duplicate key handling based on Price_Current).
        bst.insert(sku, record);
    }
    }




    @Override
    public void saveTo(String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Category,Product Name,SKU, Price Current, Price Retail\n"); 
        homeAppliaceMap.values().forEach(record -> {
            sb.append(record.getCategory()).append(",")
              .append(record.getProductName()).append(",")
              .append(record.getSKU()).append(",")
              .append(record.getPriceCurrent()).append(",").append(record.getPriceRetail()).append("\n");
        });
        com.example.datahandler.FileHandler.writeResult(fileName, sb.toString());
    }

    @Override
    public List<HomeApplianceRecord> searchBySku(String skuQuery) {
        // Here I use  BST to efficiently search by SKU.
        HomeApplianceRecord found = bst.search(skuQuery);
        List<HomeApplianceRecord> result = new ArrayList<>();
        if (found != null) {
            result.add(found);
        }
        return result;
    }

    @Override
    public String getAllHomeAppliaceRecord() {
        StringBuilder result = new StringBuilder();
        homeAppliaceMap.values().forEach(record -> result.append(record.toString()).append("\n"));
        return result.toString();
    }
    
}
