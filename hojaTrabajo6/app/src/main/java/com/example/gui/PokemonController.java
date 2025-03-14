package com.example.gui;

import com.example.gui.PokemonModel.Pokemon;

public class PokemonController {
    private final PokemonModel model;
    private final PokemonView view;
    
    public PokemonController(PokemonModel model, PokemonView view) {
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
            Pokemon pokemon = model.searchByName(name);
            if (pokemon != null) {
                view.showSearchResult(pokemon.toString());
            } else {
                view.showSearchResult("No Pokemon found with name: " + name);
            }
        });
        
        // Listener for searching by ability.
        view.addSearchByAbilityListener((ability) -> {
            String result = model.searchByAbility(ability);
            if (result.isEmpty()) {
                view.showSearchResult("No Pokemon found with ability: " + ability);
            } else {
                view.showSearchResult(result);
            }
        });
        
        // Listener for refreshing the list.
        view.addRefreshListListener(() -> {
            String all = model.getAllPokemon();
            view.updatePokemonList(all);
        });
    }
}
