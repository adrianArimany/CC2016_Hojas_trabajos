package com.example.Estados.Liquadora;

public interface  Iliquadora {
    void encender();
    void apagar();
    boolean estaEncendida();
    double llenar(double volumen);
    int incrementarVelocidad();
    int decrementarVelocidad();
    int consultarVelocidad();
    boolean estaLlena();
    double vaciar();
    double servir(double volumenRestado);
}
