package com.example.mappokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.data.FileHandler;
import com.example.factory.MappingType;



public class MapPokemonCSV implements Ipokemon{ 

    private final Map<String, PokemonRecord> pokemonMap;

    public MapPokemonCSV(MappingType mappingType) {
        pokemonMap = com.example.factory.MapFactory.getMap(mappingType); 
    }

    /**
     * Adds a Pokemon to the data.
     * 
     * @param name the name of the Pokemon to add.
     * @param Type1 the type of the Pokemon to add.
     * @return true if the Pokemon was successfully added, false if a Pokemon with the given name already exists.
     */
    @Override
    public boolean addPokemon(String name, String Type1) {
        if (pokemonMap.containsKey(name)) {
            return false;
        }
        // When adding, you may choose how to set Type1.
        pokemonMap.put(name, new PokemonRecord(name, Type1, ""));
        return true;
    }

    /**
     * Searches for Pokemon by name.
     * 
     * @param nameQuery the name of the Pokemon to search for.
     * @return a list of PokemonRecord objects that match the name query. The list is sorted by Type1.
     */
    @Override
    public List<PokemonRecord> searchByName(String nameQuery) {
        return pokemonMap.values().stream()
                .filter(record -> record.getName().equalsIgnoreCase(nameQuery))
                .sorted(Comparator.comparing(PokemonRecord::getType1))
                .collect(Collectors.toList());
    }

    
    /**
    * Searches for Pokemon by ability.
    * 
    * @param abilityQuery the ability of the Pokemon to search for.
    * @return a list of PokemonRecord objects that match the ability query. The list is sorted by Type1.
    */
    @Override
    public List<PokemonRecord> searchByAbility(String abilityQuery) {
        return pokemonMap.values().stream()
                .filter(record -> record.getAbilities().stream()
                        .anyMatch(ability -> ability.equalsIgnoreCase(abilityQuery)))
                .sorted(Comparator.comparing(PokemonRecord::getType1))
                .collect(Collectors.toList());
    }
    
    /**
     * Retrieves all Pokemon in the map as a string. Each PokemonRecord is displayed
     * on a single line, with its fields separated by commas.
     * 
     * @return a string containing all Pokemon in the map.
     */
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
     * 
     * Heavily assited by CHATGPT, I really really hate csv files.
     * 
     */ 
    @Override
    public void loadFrom(String filePath) {
    String fileContent = FileHandler.readFile(filePath);
    if (fileContent == null || fileContent.isEmpty()) {
        System.out.println("File is empty or could not be read.");
        return;
    }
    
    // Split on both Windows and Unix line breaks.
    String[] lines = fileContent.split("\\r?\\n");
    System.out.println("Total lines read: " + lines.length);
    if (lines.length < 2) {
        System.out.println("File has no data.");
        return;
    }
    
    // Parse header
    String[] headers = lines[0].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    int nameIndex = -1, type1Index = -1, abilityIndex = -1;
    for (int i = 0; i < headers.length; i++) {
        String header = headers[i].trim().toLowerCase();
        //System.out.println("Header " + i + ": " + header);
        switch (header) {
            case "name" -> nameIndex = i;
            case "type1" -> type1Index = i;
            case "abilities", "ability" -> abilityIndex = i;
            default -> {
            }
        }
    }
    
    if (nameIndex == -1 || type1Index == -1 || abilityIndex == -1) {
        System.out.println("File missing required columns. nameIndex: " + nameIndex +
                           ", type1Index: " + type1Index +
                           ", abilityIndex: " + abilityIndex);
        return;
    }
    
    // Clear any existing data.
    pokemonMap.clear();
    
    // Process each record.
    for (int i = 1; i < lines.length; i++) {
        String line = lines[i];
        // Use regex-based split for the entire line.
        String[] parts = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        if (parts.length <= Math.max(nameIndex, Math.max(type1Index, abilityIndex))) {
            System.out.println("Skipping line " + i + ": insufficient parts.");
            continue;
        }
        String name = parts[nameIndex].trim();
        String type1 = parts[type1Index].trim();
        String abilityStr = parts[abilityIndex].trim();
        // Remove surrounding quotes if present.
        if (abilityStr.startsWith("\"") && abilityStr.endsWith("\"")) {
            abilityStr = abilityStr.substring(1, abilityStr.length() - 1);
        }
        //System.out.println("Line " + i + " - Name: " + name + ", Type1: " + type1 + ", Ability: " + abilityStr);
        pokemonMap.put(name, new PokemonRecord(name, type1, abilityStr));
    }
}


    /**
     * Saves the Pokemon data to a file in CSV format.
     * 
     * @param fileName The name of the file to be created or overwritten.
     * The file is created in the subdirectory "updates" under the directory specified by POKEMONDATA_DIR.
     * If the subdirectory does not exist, it is created. If the file already exists, it is overwritten.
     * The file content is in the format of "Name,Type1,Abilities", with each line representing a PokemonRecord.
     * The abilities are comma-separated if there are multiple abilities.
     */
    @Override
    public void saveTo(String fileName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name,Type1,Abilities\n"); 
        pokemonMap.values().forEach(record -> {
            sb.append(record.getName()).append(",")
              .append(record.getType1()).append(",")
              .append(record.getAbilities()).append("\n");
        });
        com.example.data.FileHandler.writeResult(fileName, sb.toString());
    }




}
