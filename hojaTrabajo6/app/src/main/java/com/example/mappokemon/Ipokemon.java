package com.example.mappokemon;

import java.util.List;


public interface Ipokemon {
    boolean addPokemon(String name, String ability); //Adds a Pokemon to the data
    PokemonRecord getPokemon(String name); //Gets a Pokemon by name
    List<PokemonRecord> searchByName(String nameQuery); //Searches for a Pokemon by name
    List<PokemonRecord> searchByAbility(String abilityQuery); //Searches for a Pokemon by ability
    String getAllPokemon(); //gETS ALL THE POKEMOS FROM THE DATA
    void loadFrom(String filePath); //Generic Loading format, can be CSV, JSON, XML, etc. (currently only CSV)
    void saveTo(String filePath); //Generic Saving format, can be CSV, JSON, XML, etc. (currently only CSV)
}
