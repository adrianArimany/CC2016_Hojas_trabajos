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

/**
 * Returns the speed map for the liquadora.
 *
 * @return a map where the key is an integer representing the speed level
 *         and the value is a string describing the speed.
 */

    public Map<Integer, String> getSpeedMap() {
        return speedMap;
    }

    /**
     * Returns the list of volume levels for the liquadora.
     *
     * @return a list of doubles where each element represents the volume level.
     */
    public List<Double> getVolumeList() {
        return volumeList;
    }

    /**
     * Sets the volume list for the liquadora.
     *
     * @param volumeList the new volume level.
     * @return the updated volume list.
     * @throws IOException if there is an error writing the data to the JSON file.
     */
    public List<Double> setVolumeList(double volumeList) throws IOException {
        this.volumeList.clear();
        this.volumeList.add(Double.valueOf(volumeList));
        return this.volumeList;
    }

    /**
     * Removes a volume level from the liquadora.
     * 
     * @param material the material to be removed.
     * @throws IOException if there is an error writing the data to the JSON file.
     */
    public void deleteVolume(Double material) throws IOException {
        volumeList.remove(material);
    }

    /**
     * Loads the speed levels for the liquadora from the JSON file.
     *
     * If there is an error reading the JSON file, it logs a SEVERE message
     * with the error.
     */
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
