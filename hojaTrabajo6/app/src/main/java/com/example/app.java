package com.example;
import com.example.data.DataHandler;
import com.example.data.FileHandler;
import com.example.factory.MappingType;
import com.example.gui.PokemonController;
import com.example.gui.PokemonView;
import com.example.mappokemon.MapPokemon;

public class app {
    public static void main(String[] args) {
        // Initialize the data directory.
        DataHandler.initialize();

        // Step 1: File selection.
        PokemonView tempView = new PokemonView();
        String filePath = tempView.showFileSelection();
        if (filePath != null) {
            String fileContent = FileHandler.readFile(filePath);
            System.out.println("File content loaded");
        }
        
        // Step 2: Mapping selection.
        PokemonView.MappingSelectionResult mappingResult = tempView.showMappingSelection();
        MappingType mappingType;
        switch(mappingResult.getMappingType()) {
            case "Tree Map":
                mappingType = MappingType.TREE_MAP;
                break;
            case "Linked Hash Map":
                mappingType = MappingType.LINKED_HASH_MAP;
                break;
            case "Hash Map":
            default:
                mappingType = MappingType.HASH_MAP;
                break;
        }
        
        // Initialize the model using our new MapPokemon.
        MapPokemon model = new MapPokemon(mappingType);
        if (filePath != null) {
            model.loadFrom(filePath);
        }
        
        // Create the main view and controller.
        PokemonView mainView = new PokemonView();
        PokemonController controller = new PokemonController(model, mainView);
        
        // Show the main program window.
        mainView.showMainWindow();
    }
}
