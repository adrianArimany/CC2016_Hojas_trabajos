package com.example.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.logging.Level;
import java.io.FileReader;
import java.io.FileWriter;

public class LiquadoraData {
    private final String LIQUADORA_PATH = "/src/main/java/com/example/JSON/liquadoraSpeed.json";
    private final Map<Integer, String> speedMap = new HashMap<>();

    private static final Logger logger = Logger.getLogger(LiquadoraData.class.getName());  


    public LiquadoraData() {
        loadFromJson();
    }

    public Map<Integer, String> getSpeedMap() {
        return speedMap;
    }



    private void loadFromJson() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(LIQUADORA_PATH)) {
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            speedMap.clear();
            speedMap.putAll(loadedMap);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
    }

    private void saveToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(LIQUADORA_PATH)) {
            gson.toJson(speedMap, writer);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving data to JSON", e);
        }
    }
    
}
