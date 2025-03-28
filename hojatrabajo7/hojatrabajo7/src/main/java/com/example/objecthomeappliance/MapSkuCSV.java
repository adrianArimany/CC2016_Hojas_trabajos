package com.example.objecthomeappliance;

import java.util.List;
import java.util.Map;

import com.example.datahandler.FileHandler;
import com.example.searchstructure.BST;
import com.example.searchstructure.Ibst;

public class MapSkuCSV implements Isku {

    private final Map<String, HomeApplianceRecord> homeAppliaceMap;

    public MapSkuCSV(Map<String, HomeApplianceRecord> homeAppliaceMap) {
        this.homeAppliaceMap = homeAppliaceMap;
        Ibst<String, HomeApplianceRecord> bst = new BST<>();
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
    
    // Parse header
    String[] headers = lines[0].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    int categoryIndex = -1, nameIndex = -1, skuIndex  = -1, priceIndex = -1, ratailIndex = -1;
    for (int i = 0; i < headers.length; i++) {
        String header = headers[i].trim().toLowerCase();
        //System.out.println("Header " + i + ": " + header);
        switch (header) {
            case "CATEGORY" -> categoryIndex = i;
            case "PRODUCT NAME" -> nameIndex = i;
            case "SKU" -> skuIndex = i;
            case "PRICE CURRENT" -> priceIndex = i;
            case "PRICE RETAIL" -> ratailIndex = i;
            default -> {
            }
        }
    }
    
    if (categoryIndex == -1 || nameIndex == -1 || skuIndex == -1 || priceIndex == -1 || ratailIndex == -1) {
        System.out.println("File missing required columns. nameIndex: " + categoryIndex + nameIndex + skuIndex + priceIndex + ratailIndex);
        return;
    }
    
    // Clear any existing data.
    homeAppliaceMap.clear();
    
    // Process each record.
    for (int i = 1; i < lines.length; i++) {
        String line = lines[i];
        // Use regex-based split for the entire line.
        String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        if (parts.length <= Math.max(categoryIndex, Math.max(nameIndex, Math.max(skuIndex, Math.max(priceIndex, ratailIndex))))) {
            System.out.println("Skipping line " + i + ": insufficient parts.");
            continue;
        }
        String category = parts[categoryIndex].trim();
        String name = parts[nameIndex].trim();
        String sku = parts[skuIndex].trim();
        float price = Float.parseFloat(parts[priceIndex].trim());
        float retail = Float.parseFloat(parts[ratailIndex].trim());
        HomeApplianceRecord record = new HomeApplianceRecord(sku, retail, price, name, category);
        homeAppliaceMap.put(sku, record);
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchBySku'");
    }

    @Override
    public String getAllHomeAppliaceRecord() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllHomeAppliaceRecord'");
    }
    
}
