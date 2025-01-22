package com.example.Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class LiquadoraData {
    private final String LIQUADORA_PATH = getClass().getClassLoader().getResource("com/example/JSON/liquadoraSpeed.json").getPath();
    private final Map<Integer, String> speedMap = new HashMap<>();
    private final Map<Double, Object> volumeMap = new HashMap<>();

    private static final Logger logger = Logger.getLogger(LiquadoraData.class.getName());  


    public LiquadoraData() {
        loadFromJson();
    }

    public Map<Integer, String> getSpeedMap() {
        return speedMap;
    }

    public Map<Double, Object> getVolumeMap() {
        return volumeMap;
    }

    public Map<Double, Object> setMaterialMap(Map<Double, Object> volumeMap) throws IOException {
        this.volumeMap.clear();
        this.volumeMap.putAll(volumeMap);
        return this.volumeMap;
    }

    public void deleteMaterial(Double material) throws IOException {
        volumeMap.remove(material);
    }

    private void loadFromJson() {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(LIQUADORA_PATH);
            java.lang.reflect.Type type = new TypeToken<Map<Integer, String>>() {}.getType();
            Map<Integer, String> loadedMap = gson.fromJson(reader, type);
            speedMap.clear();
            speedMap.putAll(loadedMap);
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException e) {
            logger.log(Level.SEVERE, "Error loading data from JSON", e);
        }
    }

    

    
}
