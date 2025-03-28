package com.example.gui;


import java.util.List;

import com.example.objecthomeappliance.HomeApplianceRecord;
import com.example.searchstructure.Ibst;



public class GuiController {
    private final Ibst<String, String> model;
    private final GuiView view;
    
    public GuiController(Ibst<String, String> model, GuiView view) {
        this.model = model;
        this.view = view;
        initController();
        //view.updatePokemonList(model.getAllPokemon());
    }
    
    
    private void initController() {
        // Listener for searching by name.
        view.addSearchBySkuListener((name) -> {
            List<> results = model.searchBySku(name);
            if (results.isEmpty()) {
                view.showSearchResult("No Pokemon found with name: " + name);
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
            view.updateSkuList(model.getAllSku());
        });
    }
}
