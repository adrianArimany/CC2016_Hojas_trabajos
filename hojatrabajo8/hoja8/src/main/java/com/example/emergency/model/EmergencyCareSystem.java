package com.example.emergency.model;

import com.example.emergency.comparator.PatientComparator;
import com.example.emergency.heap.HeapUsingIterativeBinaryTree;

// …

public class EmergencyCareSystem {
    private final HeapUsingIterativeBinaryTree<Patient, Patient> queue;

    public EmergencyCareSystem() {
        // both priority and value are the same Patient object
        this.queue = new HeapUsingIterativeBinaryTree<>(
            new PatientComparator()
        );
    }

    /** Enqueue a new patient */
    public void admit(Patient p) {
        queue.Insert(p, p);
    }

    /** Fetch next patient (or null if none) */
    public Patient nextPatient() {
        return queue.remove();
    }
}
