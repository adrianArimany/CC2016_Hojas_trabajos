package com.example;

import java.util.HashMap;

import com.example.datahandler.Config;
import static com.example.datahandler.FileHandler.copyFile;
import com.example.gui.GuiController;
import com.example.gui.GuiView;
import com.example.objecthomeappliance.HomeApplianceRecord;
import com.example.objecthomeappliance.Isku;
import com.example.objecthomeappliance.MapSkuCSV;

/**
 * @author Adrian Arimany 211063
 * @version  1.0
 * 
 */
public final class App {
    /**
     * Entry point of the program.
     * 
     * This method is the main entry point of the program. It displays a file
     * selection dialog to the user, and if a file is selected, it copies the
     * file to the Config.HOMEAPPLIACE_DIR directory and loads its content into
     * an Isku model. Then it creates a GuiView and a GuiController, and shows
     * the main window of the GuiView.
     */
    public static void main(String[] args) {
    GuiView tempView = new GuiView();
    String filePath = tempView.showFileSelection();
    if (filePath != null) {
        System.out.println("File content loaded");
        copyFile(filePath, Config.HOMEAPPLIACE_DIR);
    }
    @SuppressWarnings("Convert2Diamond")
    Isku model = new MapSkuCSV(new HashMap<String, HomeApplianceRecord>());
    if (filePath != null) {
        model.loadFrom(filePath);
    }
    
    GuiView mainView = new GuiView();
    @SuppressWarnings("unused")
    GuiController controller = new GuiController(model, mainView);
    
    mainView.showMainWindow();
    }
}    