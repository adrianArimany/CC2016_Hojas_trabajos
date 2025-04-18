package com.example.emergency.model;

import com.example.ISystem;
import com.example.emergency.comparator.PatientComparator;
import com.example.emergency.heap.HeapUsingIterativeBinaryTree;

// …

public class EmergencyCareSystem implements ISystem<Patient>  {
    private final HeapUsingIterativeBinaryTree<Patient, Patient> queue;

    public EmergencyCareSystem() {
        // both priority and value are the same Patient object
        this.queue = new HeapUsingIterativeBinaryTree<>(
            new PatientComparator()
        );
    }

    
    /**
     * Enqueue a new patient. 
     * 
     * @param p the new patient.
     */
    @Override
    public void admit(Patient p) {
        queue.Insert(p, p);
    }

    
    /**
     * Dequeue the highest-priority patient, or null if none.
     * 
     * @return the highest-priority patient, or null if none.
     */
    @Override
    public Patient nextPatient() {
        return queue.remove();
    }
}
