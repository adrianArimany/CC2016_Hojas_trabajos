package com.example.gui;


import java.util.List;

import com.example.objecthomeappliance.HomeApplianceRecord;
import com.example.objecthomeappliance.Isku;



public class GuiController {
    private final Isku model;
    private final GuiView view;
    
    public GuiController(Isku model, GuiView view) {
        this.model = model;
        this.view = view;
        initController();
        //view.updatePokemonList(model.getAllPokemon());
    }
    
    
    /**
     * Initializes the controller by adding listeners to the view.
     * 
     * Adds a listener to the view for searching by SKU. When a search is performed,
     * the model is queried and the results are displayed in the view. If no results
     * are found, a message is displayed indicating that no Home Appliances were found
     * with the given SKU.
     * 
     * Also adds a listener to the view for refreshing the list. When the list is refreshed,
     * the model is queried and the results are displayed in the view.
     */
    private void initController() {
        // Listener for searching by SKU.
        view.addSearchBySkuListener((sku) -> {
            List<HomeApplianceRecord> results = model.searchBySku(sku);
            if (results.isEmpty()) {
                view.showSearchResult("No Home Appliance found with SKU: " + sku);
            } else {
                StringBuilder sb = new StringBuilder();
                for (HomeApplianceRecord record : results) {
                    sb.append(record.toString()).append("\n");
                }
                view.showSearchResult(sb.toString());
            }
        });
        
        // Listener for refreshing the list.
        view.addRefreshListListener(() -> {
            view.updateSkuList(model.getAllHomeAppliaceRecord());
        });
    }
    
}
