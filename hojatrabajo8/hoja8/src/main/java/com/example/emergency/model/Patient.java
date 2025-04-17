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

    /** Parse a line “Name, symptom, Code” (commas optional spaces) */
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

    @Override
    public int compareTo(Patient other) {
        // A < B < C … so 'A' is highest priority
        int cmp = Character.compare(this.code, other.code);
        if (cmp != 0) return cmp;
        return Long.compare(this.arrivalSeq, other.arrivalSeq);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %c", name, symptom, code);
    }
}