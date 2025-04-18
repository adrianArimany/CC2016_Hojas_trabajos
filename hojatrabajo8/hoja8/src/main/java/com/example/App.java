package com.example;

/**
 * Hello world!
 *
 */
// src/main/java/com/example/App.java

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.example.emergency.model.EmergencyCareSystem;
import com.example.emergency.model.EmergencyCareSystemCF;
import com.example.emergency.model.Patient;

public class App {
    public static void main(String[] args) {
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("=== Emergency Care System ===");
        System.out.println("Choose implementation:");
        System.out.println("  1) Java Collection-Framework (PriorityQueue)");
        System.out.println("  2) Custom heap (HeapUsingIterativeBinaryTree)");
        System.out.print("Enter choice [1 or 2]: ");

        EmergencyCareSystemCF cfSys = new EmergencyCareSystemCF();
        EmergencyCareSystem  customSys = new EmergencyCareSystem();
        ISystem<Patient> sys;  // a little interface both CF & custom implement

        try {
            String choice = console.readLine().trim();
            if ("2".equals(choice)) {
                sys = customSys;
                System.out.println("Using custom-heap version");
            } else {
                sys = cfSys;
                System.out.println("Using JCF PriorityQueue version");
            }

            // 1) load pacientes.txt
            try (BufferedReader br = new BufferedReader(new FileReader("pacientes.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    sys.admit(Patient.fromCsv(line));
                }
            } catch (IOException e) {
                System.err.println("Error reading pacientes.txt: " + e.getMessage());
                return;
            }

            // 2) interactive dequeue loop
            System.out.println("\nPress ENTER for next patient, or Q + ENTER to quit.");
            String in;
            while ((in = console.readLine()) != null) {
                if ("Q".equalsIgnoreCase(in.trim())) {
                    System.out.println("Goodbye.");
                    break;
                }
                Patient next = sys.nextPatient();
                if (next != null) {
                    System.out.println("Next patient: " + next);
                } else {
                    System.out.println("No more patients.");
                    break;
                }
            }

        } catch (IOException e) {
        }
    }
}

