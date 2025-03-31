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
    
/**
 * Returns the number of nodes currently present in the binary search tree.
 *
 * @return the count of nodes in the tree
 */
    @Override
    public int count() {
        return _count;
    }
    
    /**
     * Indicates whether the binary search tree is empty or not.
     * 
     * @return true if the tree is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return _count == 0;
    }
    
/**
 * Inserts a new node with the specified key and value into the binary search tree.
 * 
 * If the tree is empty, the new node becomes the root. Otherwise, it is inserted 
 * in the appropriate position to maintain the binary search tree property.
 * If a node with the same key already exists, the node's value is updated if the 
 * new value has a lower price (specific to HomeApplianceRecord).
 *
 * @param key   the key of the new node
 * @param value the value of the new node
 */
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
    
    /**
     * Recursively inserts a new node into the binary search tree.
     * 
     * It starts at the given parent node and navigates to the appropriate position
     * to insert the new node, maintaining the binary search tree property. If a node
     * with the same key already exists, the node's value is updated if the new value
     * has a lower price (specific to HomeApplianceRecord).
     * 
     * @param parent the parent node to start the insertion from
     * @param newNode the new node to be inserted
     */
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
    
    /**
     * Searches for a node in the binary search tree with the given key.
     * 
     * @param keyToFind the key of the node to search for
     * @return the value associated with the node with the given key, or null if the key is not present
     */
    @Override
    public V search(K keyToFind) {
        if (isEmpty()){
            return null;
        } else {
            return internalSearch(_root, keyToFind);
        }
    }
    
    /**
     * Recursively searches for a node in the binary search tree with the given key.
     * 
     * It starts at the given parent node and navigates to the appropriate position
     * to find the node with the given key, maintaining the binary search tree property.
     * If the key is not present, it returns null.
     * 
     * @param parent the parent node to start the search from
     * @param keyToFind the key of the node to search for
     * @return the value associated with the node with the given key, or null if the key is not present
     */
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
    
    /**
     * Removes a node from the binary search tree with the given key.
     * 
     * If the key is not present, it returns null.
     * 
     * @param key the key of the node to remove
     * @return the value associated with the removed node, or null if the key is not present
     */
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
    
    
    /**
     * Finds a node in the binary search tree with the given key.
     * 
     * Recursively searches for a node in the binary search tree with the given key.
     * It starts at the given parent node and navigates to the appropriate position
     * to find the node with the given key, maintaining the binary search tree property.
     * If the key is not present, it returns null.
     * 
     * @param node the parent node to start the search from
     * @param key the key of the node to search for
     * @return the node with the given key, or null if the key is not present
     */
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
    
    
    /**
     * Returns the node with the minimum key in the subtree rooted at the given node.
     * 
     * @param node the node to start searching from
     * @return the node with the minimum key
     */
    private BTN<K,V> getMin(BTN<K,V> node) {
        while (node.get_leftChild() != null) {
            node = node.get_leftChild();
        }
        return node;
    }
    
/**
 * Performs an in-order traversal of the binary search tree.
 * 
 * This method uses the in-order traversal technique, which visits 
 * the left subtree, the current node, and then the right subtree.
 * It applies the provided traversal method to each node during 
 * the traversal.
 * 
 * @param traversalMethod the method to be applied to each node during the traversal
 */
    @Override
    public void InOrder(ITraversal<K, V> traversalMethod) {
        if (isEmpty()){
            return;
        } else {
            internalInOrder(traversalMethod, _root);
        }
    }
    
/**
 * Recursively performs an in-order traversal of the binary search tree
 * starting from the specified node.
 *
 * This method visits the left subtree, applies the traversal method
 * to the current node, and then visits the right subtree.
 *
 * @param traversalMethod the method to be applied to each node during the traversal
 * @param actualNode the current node to start the traversal from
 */

    private void internalInOrder(ITraversal<K, V> traversalMethod, BTN<K, V> actualNode){
        if (actualNode.get_leftChild() != null){
            internalInOrder(traversalMethod, actualNode.get_leftChild());
        }
        traversalMethod.check(actualNode);
        if (actualNode.get_rightChild() != null){
            internalInOrder(traversalMethod, actualNode.get_rightChild());
        }
    }
    
    /**
     * Performs a pre-order traversal of the binary search tree.
     *
     * This method applies the provided traversal method to the root node
     * first, then recursively traverses the left and right subtrees.
     *
     * @param traversalMethod the method to be applied to each node during the traversal
     */
    @Override
    public void PreOrder(ITraversal<K, V> traversalMethod) {
        internalPreOrder(_root, traversalMethod);
    }
    
    /**
     * Recursively performs a pre-order traversal of the binary search tree
     * starting from the specified node.
     *
     * This method applies the provided traversal method to the current node
     * first, then recursively traverses the left and right subtrees.
     *
     * @param node            the current node to start the traversal from
     * @param traversalMethod the method to be applied to each node during the traversal
     */
    private void internalPreOrder(BTN<K, V> node, ITraversal<K, V> traversalMethod) {
        if (node != null) {
            traversalMethod.check(node);
            internalPreOrder(node.get_leftChild(), traversalMethod);
            internalPreOrder(node.get_rightChild(), traversalMethod);
        }
    }
    
/**
 * Performs a post-order traversal of the binary search tree.
 *
 * This method recursively traverses the left and right subtrees
 * first, then applies the provided traversal method to the root node.
 *
 * @param traversalMethod the method to be applied to each node during the traversal
 */

    @Override
    public void PostOrder(ITraversal<K, V> traversalMethod) {
        internalPostOrder(_root, traversalMethod);
    }
    
/**
 * Recursively performs a post-order traversal of the binary search tree
 * starting from the specified node.
 *
 * This method first traverses the left and right subtrees, then applies
 * the traversal method to the current node.
 *
 * @param node            the current node to start the traversal from
 * @param traversalMethod the method to be applied to each node during the traversal
 */

    private void internalPostOrder(BTN<K, V> node, ITraversal<K, V> traversalMethod) {
        if (node != null) {
            internalPostOrder(node.get_leftChild(), traversalMethod);
            internalPostOrder(node.get_rightChild(), traversalMethod);
            traversalMethod.check(node);
        }
    }
}