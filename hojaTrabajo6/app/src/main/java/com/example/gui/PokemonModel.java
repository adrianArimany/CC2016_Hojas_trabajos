package com.example.gui;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PokemonModel {
    public enum MappingType {
        HASH_MAP, TREE_MAP, LINKED_HASH_MAP
    }
    
    private Map<String, Pokemon> pokemonMap;
    private MappingType mappingType;
    
    public PokemonModel(MappingType mappingType) {
        this.mappingType = mappingType;
        switch(mappingType) {
            case TREE_MAP:
                pokemonMap = new TreeMap<>();
                break;
            case LINKED_HASH_MAP:
                pokemonMap = new LinkedHashMap<>();
                break;
            case HASH_MAP:
            default:
                pokemonMap = new HashMap<>();
                break;
        }
    }
    
    public boolean addPokemon(String name, String ability) {
        if (pokemonMap.containsKey(name)) {
            return false; // Duplicate Pokémon not allowed.
        }
        pokemonMap.put(name, new Pokemon(name, ability));
        return true;
    }
    
    public Pokemon searchByName(String name) {
        return pokemonMap.get(name);
    }
    
    public String searchByAbility(String ability) {
        StringBuilder result = new StringBuilder();
        for (Pokemon p : pokemonMap.values()) {
            if (p.getAbility().equalsIgnoreCase(ability)) {
                result.append(p.toString()).append("\n");
            }
        }
        return result.toString();
    }
    
    public String getAllPokemon() {
        StringBuilder result = new StringBuilder();
        for (Pokemon p : pokemonMap.values()) {
            result.append(p.toString()).append("\n");
        }
        return result.toString();
    }
    
    // REMOVE ME WHEN MAPpokemon is finished.
    public static class Pokemon {
        private String name;
        private String ability;
        
        public Pokemon(String name, String ability) {
            this.name = name;
            this.ability = ability;
        }
        
        public String getName() {
            return name;
        }
        
        public String getAbility() {
            return ability;
        }
        
        @Override
        public String toString() {
            return "Name: " + name + ", Ability: " + ability;
        }
    }
}
