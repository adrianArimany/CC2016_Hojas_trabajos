package com.example.factory;

import com.example.gui.PokemonModel.MappingType;
import com.example.typemaps.Imaps;
import com.example.typemaps.typeHashMap;
import com.example.typemaps.typeLinkedHashMap;
import com.example.typemaps.typeTreeMap;

public class MapFactory {
    public static Imaps getMap(MappingType type) {
        if (type == null) {
            throw new IllegalArgumentException("Can't find any Mapping type. there is a null pointer exception");
        }
        switch (type) {
            case TREE_MAP:
                return new typeTreeMap();
            case LINKED_HASH_MAP:
                return new typeLinkedHashMap();
            case HASH_MAP:
                return new typeHashMap();
            default:
                return new typeHashMap();
        }
    }
}
