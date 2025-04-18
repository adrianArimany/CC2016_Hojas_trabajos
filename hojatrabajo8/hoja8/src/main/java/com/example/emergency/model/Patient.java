package com.example.emergency.model;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

public class Patient implements Comparable<Patient> {
    // for FIFO among same‑priority
    private static final AtomicLong SEQUENCE = new AtomicLong(0);

    private final long arrivalSeq;
    private final String name;
    private final String symptom;
    private final char code; // 'A' (highest) … 'E' (lowest)

    public Patient(String name, String symptom, char code) {
        this.name    = Objects.requireNonNull(name, "name");
        this.symptom = Objects.requireNonNull(symptom, "symptom");
        if (code < 'A' || code > 'E') {
            throw new IllegalArgumentException("Emergency code must be between A and E");
        }
        this.code = code;
        this.arrivalSeq = SEQUENCE.getAndIncrement();
    }

    
    /**
     * Factory method to create a new Patient from a CSV string.
     * Expects a string of the form "name,symptom,code" where
     * <ul>
     * <li>name is the patient's name</li>
     * <li>symptom is the symptom or diagnosis</li>
     * <li>code is a character between 'A' and 'E' indicating
     * the priority of the patient</li>
     * </ul>
     * @param line the CSV string
     * @return a new Patient
     * @throws IllegalArgumentException if the input string is not valid
     */
    public static Patient fromCsv(String line) {
        String[] parts = line.split("\\s*,\\s*");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid record: " + line);
        }
        String nm = parts[0];
        String sym = parts[1];
        char cd = parts[2].charAt(0);
        return new Patient(nm, sym, cd);
    }

    public String getName()    { return name; }
    public String getSymptom() { return symptom; }
    public char   getCode()    { return code; }

    /**
     * Compares this Patient with another based on priority code and then on
     * arrival sequence.
     * <ul>
     * <li>Patients with the same code are compared by arrival sequence</li>
     * <li>If the codes are different, the highest priority code is first</li>
     * </ul>
     * @param other the other Patient to compare
     * @return a negative integer, zero, or a positive integer as this Patient is
     * less than, equal to, or greater than the other
     */
    @Override
    public int compareTo(Patient other) {
        // A < B < C … so 'A' is highest priority
        int cmp = Character.compare(this.code, other.code);
        if (cmp != 0) return cmp;
        return Long.compare(this.arrivalSeq, other.arrivalSeq);
    }

    /**
     * Converts this Patient to a string of the form
     * <i>name</i>, <i>symptom</i>, <i>code</i>
     * @return a string representation of this Patient
     */
    @Override
    public String toString() {
        return String.format("%s, %s, %c", name, symptom, code);
    }
}