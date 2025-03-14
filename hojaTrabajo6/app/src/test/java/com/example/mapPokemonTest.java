package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.example.factory.MappingType;


public class mapPokemonTest {
    // Test for the PokemonRecord class
    // @Test
    // public void testPokemonRecord() {
    //     PokemonRecord pokemon = new PokemonRecord("Pikachu", "Static", "Electric");
    //     assertEquals("Pikachu", pokemon.getName());
    //     assertEquals("Static", pokemon.getAbility());
    //     assertEquals("Electric", pokemon.getType1());
    //     assertEquals("Pokemon [name : Pikachu, type1 : Electric]", pokemon.toString());
    //     assertEquals("Pokemon [name : Pikachu, type1 : Electric, ability : Static]", pokemon.toStringWithAbility());
    // }
    // Test for the MappingType class
    @Test
    public void testMappingType() {
        assertEquals(MappingType.HASH_MAP, MappingType.valueOf("HASH_MAP"));
        assertEquals(MappingType.LINKED_HASH_MAP, MappingType.valueOf("LINKED_HASH_MAP"));
        assertEquals(MappingType.TREE_MAP, MappingType.valueOf("TREE_MAP"));
    }
}
