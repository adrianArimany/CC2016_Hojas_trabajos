package com.example.emergency.heap;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * 
 */

/**
 * Extracted from the original code to be used as a generic class for the:
 * @author moises.alonso (Original author) (source code: https://github.com/malonso-uvg/uvg2025ed/blob/main/e14_BinaryHeap/src/TreeNode.java)
 * 
 */
public class HeapUsingIterativeBinaryTree<P, V> implements IHeap<P, V> {

	private int _count;
	private TreeNode<P, V> _root;
	private final Comparator<P> _priorityComparator;
	
	public HeapUsingIterativeBinaryTree(Comparator<P> priorityComparator) {
		_count = 0;
		_priorityComparator = priorityComparator;
	}
	
	/**
	 * Inserts a new element into the heap. If the heap is empty, the element is
	 * inserted at the root. Otherwise, the element is inserted at the next
	 * available position in the heap and then swapped up the tree according to
	 * the priority of the element.
	 * 
	 * @param priority the priority of the element to be inserted
	 * @param value    the value to be inserted
	 */
	@Override
	public void Insert(P priority, V value) {
		TreeNode<P, V> newNode = new TreeNode<>(priority, value);
		
		if (count() == 0) { //Inserto en la raiz
			_count++;
			_root = newNode;
		} else {
			
			_count++;
			
			byte[] movimientos = convertToBinary(_count);
			
			//Reccorer de acuerdo a los movimientos
			int index = 1;
			TreeNode<P, V> actual = _root;
			
			while (index < movimientos.length) {
				
				if (index == movimientos.length - 1) {
					if (movimientos[index] == 0) { // Inserto en el hijo izquierdo
						actual.set_left(newNode);
					} else { //Inserto en el hijo derecho
						actual.set_right(newNode);
					}
					newNode.set_parent(actual);
				} else {
					if (movimientos[index] == 0) { // Inserto en el hijo izquierdo
						actual = actual.get_left();
					} else { //Inserto en el hijo derecho
						actual = actual.get_right();
					}
				}
				
				index++;
			}
			
			
			//Hacer Swap
			actual = newNode;
			while((actual != null) && (actual.get_parent() != null)) {
				Swap(actual);
				actual = actual.get_parent();
			}
			
		}
		
	}
	
    /**
     * Converts an integer value to its binary representation as an array of bytes.
     * Each byte represents a bit (0 or 1) of the binary number, with the least
     * significant bit at the beginning of the array.
     *
     * @param value the integer value to convert to binary
     * @return a byte array representing the binary form of the given integer
     */

	private byte[] convertToBinary(int value) {
		
		ArrayList<Byte> listBytes = new ArrayList<>();
		
		while (value > 0) {
			listBytes.add( (byte)(value % 2) );
			value = value / 2;
		}
		//listBytes.add((byte) (value % 2) );
		
		byte[] binaryBytes = new byte[listBytes.size()]; 
		
		for (int i = listBytes.size() - 1; i >= 0; i-- ) {
			binaryBytes[listBytes.size() - 1 - i] = listBytes.get(i);  
		}
		
		return binaryBytes;
	}
	
	/**
	 * Swap the values of two nodes if the child has a higher priority than its parent.
	 * This is a helper method for the Insert method.
	 * 
	 * @param actualNode the node that is being swapped
	 */
	private void Swap(TreeNode<P, V> actualNode) {
		if (actualNode != null) {
			
			if (actualNode.get_parent() != null) { //is not the root
				
				int result = _priorityComparator.compare(actualNode.get_priority(), actualNode.get_parent().get_priority());
				
				if (result > 0) { //if child is greater than parent
					P tempP = actualNode.get_priority();
					V tempV = actualNode.get_value();
					
					actualNode.set_priority(actualNode.get_parent().get_priority());
					actualNode.set_value(actualNode.get_parent().get_value());
					
					actualNode.get_parent().set_priority(tempP);
					actualNode.get_parent().set_value(tempV);
				}
				
			}
			
		}
	}
	

	/**
	 * Return the highest priority element in the heap, or null if empty.
	 * This just returns the value of the root of the tree.
	 * 
	 * @return the highest priority element, or null if the heap is empty
	 */
	@Override
	public V get() {
		if (isEmpty())
			return null;
		else 
			return _root.get_value();
	}

/**
 * Removes and returns the value with the highest priority from the heap.
 * If the heap is empty, returns null. This method performs a swap between
 * the root and the last node, removes the last node, and then restores
 * the heap property by swapping the root down the tree as necessary.
 * 
 * @return the value of the removed node, or null if the heap is empty
 */

	@Override
	public V remove() {
		
		if (isEmpty()) {
			return null;
		}
		
		if (count() == 1) {
			_count--;
			TreeNode<P, V> temporal = _root;
			_root = null;
			return temporal.get_value();
		}
		
		//if has more than 1 element
		
		// Obtener el binario de la cantidad de nodos
		byte[] movimientos = convertToBinary(_count);
		
		// Encontrar el nodo a eliminar
		//Reccorer de acuerdo a los movimientos
		int index = 1;
		TreeNode<P, V> actual = _root;
		TreeNode<P, V> nodoToBeDeleted = null;
		
		while (index < movimientos.length) {
			
			if (index == movimientos.length - 1) {
				if (movimientos[index] == 0) { // Inserto en el hijo izquierdo
					nodoToBeDeleted = actual.get_left();
				} else { //Inserto en el hijo derecho
					nodoToBeDeleted = actual.get_right();
				}
				
			} else {
				if (movimientos[index] == 0) { // Inserto en el hijo izquierdo
					actual = actual.get_left();
				} else { //Inserto en el hijo derecho
					actual = actual.get_right();
				}
			}
			
			index++;
		}
		
		// Hacer Swap de la hoja con la raiz
		P tempPriority = nodoToBeDeleted.get_priority();
		V tempValue = nodoToBeDeleted.get_value();
		
		nodoToBeDeleted.set_priority(_root.get_priority());
		nodoToBeDeleted.set_value(_root.get_value());
		
		_root.set_priority(tempPriority);
		_root.set_value(tempValue);
		
		
		// Eliminar el nodo hoja
		tempPriority = nodoToBeDeleted.get_priority();
		tempValue = nodoToBeDeleted.get_value();
		
		TreeNode<P, V> parent = nodoToBeDeleted.get_parent(); 
		if (parent.get_left() == nodoToBeDeleted)
			parent.set_left(null);
		else 
			parent.set_right(null);
		
		
		// buscar el lugar de insercion
		actual = _root;
		
		while (actual != null) {
			
			boolean actualHasLeftChild = actual.get_left() != null;
			boolean actualHasRightChild = actual.get_right() != null;
			
			if (actualHasLeftChild && actualHasRightChild) { //Tiene a los 2 hijos
				
				//Si tiene a los dos hijos verifico quien es el mayor
				int result = _priorityComparator.compare(actual.get_left().get_priority(), actual.get_right().get_priority());
				
				if (result == 0) { //Son iguales
					
					result = _priorityComparator.compare(actual.get_priority(), actual.get_left().get_priority());
					if (result < 0) {
						P tempPriority2 = actual.get_priority();
						V tempValue2 = actual.get_value();
						
						actual.set_priority(actual.get_left().get_priority());
						actual.set_value(actual.get_left().get_value());
						
						actual.get_left().set_priority(tempPriority2);
						actual.get_left().set_value(tempValue2);
						
						actual = actual.get_left();
					} else {
						break;
					}
					
				} else if (result > 0){ //Hijo izquierdo mayor
					result = _priorityComparator.compare(actual.get_priority(), actual.get_left().get_priority());
					if (result < 0) {
						P tempPriority2 = actual.get_priority();
						V tempValue2 = actual.get_value();
						
						actual.set_priority(actual.get_left().get_priority());
						actual.set_value(actual.get_left().get_value());
						
						actual.get_left().set_priority(tempPriority2);
						actual.get_left().set_value(tempValue2);
						actual = actual.get_left();
					} else {
						break;
					}
				} else {
					result = _priorityComparator.compare(actual.get_priority(), actual.get_right().get_priority());
					if (result < 0) {
						P tempPriority2 = actual.get_priority();
						V tempValue2 = actual.get_value();
						
						actual.set_priority(actual.get_right().get_priority());
						actual.set_value(actual.get_right().get_value());
						
						actual.get_right().set_priority(tempPriority2);
						actual.get_right().set_value(tempValue2);
						actual = actual.get_right();
					} else {
						break;
					}
				}
				
			} else if (!actualHasLeftChild && !actualHasRightChild){ //No tiene hijos
				break;
			} else if (actualHasLeftChild){ //Solo tiene izquierdo
				int result = _priorityComparator.compare(actual.get_priority(), actual.get_left().get_priority());
				if (result < 0) {
					P tempPriority2 = actual.get_priority();
					V tempValue2 = actual.get_value();
					
					actual.set_priority(actual.get_left().get_priority());
					actual.set_value(actual.get_left().get_value());
					
					actual.get_left().set_priority(tempPriority2);
					actual.get_left().set_value(tempValue2);
				} else {
					break;
				}
			} else { //Solo tiene derecho
				int result = _priorityComparator.compare(actual.get_priority(), actual.get_right().get_priority());
				if (result < 0) {
					P tempPriority2 = actual.get_priority();
					V tempValue2 = actual.get_value();
					
					actual.set_priority(actual.get_right().get_priority());
					actual.set_value(actual.get_right().get_value());
					
					actual.get_right().set_priority(tempPriority2);
					actual.get_right().set_value(tempValue2);
				} else {
					break;
				}
			}
		}
		
		// reducir la cantidad
		_count--;
		
		return tempValue;
	}

	/**
	 * @return the number of elements in the heap
	 */
	@Override
	public int count() {
		return _count;
	}

	/**
	 * True if no elements are queued.
	 * 
	 * @return true if the heap is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return _count == 0;
	}

}