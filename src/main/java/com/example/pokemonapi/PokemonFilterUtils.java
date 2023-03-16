package com.example.pokemonapi;

import java.util.HashMap;

public class PokemonFilterUtils {

    public static boolean searchByPoweredThanValue(HashMap<String, Object> pokemon, int power) {
        if(pokemon.containsKey("stats")) {
            HashMap<String, Integer> stats = (HashMap<String, Integer>) pokemon.get("stats");
            if(stats instanceof HashMap<String, Integer>) {
                return stats.get("attack") >= power;
            }
        }
        return false;
    }
}
