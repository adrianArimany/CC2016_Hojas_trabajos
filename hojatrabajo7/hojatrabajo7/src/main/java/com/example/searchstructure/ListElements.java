package com.example.searchstructure;

import java.util.ArrayList;

public class ListElements<K extends Comparable<K>, V> implements ITraversal<K , V>{

    public ArrayList<V> elementos = new ArrayList<>();

    @Override
    public void visitar(BTN<K, V> actualNode) {
        elementos.add(actualNode.get_value());
    }
    
}