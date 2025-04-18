package com.example;

/**
 * Interface for a system that manages patients.
 *
 * @param <T> the type of patient
 */

public interface ISystem<T> {
    void admit(T patient);
    T nextPatient();
}