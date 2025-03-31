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


/**
 * Retrieves the SKU (Stock Keeping Unit) of the home appliance.
 *
 * @return the SKU code associated with this home appliance record
 */
    public String getSKU() {
        return sku;
    }

    /**
     * Retrieves the retail price of the home appliance.
     * 
     * @return the retail price associated with this home appliance record
     */
    public float getPriceRetail() {
        return price_retail;
    }
    /**
     * Retrieves the current price of the home appliance.
     * 
     * @return the current price associated with this home appliance record
     */
    public float getPriceCurrent() {
        return price_current;
    }

    /**
     * Retrieves the product name of the home appliance.
     *
     * @return the product name associated with this home appliance record
     */
    public String getProductName() {
        return product_name;
    }

    /**
     * Retrieves the category of the home appliance.
     *
     * @return the category associated with this home appliance record
     */
    public String getCategory() {
        return category;
    }

    /**
     * Provides a string representation of the HomeApplianceRecord.
     * 
     * This method returns a string containing the SKU, product name, category,
     * current price, and retail price of the home appliance record.
     * 
     * @return a string representation of the home appliance record
     */
    @Override
    public String toString() {
        return "Home appliance [Sku : " + sku 
        + ", price current: " + price_current 
        + " , Category: " + category 
        + ", product name:  " + product_name 
        + ", price retail: " + price_retail 
        + " ]";
    }
}
