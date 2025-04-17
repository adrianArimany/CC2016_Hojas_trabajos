package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.example.emergency.model.EmergencyCareSystem;
import com.example.emergency.model.Patient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException
    {
        EmergencyCareSystem sys = new EmergencyCareSystem();
    
        // read file “pacientes.txt”
        try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            Patient p = Patient.fromCsv(line);
            sys.admit(p);
        }
    } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
    }
    
        // when doctor asks:
        Patient next = sys.nextPatient();
        if (next != null) {
            System.out.println("Next up: " + next);
        } else {
            System.out.println("No more patients.");    
        }
    }
}
