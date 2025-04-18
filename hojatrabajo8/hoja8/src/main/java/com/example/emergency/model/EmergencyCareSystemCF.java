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

    /** Enqueue a new patient. */
    @Override
    public void admit(Patient p) {
        queue.offer(p);
    }

    /** Dequeue the highest‐priority patient, or null if none. */
    @Override
    public Patient nextPatient() {
        return queue.poll();
    }

    /** True if no one’s waiting. */
    public boolean hasNext() {
        return !queue.isEmpty();
    }

}