package com.example.mappokemon;

import java.util.List;


public interface Ipokemon {
    boolean addPokemon(String name, String ability);
    PokemonRecord getPokemon(String name);
    List<PokemonRecord> searchByName(String nameQuery);
    List<PokemonRecord> searchByAbility(String abilityQuery);
    String getAllPokemon();
    //Generic Loading format, can be CSV, JSON, XML, etc.
    void loadFrom(String filePath);
}
