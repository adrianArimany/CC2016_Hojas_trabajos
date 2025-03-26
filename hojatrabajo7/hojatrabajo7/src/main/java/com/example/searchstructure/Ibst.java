package com.example.searchstructure;

/**
 * 
 * This interface was obtained mainly from https://github.com/malonso-uvg/uvg2025ed/tree/main/e13_BinarySearchTree/binary-search-tree 
 * (Moises, 2025)
 * 
 * 
 * 
 */

public interface Ibst<K extends Comparable<K>, V> {
    int count();

    boolean isEmpty();

    void insert(K key, V value);

    V search(K keyToFind);

    V remove(K key);

    void InOrder(ITraversal<K, V> traversalMethod);

    void PreOrder(ITraversal<K, V> traversalMethod);

    void PostOrder(ITraversal<K, V> traversalMethod);

}
