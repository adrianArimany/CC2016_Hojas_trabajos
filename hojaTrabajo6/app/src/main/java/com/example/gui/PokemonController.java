package com.example.gui;

import java.util.List;

import com.example.mappokemon.Ipokemon;
import com.example.mappokemon.PokemonRecord;



public class PokemonController {
    private final Ipokemon model;
    private final PokemonView view;
    
    public PokemonController(Ipokemon model, PokemonView view) {
        this.model = model;
        this.view = view;
        initController();
    }
    
    private void initController() {
        // Listener for adding a Pokemon.
        view.addAddPokemonListener((name, ability) -> {
            if (name.isEmpty() || ability.isEmpty()) {
                view.showMessage("Please provide both a name and an ability.");
            } else if (!model.addPokemon(name, ability)) {
                view.showMessage("Error: Pokemon with name " + name + " already exists.");
            } else {
                view.showMessage("Pokemon added: " + name);
            }
        });
        
        // Listener for searching by name.
        view.addSearchByNameListener((name) -> {
            List<PokemonRecord> results = model.searchByName(name);
            if (results.isEmpty()) {
                view.showSearchResult("No Pokemon found with name: " + name);
            } else {
                // For simplicity, display all matching records.
                StringBuilder sb = new StringBuilder();
                for (PokemonRecord record : results) {
                    sb.append(record.toString()).append("\n");
                }
                view.showSearchResult(sb.toString());
            }
        });
        
        // Listener for searching by ability.
        view.addSearchByAbilityListener((ability) -> {
            List<PokemonRecord> results = model.searchByAbility(ability);
            if (results.isEmpty()) {
                view.showSearchResult("No Pokemon found with ability: " + ability);
            } else {
                StringBuilder sb = new StringBuilder();
                for (PokemonRecord record : results) {
                    sb.append(record.toStringWithAbility()).append("\n");
                }
                view.showSearchResult(sb.toString());
            }
        });
        
        // Listener for refreshing the list.
        view.addRefreshListListener(() -> {
            String all = model.getAllPokemon();
            view.updatePokemonList(all);
        });
    }
}
