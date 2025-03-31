package com.example.searchstructure;

public final class BTN<K extends Comparable<K>, V> {
    private K _key;
    private V _value;
    private BTN<K, V> _leftChild;
    private BTN<K, V> _rightChild;
    private BTN<K, V> _parent;
    private boolean isRightChild;


    public BTN(K key, V value, BTN<K, V> parent, boolean isrightchild){
        set_key(key);
        set_value(value);
        set_parent(parent);
        set_rightChild(null);
        set_leftChild(null);
        setIsRightChild(isrightchild);
    }   
    
    /**
     * @return the key for this node
     */
    public K get_key() {
        return _key;
    }
    /**
     * Set the key for this node.
     * 
     * @param _key
     *            the key for this node
     */
    public void set_key(K _key) {
        this._key = _key;
    }
    /**
     * @return the value associated with this node
     */
    public V get_value() {
        return _value;
    }
    /**
     * Set the value associated with this node.
     * 
     * @param _value
     *            the value to associate with this node
     */
    public void set_value(V _value) {
        this._value = _value;
    }
    /**
     * @return the left child node of this node, or null if there is no left child
     */

    public BTN<K, V> get_leftChild() {
        return _leftChild;
    }
    /**
     * Set the left child of this node.
     * 
     * @param _leftChild
     *            the left child of this node
     */
    public void set_leftChild(BTN<K, V> _leftChild) {
        this._leftChild = _leftChild;
    }
    /**
     * @return the right child node of this node, or null if there is no right child
     */
    public BTN<K, V> get_rightChild() {
        return _rightChild;
    }
    /**
     * Set the right child of this node.
     * 
     * @param _rightChild
     *            the right child of this node
     */
    public void set_rightChild(BTN<K, V> _rightChild) {
        this._rightChild = _rightChild;
    }
    /**
     * @return the parent node of this node, or null if this node is the root
     */
    public BTN<K, V> get_parent() {
        return _parent;
    }
    /**
     * Set the parent of this node.
     * 
     * @param _parent
     *            the parent of this node, or null if this node is the root
     */
    public void set_parent(BTN<K, V> _parent) {
        this._parent = _parent;
    }
    /**
     * @return true if this node is the right child of its parent, false otherwise
     */
    public boolean isRightChild() {
        return isRightChild;
    }
    /**
     * Sets whether this node is the right child of its parent.
     *
     * @param isRightChild
     *            true if this node is the right child, false otherwise
     */

    public void setIsRightChild(boolean isRightChild) {
        this.isRightChild = isRightChild;
    }

     
}