package com.example;

import com.example.datahandler.Config;
import com.example.datahandler.DataHandler;
import static com.example.datahandler.FileHandler.copyFile;
import com.example.gui.GuiController;
import com.example.gui.GuiView;
import com.example.searchstructure.BST;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        // Initialize the data directory.
        DataHandler.initialize();

        // Step 1: File selection.
        GuiView tempView = new GuiView();
        String filePath = tempView.showFileSelection();
        if (filePath != null) {
            System.out.println("File content loaded");
            copyFile(filePath, Config.HOMEAPPLIACE_DIR);
        }
          
        // Initialize the model using our new csv file.
        BST<String, String> model = new BST<String, String>();
        if (filePath != null) {
            model.loadFrom(filePath);
        }
        
        // Create the main view and controller.
        GuiView mainView = new GuiView();
        GuiController controller = new GuiController(model, mainView);
        
        // Show the main program window.
        mainView.showMainWindow();
    }
}
