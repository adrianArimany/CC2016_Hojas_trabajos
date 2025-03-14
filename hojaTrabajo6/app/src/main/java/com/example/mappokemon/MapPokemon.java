package com.example.mappokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.factory.MappingType;



public class MapPokemon implements Ipokemon{ 

    private final Map<String, PokemonRecord> pokemonMap;

    public MapPokemon(MappingType mappingType) {
        pokemonMap = com.example.factory.MapFactory.getMap(mappingType); 
    }

    @Override
    public boolean addPokemon(String name, String ability) {
        if (pokemonMap.containsKey(name)) {
            return false;
        }
        // When adding, you may choose how to set Type1.
        pokemonMap.put(name, new PokemonRecord(name, "", ability));
        return true;
    }

    /**
    * Retrieves a PokemonRecord from the map based on the given name.
    *
    * @param name the name of the Pokemon to retrieve.
    * @return the PokemonRecord associated with the given name, or null if not found.
    */
    @Override
    public PokemonRecord getPokemon(String name) {
        return pokemonMap.get(name);
    }

    @Override
    public List<PokemonRecord> searchByName(String nameQuery) {
        return pokemonMap.values().stream()
                .filter(record -> record.getName().equalsIgnoreCase(nameQuery))
                .sorted(Comparator.comparing(PokemonRecord::getType1))
                .collect(Collectors.toList());
    }

    @Override
    public List<PokemonRecord> searchByAbility(String abilityQuery) {
        return pokemonMap.values().stream()
                .filter(record -> record.getAbility().equalsIgnoreCase(abilityQuery))
                .sorted(Comparator.comparing(PokemonRecord::getType1))
                .collect(Collectors.toList());
    }

    @Override
    public String getAllPokemon() {
        StringBuilder result = new StringBuilder();
        pokemonMap.values().forEach(record -> result.append(record.toString()).append("\n"));
        return result.toString();
    }



    /**
     * This method assumes that the file is a CSV file. 
     * It Reads a CSV file containing Pokemon data and loads it into the map.
     * 
     * 
     * @param filePath The path to the CSV file.
     * @throws IllegalArgumentException If the file is empty or cannot be read.
     * 
     *
     */ 
    @Override
    public void loadFrom(String filePath) {

        // Use FileHandler to read file content.
        String csvContent = com.example.data.FileHandler.readFile(filePath);
        if (csvContent == null || csvContent.isEmpty()) {
            System.out.println("CSV file is empty or could not be read.");
            return;
        }
        
        String[] lines = csvContent.split("\n");
        if (lines.length < 2) {
            System.out.println("CSV file has no data.");
            return;
        }
        
        // Determine column indices using the header line.
        String[] headers = lines[0].split(",");
        int nameIndex = -1, type1Index = -1, abilityIndex = -1;
        for (int i = 0; i < headers.length; i++) {
            String header = headers[i].trim().toLowerCase();
            if (header.equals("name")) {
                nameIndex = i;
            } else if (header.equals("type1")) {
                type1Index = i;
            } else if (header.equals("abilities")) {
                abilityIndex = i;
            }
        }
        
        if (nameIndex == -1 || type1Index == -1 || abilityIndex == -1) {
            System.out.println("CSV file missing required columns. Make sure the columns are named 'name', 'type1', and 'abilities'.");
            return;
        }
        
        // Process each line of CSV data.
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            if (parts.length <= Math.max(nameIndex, Math.max(type1Index, abilityIndex))) {
                continue;
            }
            String name = parts[nameIndex].trim();
            String type1 = parts[type1Index].trim();
            String ability = parts[abilityIndex].trim();
            // Add the record to the map.
            pokemonMap.put(name, new PokemonRecord(name, type1, ability));
        }
    }




}
