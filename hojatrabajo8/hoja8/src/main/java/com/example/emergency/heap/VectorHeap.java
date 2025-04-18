package com.example.emergency.heap;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple min‑heap–based priority queue.
 * @param <T> element type; must be Comparable
 */
public class VectorHeap<T extends Comparable<? super T>> {
    private final List<T> heap = new ArrayList<>();

    /** Create an empty heap */
    public VectorHeap() { }

    
    /**
     * Insert a new element into the heap. This adds the element to the end of
     * the list and then "percolates" it up the heap, swapping it with parents
     * that are smaller than it.
     * 
     * @param item the element to be inserted
     */
    public void add(T item) {
        heap.add(item);
        percolateUp(heap.size() - 1);
    }

   
    /**
     * Return the highest priority element in the heap, or null if empty.
     * This just returns the value of the root of the tree.
     * 
     * @return the highest priority element, or null if the heap is empty
     */
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

 
    /**
     * Remove and return the highest priority element from the heap, or null if the heap is empty.
     * This method removes the root element, replaces it with the last element in the list, and 
     * percolates it down to maintain the heap property.
     * 
     * @return the removed highest priority element, or null if the heap is empty
     */

    public T remove() {
        if (heap.isEmpty()) return null;
        T root = heap.get(0);
        T last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            percolateDown(0);
        }
        return root;
    }

   
/**
 * Returns the number of elements currently in the heap.
 * 
 * @return the size of the heap
 */

    public int size() {
        return heap.size();
    }

  
    /**
     * True if no elements are queued.
     * 
     * @return true if the heap is empty, false otherwise
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    //––– Internal helpers –––

/**
 * Moves the element at the specified index up the heap until the heap property
 * is restored. This involves comparing the element with its parent and swapping
 * if necessary, to ensure that parent nodes are always less than or equal to
 * the value of their children in a min-heap.
 * 
 * @param idx the index of the element to move up the heap
 */

    private void percolateUp(int idx) {
        T value = heap.get(idx);
        while (idx > 0) {
            int parentIdx = (idx - 1) / 2;
            T parent = heap.get(parentIdx);
            if (value.compareTo(parent) >= 0) break;
            heap.set(idx, parent);
            idx = parentIdx;
        }
        heap.set(idx, value);
    }

    
    /**
     * Moves the element at the specified index down the heap until the heap property
     * is restored. This involves comparing the element with its children and swapping
     * if necessary, to ensure that parent nodes are always less than or equal to
     * the value of their children in a min-heap.
     * 
     * @param idx the index of the element to move down the heap
     */
    private void percolateDown(int idx) {
        int size = heap.size();
        T value = heap.get(idx);

        while (true) {
            int leftIdx  = 2 * idx + 1;
            if (leftIdx >= size) break;

            int rightIdx = leftIdx + 1;
            int childIdx = (rightIdx < size
                              && heap.get(rightIdx).compareTo(heap.get(leftIdx)) < 0)
                           ? rightIdx
                           : leftIdx;

            if (heap.get(childIdx).compareTo(value) >= 0) break;

            heap.set(idx, heap.get(childIdx));
            idx = childIdx;
        }

        heap.set(idx, value);
    }
}