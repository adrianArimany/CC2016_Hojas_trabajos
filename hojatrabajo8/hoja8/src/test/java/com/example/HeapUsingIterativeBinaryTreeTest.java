package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.emergency.comparator.PatientComparator;
import com.example.emergency.heap.HeapUsingIterativeBinaryTree;
import com.example.emergency.heap.IHeap;
import com.example.emergency.model.Patient;

class HeapUsingIterativeBinaryTreeTest {

    @Test
    void testEmptyHeap() {
        IHeap<Patient,Patient> heap = new HeapUsingIterativeBinaryTree<>(new PatientComparator());
        assertTrue(heap.isEmpty(), "New heap should be empty");
        assertEquals(0, heap.count(), "Count of new heap should be 0");
        assertNull(heap.get(), "get() on empty should return null");
        assertNull(heap.remove(), "remove() on empty should return null");
    }

    @Test
    void testSingleInsertAndRemove() {
        IHeap<Patient,Patient> heap = new HeapUsingIterativeBinaryTree<>(new PatientComparator());
        Patient p = Patient.fromCsv("John Doe, headache, C");

        heap.Insert(p, p);
        assertFalse(heap.isEmpty(), "Heap should no longer be empty after insert");
        assertEquals(1, heap.count(), "Count should be 1 after one insert");
        assertEquals(p, heap.get(), "get() should return the inserted patient");

        Patient removed = heap.remove();
        assertEquals(p, removed, "remove() should return the same patient");
        assertTrue(heap.isEmpty(), "Heap should be empty after removing the only element");
    }

    @Test
    void testOrderingMultiplePatients() {
        IHeap<Patient,Patient> heap = new HeapUsingIterativeBinaryTree<>(new PatientComparator());

        // Create four patients with codes C, A, E, B
        Patient pC = Patient.fromCsv("Juan Perez, leg fracture, C");
        Patient pA = Patient.fromCsv("Maria Ramirez, appendicitis, A");
        Patient pE = Patient.fromCsv("Lorenzo Toledo, chikungunya, E");
        Patient pB = Patient.fromCsv("Carmen Sarmientos, labor pains, B");

        // Insert in “random” order
        heap.Insert(pC, pC);
        heap.Insert(pA, pA);
        heap.Insert(pE, pE);
        heap.Insert(pB, pB);

        // Removal order must be A → B → C → E
        assertEquals(pA, heap.remove(), "First out should be A");
        assertEquals(pB, heap.remove(), "Second out should be B");
        assertEquals(pC, heap.remove(), "Third out should be C");
        assertEquals(pE, heap.remove(), "Fourth out should be E");

        assertTrue(heap.isEmpty(), "Heap should be empty after removing all");
    }
}
