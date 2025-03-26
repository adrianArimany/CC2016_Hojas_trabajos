package com.example.searchstructure;

public interface ITraversal<K extends Comparable<K>, V> {
    void visitar(BinaryTreeNode<K, V> actualNode);
}