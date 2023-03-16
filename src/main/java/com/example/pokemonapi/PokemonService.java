package com.example.pokemonapi;

import java.util.List;
public interface PokemonService<E> {
    List<E> getPokemons(int limit);


    List<E> getPokemonList();

    E getPokemon(String name);

    List<E> getPokemonsByType(String name);

    List<String> getPokemonsName(int limit);


    List<String> getPokemonsNameByType(String type);

    List<E> getRandomPokemonList();

    List<String> getRandomPokemonNameList();

    List<E> getRandomSuggestList();


    List<String> getPokemonWithPowerThan(int attack);
}
