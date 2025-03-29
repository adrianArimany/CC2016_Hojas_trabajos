package com.example.searchstructure;

import com.example.objecthomeappliance.HomeApplianceRecord;

/**
 * I got parts of this class from Moises, 2025
 * https://github.com/malonso-uvg/uvg2025ed/blob/main/e13_BinarySearchTree/binary-search-tree/src/main/java/edu/uvg/estructuras/BinarySearchTree.java
 * 
 * 
 * 
 */

public class BST<K extends Comparable<K>, V> implements Ibst<K, V> {

    private int _count;
    private BTN<K, V> _root;
    
    public BST(){
        _count = 0;
        _root = null;
    }
    
    @Override
    public int count() {
        return _count;
    }
    
    @Override
    public boolean isEmpty() {
        return _count == 0;
    }
    
    @Override
    public void insert(K key, V value) {
        BTN<K, V> newNode = new BTN<>(key, value, null, false);
    
        if (isEmpty()){
            _root = newNode;
            _count++;
        } else {
            internalInsert(_root, newNode);
        }
    }
    
    private void internalInsert(BTN<K, V> parent, BTN<K, V> newNode){
        int result = parent.get_key().compareTo(newNode.get_key());
    
        if (result > 0){ // parent is greater, go left
            if (parent.get_leftChild() == null){
                newNode.setIsRightChild(false);
                newNode.set_parent(parent);
                parent.set_leftChild(newNode);
                _count++;
            } else {
                internalInsert(parent.get_leftChild(), newNode);
            }   
        } else if (result < 0){ // parent is less, go right
            if (parent.get_rightChild() == null){
                newNode.setIsRightChild(true);
                newNode.set_parent(parent);
                parent.set_rightChild(newNode);
                _count++;
            } else {
                internalInsert(parent.get_rightChild(), newNode);
            }   
        } else {
            // Duplicate key: if the same SKU is being inserted, we want to keep the product
            // with the lowest Price_Current.
            HomeApplianceRecord current = (HomeApplianceRecord) parent.get_value();
            V newRecord = newNode.get_value();
            if(((HomeApplianceRecord)newRecord).getPriceCurrent() < ((HomeApplianceRecord)current).getPriceCurrent()){ 
                parent.set_value(newRecord);
            }
            return;
        }
    }
    
    @Override
    public V search(K keyToFind) {
        if (isEmpty()){
            return null;
        } else {
            return internalSearch(_root, keyToFind);
        }
    }
    
    private V internalSearch(BTN<K, V> parent, K keyToFind){
        if (parent != null){
            int result = parent.get_key().compareTo(keyToFind);
            if (result > 0){ // search left
                return internalSearch(parent.get_leftChild(), keyToFind);
            } else if (result < 0){ // search right
                return internalSearch(parent.get_rightChild(), keyToFind);
            } else { // found it
                return parent.get_value();
            }
        } else {
            return null;
        }
    }
    
    @Override
    public V remove(K key) {
        BTN<K,V> nodeToRemove = findNode(_root, key);
        if (nodeToRemove == null) {
            return null;
        }
        V removedValue = nodeToRemove.get_value();
        removeNode(nodeToRemove);
        _count--;
        return removedValue;
    }
    
    // Helper method to locate the node with the given key.
    private BTN<K,V> findNode(BTN<K,V> node, K key) {
        if (node == null) return null;
        int cmp = node.get_key().compareTo(key);
        if (cmp == 0) return node;
        if (cmp > 0) return findNode(node.get_leftChild(), key);
        else return findNode(node.get_rightChild(), key);
    }
    
    // Helper method to remove a node from the tree.
    private void removeNode(BTN<K,V> node) {
        // Case 1: Node has no children.
        if (node.get_leftChild() == null && node.get_rightChild() == null) {
            if (node == _root) {
                _root = null;
            } else {
                BTN<K,V> parent = node.get_parent();
                if (node.isRightChild()) {
                    parent.set_rightChild(null);
                } else {
                    parent.set_leftChild(null);
                }
            }
        }
        // Case 2: Node has only one child.
        else if (node.get_leftChild() != null && node.get_rightChild() == null) {
            BTN<K,V> child = node.get_leftChild();
            if (node == _root) {
                _root = child;
                child.set_parent(null);
            } else {
                BTN<K,V> parent = node.get_parent();
                if (node.isRightChild()) {
                    parent.set_rightChild(child);
                    child.setIsRightChild(true);
                } else {
                    parent.set_leftChild(child);
                    child.setIsRightChild(false);
                }
                child.set_parent(parent);
            }
        }
        else if (node.get_leftChild() == null && node.get_rightChild() != null) {
            BTN<K,V> child = node.get_rightChild();
            if (node == _root) {
                _root = child;
                child.set_parent(null);
            } else {
                BTN<K,V> parent = node.get_parent();
                if (node.isRightChild()) {
                    parent.set_rightChild(child);
                    child.setIsRightChild(true);
                } else {
                    parent.set_leftChild(child);
                    child.setIsRightChild(false);
                }
                child.set_parent(parent);
            }
        }
        // Case 3: Node has two children.
        else {
            // Find the in-order successor (minimum in right subtree)
            BTN<K,V> successor = getMin(node.get_rightChild());
            // Swap the key and value between node and its successor.
            K tempKey = node.get_key();
            V tempValue = node.get_value();
            node.set_key(successor.get_key());
            node.set_value(successor.get_value());
            successor.set_key(tempKey);
            successor.set_value(tempValue);
            // Remove the successor node (which now has at most one child)
            removeNode(successor);
        }
    }
    
    // Helper method to find the minimum node in a subtree.
    private BTN<K,V> getMin(BTN<K,V> node) {
        while (node.get_leftChild() != null) {
            node = node.get_leftChild();
        }
        return node;
    }
    
    @Override
    public void InOrder(ITraversal<K, V> traversalMethod) {
        if (isEmpty()){
            return;
        } else {
            internalInOrder(traversalMethod, _root);
        }
    }
    
    private void internalInOrder(ITraversal<K, V> traversalMethod, BTN<K, V> actualNode){
        if (actualNode.get_leftChild() != null){
            internalInOrder(traversalMethod, actualNode.get_leftChild());
        }
        traversalMethod.check(actualNode);
        if (actualNode.get_rightChild() != null){
            internalInOrder(traversalMethod, actualNode.get_rightChild());
        }
    }
    
    @Override
    public void PreOrder(ITraversal<K, V> traversalMethod) {
        internalPreOrder(_root, traversalMethod);
    }
    
    private void internalPreOrder(BTN<K, V> node, ITraversal<K, V> traversalMethod) {
        if (node != null) {
            traversalMethod.check(node);
            internalPreOrder(node.get_leftChild(), traversalMethod);
            internalPreOrder(node.get_rightChild(), traversalMethod);
        }
    }
    
    @Override
    public void PostOrder(ITraversal<K, V> traversalMethod) {
        internalPostOrder(_root, traversalMethod);
    }
    
    private void internalPostOrder(BTN<K, V> node, ITraversal<K, V> traversalMethod) {
        if (node != null) {
            internalPostOrder(node.get_leftChild(), traversalMethod);
            internalPostOrder(node.get_rightChild(), traversalMethod);
            traversalMethod.check(node);
        }
    }
}