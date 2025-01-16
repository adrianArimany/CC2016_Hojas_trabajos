package com.example.Estados.MenuPrincipal;

import com.example.Estados.Estado;
import com.example.Estados.Liquadora.EstadoLiquadora;


/**
 * The MenuPrincipal class represents the main menu of the system.
 * It provides options for Mobile, Radio, Personal Audio, Productivity, and Sleep mode.
 * 
 */
public class MenuPrincipal extends Estado {

    /**
    * Generates a string of the menu options for the main menu.
    * 
    * @return A string of the menu options, including options for Mobile,
    * Radio, Personal Audio, Productivity, and Sleep mode.
    */
    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("-1. Sleep mode (Press at any time) \n");
        
        return menu.toString();
    }

    @Override
    public Estado transition(int action) {
       switch (action) {
        case 1:
            return new EstadoLiquadora();
        default:
            return this;
       }
    }
}