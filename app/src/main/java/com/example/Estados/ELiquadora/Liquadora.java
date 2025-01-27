package com.example.Estados.ELiquadora;

<<<<<<< HEAD:app/src/main/java/com/example/Estados/ELiquadora/Liquadora.java
public interface  Liquadora {
    public Float addToLiquiadora(Float materialQuantity);
    public String increaseVelocity();
    public String decreaseVelocity();
    public String emptyLiquiadora();
=======
/**
 * Estas clases fueron las que se decidieron en clase.
 */
public interface  Iliquadora {
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
>>>>>>> 86051701ab5c7624fd7b3377c8aa3421db9c6a54:app/src/main/java/com/example/Estados/Liquadora/Iliquadora.java
}
