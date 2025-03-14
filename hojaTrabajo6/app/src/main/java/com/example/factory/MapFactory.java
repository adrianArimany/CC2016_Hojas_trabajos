package com.example.factory;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import com.example.gui.PokemonModel.MappingType;
import com.example.mappokemon.PokemonRecord;


public class MapFactory {
    public static  Map<String, PokemonRecord> getMap(MappingType type) {
        if (type == null) {
            throw new IllegalArgumentException("Can't find any Mapping type. Check for a null pointer exception.");
        }
        switch (type) {
            case TREE_MAP:
                return new TreeMap<>();
            case LINKED_HASH_MAP:
                return new LinkedHashMap<>();
            case HASH_MAP:
            default:
                return new HashMap<>();
        }
    }
}
