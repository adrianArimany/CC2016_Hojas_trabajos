package com.example.objecthomeappliance;

import java.util.List;

public interface  Isku {
    void loadFrom(String filePath); 
    void saveTo(String filePath); 
    List<HomeApplianceRecord> searchBySku(String skuQuery); //Searches the home appliace by the SKU code
    String getAllHomeAppliaceRecord(); 

}
