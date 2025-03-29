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
 * Hello world!
 */
public final class App {
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