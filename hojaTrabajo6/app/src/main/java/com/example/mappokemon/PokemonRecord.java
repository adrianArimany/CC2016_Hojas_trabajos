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

    /**
     * @return the name of the Pokemon represented by this PokemonRecord
     */
    public String getName() {
        return name;
    }

    /**
     * @return the ability of the Pokemon represented by this PokemonRecord
     */
    public String getAbility() {
        return ability;
    }

    /**
     * @return the type of the Pokemon represented by this PokemonRecord
     */
    public String getType1() {
        return type1;
    }

    /**
    * Returns a string representation of the Pokemon, including its name and primary type.
    *
    * @return a string in the format "Pokemon [name : {name}, type1 : {type1}]"
    */
    @Override
    public String toString() {
        return "Pokemon [name : " + name + ", type1 : " + type1 + "]";
    }


    
    /**
     * Returns a string representation of the Pokemon, including its name, primary type, and ability.
     *
     * @return a string in the format "Pokemon [name : {name}, type1 : {type1}, ability : {ability}]"
     */
    public String toStringWithAbility() {
        return "Pokemon [name : " + name + ", type1 : " + type1 + ", ability : " + ability + "]";
    }
}
