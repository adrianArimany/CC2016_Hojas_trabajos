package com.example.emergency.heap;

/**
 * 
 */

/**
 * Extracted from the original code to be used as a generic class for the:
 * @author moises.alonso (Original author) (source code: https://github.com/malonso-uvg/uvg2025ed/blob/main/e14_BinaryHeap/src/TreeNode.java)
 * 
 */
public class TreeNode<P, V> {

	private P _priority;
	private V _value;
	TreeNode<P, V> _parent;
	TreeNode<P, V> _left;
	TreeNode<P, V> _right;
	
	public TreeNode(P priority, V value) {
		this._priority = priority;
		this._value = value;
		this._parent = null;
		this._left = null;
		this._right = null;
	}
	
    /**
     * Returns the priority of this tree node.
     * 
     * @return the priority associated with this node
     */
	public P get_priority() {
		return _priority;
	}
    /**
     * Sets the priority of this tree node.
     *
     * @param _priority the new priority to be set for this node
     */

	public void set_priority(P _priority) {
		this._priority = _priority;
	}
    /**
     * Returns the value associated with this tree node.
     * 
     * @return the value stored in this node
     */
	public V get_value() {
		return _value;
	}
    /**
     * Sets the value associated with this tree node.
     * 
     * @param _value the new value to be set for this node
     */
	public void set_value(V _value) {
		this._value = _value;
	}
    /**
     * Returns the parent of this tree node.
     * 
     * @return the parent of this node
     */
	public TreeNode<P, V> get_parent() {
		return _parent;
	}
    /**
     * Sets the parent of this tree node.
     * 
     * @param _parent the new parent to be set for this node
     */
	public void set_parent(TreeNode<P, V> _parent) {
		this._parent = _parent;
	}
    /**
     * Returns the left child of this tree node.
     * 
     * @return the left child of this node
     */
	public TreeNode<P, V> get_left() {
		return _left;
	}
    /**
     * Sets the left child of this tree node.
     * 
     * @param _left the new left child to be set for this node
     */
	public void set_left(TreeNode<P, V> _left) {
		this._left = _left;
	}
    /**
     * Returns the right child of this tree node.
     * 
     * @return the right child of this node
     */
	public TreeNode<P, V> get_right() {
		return _right;
	}
    /**
     * Sets the right child of this tree node.
     * 
     * @param _right the new right child to be set for this node
     */
	public void set_right(TreeNode<P, V> _right) {
		this._right = _right;
	}

	
	
}

