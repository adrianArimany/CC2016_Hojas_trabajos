package com.example.Estados.MenuPrincipal;

import com.example.Estados.Estado;

public class estadoMenuPrincipal extends Estado {

    public estadoMenuPrincipal() {

    }

    @Override
    public String showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("============ Systema de la Calculadora ============== \n");
        menu.append("| 1.                           |\n");
        menu.append("| 2.                                |\n");
        menu.append("| 3.                               |\n");
        menu.append("| 4.                                 |\n");
        menu.append("| 5.                                |\n");
        menu.append("| -1.                                           |\n");
        menu.append("======================================================\n");


        return menu.toString();
    }

    @Override
    public Estado transition(int action) {
        
        
        return null;
    }

}
