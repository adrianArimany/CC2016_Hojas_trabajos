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
    
    public K get_key() {
        return _key;
    }
    public void set_key(K _key) {
        this._key = _key;
    }
    public V get_value() {
        return _value;
    }
    public void set_value(V _value) {
        this._value = _value;
    }
    public BTN<K, V> get_leftChild() {
        return _leftChild;
    }
    public void set_leftChild(BTN<K, V> _leftChild) {
        this._leftChild = _leftChild;
    }
    public BTN<K, V> get_rightChild() {
        return _rightChild;
    }
    public void set_rightChild(BTN<K, V> _rightChild) {
        this._rightChild = _rightChild;
    }
    public BTN<K, V> get_parent() {
        return _parent;
    }
    public void set_parent(BTN<K, V> _parent) {
        this._parent = _parent;
    }
    public boolean isRightChild() {
        return isRightChild;
    }
    public void setIsRightChild(boolean isRightChild) {
        this.isRightChild = isRightChild;
    }

     
}