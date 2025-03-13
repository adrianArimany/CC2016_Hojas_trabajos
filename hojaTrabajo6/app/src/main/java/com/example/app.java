package com.example;



import com.example.data.DataHandler;
import com.example.data.FileHandler;
import com.example.gui.PokemonController;
import com.example.gui.PokemonModel;
import com.example.gui.PokemonModel.MappingType;
import com.example.gui.PokemonView;

public class app {
    public static void main(String[] args) {
        // Initialize the directory (using your DataHandler).
        DataHandler.initialize();
        
        // Step 1: File selection.
        PokemonView tempView = new PokemonView();
        String filePath = tempView.showFileSelection();
        if (filePath != null) {
            String fileContent = FileHandler.readFile(filePath);
            System.out.println("File content loaded: " + fileContent);
        }
        
        // Step 2: Mapping selection.
        PokemonView.MappingSelectionResult mappingResult = tempView.showMappingSelection();
        MappingType mappingType = MappingType.HASH_MAP; // default
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
        
        // Initialize the model with the chosen mapping type.
        PokemonModel model = new PokemonModel(mappingType);
        
        // Create the main view for the program.
        PokemonView mainView = new PokemonView();
        // Create the controller to connect the model and the view.
        PokemonController controller = new PokemonController(model, mainView);
        
        // Show the main program window.
        mainView.showMainWindow();
    }
}

