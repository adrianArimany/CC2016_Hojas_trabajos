package com.example.Estados.ELiquadora;

/**
 * Estas clases fueron las que se decidieron en clase.
 */
public interface  Liquadora {
    void encender(); //apagar la liquadora
    void apagar(); //endencer la liquadora
    boolean estaEncendida(); //Esta la liquadora encendida returna un verdadero o falso
    double llenar(double volumen);  //se suma valores a la liquadora returna un double
    int incrementarVelocidad(); //se aumenta la velocidad de la liquadora returna un int
    int decrementarVelocidad(); // se disminuye la velocidad de la liquadora returna un int
    int consultarVelocidad(); //se consulta la velocidad returna un int
    boolean estaLlena(); //revisa que la liquadora no a llegado a su limite returna un verdadero o falso
    double vaciar(); //se vacia la liquadora returna un double
    double servir(double volumenRestado); //se sirve toda o parte de la liquadora. returna un double

}
