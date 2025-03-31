package com.example.searchstructure;

import java.util.ArrayList;

public class ListElements<K extends Comparable<K>, V> implements ITraversal<K , V>{

    public ArrayList<V> elementos = new ArrayList<>();

    /**
     * Agrega el valor de un nodo a una lista.
     *
     * <p>Este m todo se utiliza como parte del patr n de dise o
     * <i>Visitor</i> para recorrer un  rbol binario de b squeda y
     * extraer sus valores.</p>
     *
     */
    @Override
    public void check(BTN<K, V> actualNode) {
        elementos.add(actualNode.get_value());
    }
    
}