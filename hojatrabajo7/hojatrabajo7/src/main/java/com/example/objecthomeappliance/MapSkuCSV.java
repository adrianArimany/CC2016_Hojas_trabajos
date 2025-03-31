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

    /**
     * Reads the content of a file specified by the file path into the
     * HomeApplianceRecord map and the BST data structure.
     * 
     * @param filePath The path of the file to be read.
     * @return The content of the file as a string. If the file path is null, 
     * returns "Error: File path is null". If an error occurs during reading, an empty string is returned.
     */
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
        String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        if (parts.length <= Math.max(categoryIndex, Math.max(nameIndex, Math.max(skuIndex, Math.max(priceIndex, retailIndex))))) {
            System.out.println("Skipping line " + i + ": insufficient parts.");
            continue;
        }
        String category = parts[categoryIndex].trim();
        String name = parts[nameIndex].trim();
        String sku = parts[skuIndex].trim();
        
        // Since some of the values for the price index and retail index are empty, we need to handle that case. 
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




    /**
     * Saves the home appliance data to a CSV file.
     * 
     * This method will write the contents of the homeAppliaceMap to a file
     * named by the given filename. The file will be written in a CSV format
     * with the following columns:
     * 
     * <ul>
     * <li>Category</li>
     * <li>Product Name</li>
     * <li>SKU</li>
     * <li>Price Current</li>
     * <li>Price Retail</li>
     * </ul>
     * 
     * @param fileName the name of the file to write to.
     */
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

/**
 * Searches for a home appliance record by its SKU.
 *
 * This method utilizes a binary search tree (BST) to efficiently locate
 * a home appliance record based on the provided SKU query. If a matching
 * record is found, it is added to the result list.
 *
 * @param skuQuery the SKU code to search for
 * @return a list containing the HomeApplianceRecord if found; otherwise, an empty list
 */
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

    /**
     * Retrieves all home appliance records as a string.
     * 
     * This method iterates over the homeAppliaceMap and appends each
     * HomeApplianceRecord's string representation to a StringBuilder.
     * 
     * @return a string containing all home appliance records, each separated by a newline.
     */
    @Override
    public String getAllHomeAppliaceRecord() {
        StringBuilder result = new StringBuilder();
        homeAppliaceMap.values().forEach(record -> result.append(record.toString()).append("\n"));
        return result.toString();
    }
    
}
