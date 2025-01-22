package com.example.Data;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private final List<Double> volumeList = new ArrayList<>();

    private static final Logger logger = Logger.getLogger(LiquadoraData.class.getName());  


    public LiquadoraData() {
        loadFromJson();
    }

    public Map<Integer, String> getSpeedMap() {
        return speedMap;
    }

    public List<Double> getVolumeList() {
        return volumeList;
    }

    public List<Double> setVolumeList(double volumeList) throws IOException {
        this.volumeList.clear();
        this.volumeList.add(Double.valueOf(volumeList));
        return this.volumeList;
    }

    public void deleteVolume(Double material) throws IOException {
        volumeList.remove(material);
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
