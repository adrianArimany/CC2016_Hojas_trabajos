package com.example.searchstructure;

public class ElementsToSave<K extends Comparable<K>, V> implements ITraversal<K, V> {

    @Override
    public void check(BTN<K, V> actualNode) {
        System.out.println(actualNode.get_value().toString());
    }
}  
