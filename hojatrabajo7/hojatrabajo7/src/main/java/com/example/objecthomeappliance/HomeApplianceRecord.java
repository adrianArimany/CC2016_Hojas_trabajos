package com.example.objecthomeappliance;

public class HomeApplianceRecord {
    private final String sku;
    private final float price_retail;
    private final float price_current;
    private final String product_name;
    private final String category;


    public HomeApplianceRecord(String sku, float price_retail, float price_current, String product_name, String category) {
        this.sku = sku;
        this.price_retail = price_retail;
        this.price_current = price_current;
        this.product_name = product_name;
        this.category = category;
    }


    public String getSKU() {
        return sku;
    }

    public float getPriceRetail() {
        return price_retail;
    }
    public float getPriceCurrent() {
        return price_current;
    }

    public String getProductName() {
        return product_name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Home appliance [Category : " + category 
        + ", product name:  " + product_name 
        + " , sku: " + sku 
        + ", price current: " + price_current 
        + ", price retail: " + price_retail 
        + "= ]";
    }
}
