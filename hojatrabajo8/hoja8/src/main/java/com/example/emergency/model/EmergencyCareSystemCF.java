package com.example.emergency.model;

import java.util.PriorityQueue;

import com.example.ISystem;
/**
 * Emergency care system using java.util.PriorityQueue.
 */
public class EmergencyCareSystemCF implements ISystem<Patient> {
    private final PriorityQueue<Patient> queue;

    /** Uses Patient’s natural ordering (code 'A' is “smallest”). */
    public EmergencyCareSystemCF() {
        this.queue = new PriorityQueue<>();
    }

    
    /**
     * Admit a patient into the system.
     * 
     * @param p the patient to admit
     */
    @Override
    public void admit(Patient p) {
        queue.offer(p);
    }

   
    /**
     * Retrieve the next patient to be treated from the system.
     * 
     * @return the next patient, or null if the system is empty
     */
    @Override
    public Patient nextPatient() {
        return queue.poll();
    }


    /**
     * Is there another patient in the system to be treated?
     * 
     * @return true if there is another patient, false otherwise
     */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

}