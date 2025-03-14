package com.example.mappokemon;

import java.util.Arrays;
import java.util.List;

public class PokemonRecord {
    private final String name;
    private final String type1;
    private final List<String> abilities;

    public PokemonRecord(String name, String type1, String abilityField) {
        this.name = name;
        this.type1 = type1;
        this.abilities = Arrays.asList(abilityField.split(",")); //please have the abilities separated by commas
        this.abilities.replaceAll(String::trim);
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
    public List<String> getAbilities() {
        return abilities;
    }

    /**
     * @return the type of the Pokemon represented by this PokemonRecord
     */
    public String getType1() {
        return type1;
    }

     /**
     * Returns a string representation of the Pokemon, including its name, primary type, and ability.
     *
     * @return a string in the format "Pokemon [name : {name}, type1 : {type1}, ability : {ability}]"
     */
    
    public String toStringWithAbility() {
        return "Pokemon [name : " + name + ", type1 : " + type1 + ", abilities : " + abilities + "]";
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


    
   
}
