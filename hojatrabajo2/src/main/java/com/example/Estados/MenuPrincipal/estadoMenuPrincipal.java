package com.example.Estados.MenuPrincipal;

import com.example.Estados.Estado;

public class estadoMenuPrincipal extends Estado {

    public estadoMenuPrincipal() {

    }

    @Override
    public String showMenu() {
        return "Menu Principal";
    }

    @Override
    public Estado transition(int action) {
        return null;
    }
    
}
