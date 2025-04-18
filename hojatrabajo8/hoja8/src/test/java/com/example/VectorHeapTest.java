package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

import com.example.emergency.heap.VectorHeap;
import com.example.emergency.model.Patient;

class VectorHeapTest {

    @Test
    void testEmptyHeap() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        assertTrue(heap.isEmpty(), "New heap should be empty");
        assertEquals(0, heap.size(), "Size of new heap should be 0");
        assertNull(heap.peek(), "peek() on empty should be null");
        assertNull(heap.remove(), "remove() on empty should be null");
    }

    @Test
    void testAddAndSize() {
        VectorHeap<String> heap = new VectorHeap<>();
        heap.add("b");
        assertFalse(heap.isEmpty());
        assertEquals(1, heap.size());
        heap.add("a");
        heap.add("c");
        assertEquals(3, heap.size());
    }

    @Test
    void testPeekDoesNotRemove() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        heap.add(10);
        heap.add(5);
        assertEquals(5, heap.peek());
        assertEquals(2, heap.size(), "peek() should not remove element");
        assertEquals(5, heap.peek(), "peeking twice still gives same top");
    }

    @Test
    void testIntegerOrdering() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        int[] inputs = { 7, 3, 9, 1, 4 };
        for (int v : inputs) {
            heap.add(v);
        }
        int[] expected = { 1, 3, 4, 7, 9 };
        for (int v : expected) {
            Integer removed = heap.remove();
            assertNotNull(removed);
            assertEquals(v, removed.intValue());
        }
        assertTrue(heap.isEmpty(), "Heap should be empty after removing all");
    }

    @Test
    void testStringOrdering() {
        VectorHeap<String> heap = new VectorHeap<>();
        heap.add("delta");
        heap.add("alpha");
        heap.add("charlie");
        heap.add("bravo");

        assertEquals("alpha", heap.remove());
        assertEquals("bravo", heap.remove());
        assertEquals("charlie", heap.remove());
        assertEquals("delta", heap.remove());
    }

    @Test
    void testPatientOrdering() {
        VectorHeap<Patient> heap = new VectorHeap<>();
        // The Patient.compareTo orders by code then arrival sequence:
        Patient pC = Patient.fromCsv("Juan Perez, leg fracture, C");
        Patient pA = Patient.fromCsv("Maria Ramirez, appendicitis, A");
        Patient pE = Patient.fromCsv("Lorenzo Toledo, chikungunya, E");
        Patient pB = Patient.fromCsv("Carmen Sarmientos, labor pains, B");

        // insert in random order
        heap.add(pC);
        heap.add(pA);
        heap.add(pE);
        heap.add(pB);

        // should come out A → B → C → E
        assertEquals(pA, heap.remove());
        assertEquals(pB, heap.remove());
        assertEquals(pC, heap.remove());
        assertEquals(pE, heap.remove());
        assertTrue(heap.isEmpty());
    }
}
