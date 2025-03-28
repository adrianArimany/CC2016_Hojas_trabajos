package com.example.searchstructure;

public interface ITraversal<K extends Comparable<K>, V> {
    void check(BTN<K, V> actualNode);
}