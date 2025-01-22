package com.example.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    private final String LIQUADORA_VOLUME_PATH = getClass().getClassLoader().getResource("com/example/CSV/LiquadoraVolume.csv").getPath();
    private final Map<Integer, String> speedMap = new HashMap<>();
    private double volume;
    private static final Logger logger = Logger.getLogger(LiquadoraData.class.getName());  


    public LiquadoraData() {
        loadSpeedFromJson();
        loadVolumeFromCSV();
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
    public double getVolume() {
        return volume;
    }

    /**
     * Sets the volume level for the liquadora.
     *
     * @param newVolume the new volume level.
     * @throws IOException if there is an error writing the data to the CSV file.
     */
    public void setVolume(double newVolume) throws IOException {
        volume = newVolume;
        try {
            saveVolumeToCSV();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving volume data to CSV", e);
            throw e;
        }
    }

    /**
     * Removes a volume level from the liquadora.
     * 
     * @param material the material to be removed.
     * @throws IOException if there is an error writing the data to the JSON file.
     */
    public void deleteVolume() throws IOException {
        volume = 0.0;
        try {
            saveVolumeToCSV();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving volume data to CSV", e);
            throw e;
        }
    }

    /**
     * Loads the speed levels for the liquadora from the JSON file.
     *
     * If there is an error reading the JSON file, it logs a SEVERE message
     * with the error.
     */
    private void loadSpeedFromJson() {
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
    
    private void loadVolumeFromCSV() {
        File csvFile = new File(LIQUADORA_VOLUME_PATH);
        if (!csvFile.exists()) {
            logger.log(Level.WARNING, "CSV file does not exist: " + LIQUADORA_VOLUME_PATH);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String line = reader.readLine();
            if (line != null) {
                volume = Double.parseDouble(line);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading volume data from CSV", e);
        } catch (NumberFormatException e) {
            logger.log(Level.SEVERE, "Error parsing volume data from CSV", e);
        }
    }


    private void saveVolumeToCSV() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LIQUADORA_VOLUME_PATH))) {
            writer.write(String.valueOf(volume));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving volume data to CSV", e);
            throw e;
        }
    }
    

    
}

