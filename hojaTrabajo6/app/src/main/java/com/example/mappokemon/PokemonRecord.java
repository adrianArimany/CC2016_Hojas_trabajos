package com.example.mappokemon;

public class PokemonRecord {
    private final String name;
    private final String ability;
    private final String type1;

    public PokemonRecord(String name, String ability, String type1) {
        this.name = name;
        this.ability = ability;
        this.type1 = type1;
    }

    public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }

    public String getType1() {
        return type1;
    }

    @Override
    public String toString() {
        return "Pokemon [name : " + name + ", type1 : " + type1 + "]";
    }

    public String toStringWithAbility() {
        return "Pokemon [name : " + name + ", type1 : " + type1 + ", ability : " + ability + "]";
    }
}
