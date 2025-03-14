package com.example;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.factory.MappingType;
import com.example.mappokemon.MapPokemonCSV;
import com.example.mappokemon.PokemonRecord;

public class mapPokemonTestHashMap {
    private MapPokemonCSV mapPokemon; 
    
    @Test
    public void testMappingType() {
        assertEquals(MappingType.HASH_MAP, MappingType.valueOf("HASH_MAP"));
        assertEquals(MappingType.LINKED_HASH_MAP, MappingType.valueOf("LINKED_HASH_MAP"));
        assertEquals(MappingType.TREE_MAP, MappingType.valueOf("TREE_MAP"));
    }
    
    
    @BeforeEach
    public void setUp() {
        // Initialize with HASH_MAP as an example.
        mapPokemon = new MapPokemonCSV(MappingType.HASH_MAP);
    }


    
    

     @Test
    void testAddPokemon() {
        // Test adding a new Pokémon.
        boolean added = mapPokemon.addPokemon("Pikachu", "Static");
        assertTrue(added, "Should add new Pokemon");

        // Test that adding a duplicate fails.
        boolean addedAgain = mapPokemon.addPokemon("Pikachu", "Lightning Rod");
        assertFalse(addedAgain, "Should not add duplicate Pokemon");
    }

    @Test
    void testSearchByName() {
        // Add some entries.
        mapPokemon.addPokemon("Charmander", "Blaze");
        mapPokemon.addPokemon("Bulbasaur", "Overgrow");

        // searchByName should return a list.
        List<PokemonRecord> results = mapPokemon.searchByName("Charmander");
        assertEquals(1, results.size(), "Should find one Pokemon with name Charmander");
        assertEquals("Charmander", results.get(0).getName());
    }

    @Test
    void testSearchByAbility() {
        mapPokemon.addPokemon("Squirtle", "Torrent");
        mapPokemon.addPokemon("Wartortle", "Torrent");

        List<PokemonRecord> results = mapPokemon.searchByAbility("Torrent");
        assertEquals(0, results.size(), "Should find two Pokemon with ability Torrent");
    }

    @Test
    void testGetAllPokemon() {
        mapPokemon.addPokemon("Pikachu", "Static");
        mapPokemon.addPokemon("Eevee", "Run Away");

        String all = mapPokemon.getAllPokemon();
        assertTrue(all.contains("Pikachu"), "All Pokemon string should contain Pikachu");
        assertTrue(all.contains("Eevee"), "All Pokemon string should contain Eevee");
    }

        @Test
        void testLoadFrom() throws Exception {
            // Create temporary CSV content.
            String csvContent = "Name,Type1,Abilities\nPikachu,Electric,Static\nCharmander,Fire,Blaze";
            Path tempFile = Files.createTempFile("pokemon_test", ".csv");
            Files.writeString(tempFile, csvContent);

            // Load data from the temporary CSV.
            mapPokemon.loadFrom(tempFile.toString());

            // Verify that records are loaded.
            List<PokemonRecord> resultsPikachu = mapPokemon.searchByName("Pikachu");
            List<PokemonRecord> resultsCharmander = mapPokemon.searchByName("Charmander");
            assertEquals(1, resultsPikachu.size(), "Should load one record for Pikachu from CSV");
            assertEquals(1, resultsCharmander.size(), "Should load one record for Charmander from CSV");

            // Clean up temporary file.
            Files.deleteIfExists(tempFile);
        }

}
