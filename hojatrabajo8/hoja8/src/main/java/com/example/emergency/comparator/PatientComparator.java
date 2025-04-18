package com.example.emergency.comparator;

import java.util.Comparator;

import com.example.emergency.model.Patient;

public class PatientComparator implements Comparator<Patient> {
/**
 * Compares two patients for ordering based on their priority.
 * The patient with a higher priority code (closer to 'A')
 * is considered greater. If two patients have the same priority
 * code, the one with the earlier arrival sequence is considered
 * greater.
 *
 * @param p1 the first patient to compare
 * @param p2 the second patient to compare
 * @return a negative integer if p1 is greater (i.e., higher priority) than p2,
 *         zero if they are equal, or a positive integer if p1 is less than p2
 */

    @Override
    public int compare(Patient p1, Patient p2) {
        // we want p1 to be considered “greater” if p1 has higher priority than p2
        // so just flip the sign of Patient.compareTo:
        return - p1.compareTo(p2);
    }
}