package com.example.emergency.comparator;

import java.util.Comparator;

import com.example.emergency.model.Patient;

public class PatientComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient p1, Patient p2) {
        // we want p1 to be considered “greater” if p1 has higher priority than p2
        // so just flip the sign of Patient.compareTo:
        return - p1.compareTo(p2);
    }
}