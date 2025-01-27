package com.example.Estados;



import com.example.Estados.ELiquadora.EstadoLiquadora;

public class ManejadorDeEstados {
    Estado currEstado;
    boolean systemOn = false;
    
     public ManejadorDeEstados() {
        this.currEstado = new EstadoLiquadora();
    }

    /**
     * Gets the menu options for the current state of the system.
     * 
     * @return A string of the menu options.
     */
    public String showMenu(){
        return this.currEstado.showMenu();
    }

    /**
    * Retrieves the current state of the system.
    *
    * @return the current Estado object representing the system's state.
    */
    public Estado getEstado(){
        return this.currEstado;
    }

    /**
    * Handles the state transition based on the given action.
    * 
    * If the action is -1, the system will enter sleep mode. For any other action,
    * it will transition to the corresponding state as determined by the current
    * state's transition method.
    * 
    * @param action the action to be performed, which determines the transition.
    *               Possible values are:
    *               -1 - Turn off the system.
    */
    public void transition(int action){        
        if (action == -1){
            ((EstadoLiquadora) this.currEstado).apagar();
            systemOff();
            return;
        }
        this.currEstado = this.currEstado.transition(action);
    }

    /**
    * Turns off the system by setting the systemOn flag to false.
    * This will stop the system from accepting any further actions
    * until it is turned on again.
    */
    public void systemOff(){
        systemOn = false;
    }

    /**
     * Checks if the system is currently on.
     * 
     * @return true if the system is on, false otherwise.
     */
    public boolean isSystemOn() {
        return systemOn;
    }

    /**
     * Sets the system's on/off state.
     * 
     * @param systemOn a boolean value representing the desired state of the system:
     *                 true to turn the system on, false to turn it off.
     */
    public void setSystemOn(boolean systemOn) {
        this.systemOn = systemOn;
    }
}
