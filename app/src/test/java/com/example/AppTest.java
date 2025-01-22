package com.example;

import static org.junit.Assert.assertEquals;


import org.junit.Test;

import com.example.Estados.Liquadora.EstadoLiquadora;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    @Test
    public void testGetVelocidad() {
        EstadoLiquadora estado = new EstadoLiquadora();
        assertEquals(0, estado.llenar(0), 0.01);
    }
    
    @Test
    public void testIncrementarVelocidad() {
        EstadoLiquadora estado = new EstadoLiquadora();
        assertEquals(1, estado.incrementarVelocidad(), 0.01);
    }
    
    @Test
    public void testVaciar() {
        EstadoLiquadora estado = new EstadoLiquadora();
        assertEquals(0, estado.vaciar(), 0.01);
    }
    
    
    
    
}
