package com.example.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;
import java.util.logging.Level;

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
            e.printStackTrace();
        }
    }

    public Properties loadProperties() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("liquadora.properties")) {
            prop.load(fis);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to load properties file", e);
        }
        return prop;
    }


    public int getVelocidadActual() {
        return velocidadActual;
    }

    public double getMaxCapacity() {
        return maxCapacity;
    }   












}
