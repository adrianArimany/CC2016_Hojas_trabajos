package com.example.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConfig {
    private int velocidadActual;
    private double maxCapacity;
    private static final Logger logger = Logger.getLogger(LiquadoraData.class.getName());
    private String fileCofig = getClass().getClassLoader().getResource("com/example/properties/config.properties").getPath();
    
    public AppConfig() {
        try (FileInputStream fis = new FileInputStream(fileCofig)) {
            Properties prop = new Properties();
            prop.load(fis);

            this.velocidadActual = Integer.parseInt(prop.getProperty("velocidadActual"));
            this.maxCapacity = Double.parseDouble(prop.getProperty("maxCapacity"));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error loading config file", e);
        }
    }

    /**
     * Load the properties from the "liquadora.properties" file.
     * 
     * The file is expected to be in the classpath.
     * 
     * @return the loaded Properties object.
     */
    public Properties loadProperties() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("liquadora.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to load properties file", e);
        }
        return prop;
    }


    /**
     * Retrieves the current speed setting of the liquadora.
     *
     * @return the current speed level as an integer.
     */

    public int getVelocidadActual() {
        return velocidadActual;
    }

    /**
     * Retrieves the maximum capacity of the liquadora.
     * 
     * @return the maximum capacity of the liquadora as a double.
     */
    public double getMaxCapacity() {
        return maxCapacity;
    }   












}
