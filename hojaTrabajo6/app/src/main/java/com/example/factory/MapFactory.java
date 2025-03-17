package com.example.factory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.example.mappokemon.PokemonRecord;



public class MapFactory {
    /**
     * Factory method to create an instance of Map<String, PokemonRecord>
     * based on the given MappingType.
     * 
     * @param type the MappingType to create the map
     * @return the map instance
     * @throws IllegalArgumentException if type is null
     */
    public static  Map<String, PokemonRecord> getMap(MappingType type) {
        if (type == null) {
            throw new IllegalArgumentException("Can't find any Mapping type. Check for a null pointer exception.");
        }
        return switch (type) {
            case TREE_MAP -> new TreeMap<>();
            case LINKED_HASH_MAP -> new LinkedHashMap<>();
            case HASH_MAP -> new HashMap<>();
            default -> new HashMap<>();
        };
    }
}
