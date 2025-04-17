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

    /** Insert an element into the heap */
    public void add(T item) {
        heap.add(item);
        percolateUp(heap.size() - 1);
    }

    /** Peek at (but don’t remove) the top priority element, or null if empty */
    public T peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    /** Remove and return the top priority element, or null if empty */
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

    /** Number of elements in the queue */
    public int size() {
        return heap.size();
    }

    /** True if no elements are queued */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    //––– Internal helpers –––

    /** Restore heap order by bubbling the item at idx up */
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

    /** Restore heap order by bubbling the item at idx down */
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