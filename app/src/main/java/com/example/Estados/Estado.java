package com.example.Estados;

public  abstract class Estado {
    public abstract String showMenu();
    public abstract Estado transition(int action);
}
