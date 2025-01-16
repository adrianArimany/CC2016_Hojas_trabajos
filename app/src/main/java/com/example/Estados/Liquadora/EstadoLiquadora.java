package com.example.Estados.Liquadora;

import com.example.Estados.Estado;

public class EstadoLiquadora extends Estado implements Iliquadora {
    public EstadoLiquadora() {
        super();
    }

    @Override
    public String showMenu() {
        return "";
    } 

    @Override
    public Estado transition(int action) {
        return this;
    }  

    @Override
    public String addToLiquiadora(int index) {
        return "";
    }

    @Override
    public String increaseVelocity() {
        return "";
    }   

    @Override
    public String decreaseVelocity() {
        return "";
    }   

    @Override
    public String emptyLiquiadora() {
        return "";
    }   

}
