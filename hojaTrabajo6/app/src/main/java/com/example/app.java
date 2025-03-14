package com.example;
import com.example.data.Config;
import com.example.data.DataHandler;
import static com.example.data.FileHandler.copyFile;
import com.example.factory.MappingType;
import com.example.gui.PokemonController;
import com.example.gui.PokemonView;
import com.example.mappokemon.MapPokemonCSV;

public class app {
    public static void main(String[] args) {
        // Initialize the data directory.
        DataHandler.initialize();

        // Step 1: File selection.
        PokemonView tempView = new PokemonView();
        String filePath = tempView.showFileSelection();
        if (filePath != null) {
            System.out.println("File content loaded");
            copyFile(filePath, Config.POKEMONDATA_DIR);
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
        MapPokemonCSV model = new MapPokemonCSV(mappingType);
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
