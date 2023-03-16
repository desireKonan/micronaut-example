package com.example.pokemonapi;

import io.micronaut.http.client.HttpClient;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Singleton
public class PokemonRestAPIService implements PokemonService<HashMap<String, Object>> {

    private static final String POKE_API_BASE_URL = "https://pokebuildapi.fr/api/v1/";

    private static final String POKEMON_URL = "pokemon/";

    private static final String TEAM_URL = "teams/";

    private static final int POKEMON_MAX = 500;

    private static final int POKEMON_MIN = 100;

    private static final String RANDOM_TEAM_URL = "random/team";
    
    private final HttpClient httpClient;
    public PokemonRestAPIService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }
    @Override
    public List<HashMap<String, Object>> getPokemons(int limit) {
        String URL = POKE_API_BASE_URL + POKEMON_URL + "limit/" + limit;

        List<HashMap<String, Object>> pokemons = new ArrayList<>();

        if(limit <= POKEMON_MIN) {
            pokemons = this.httpClient.toBlocking().retrieve(URL, List.class);
        } else {
            //pokemons = Fl
        }

        return pokemons;
    }

    @Override
    public List<HashMap<String, Object>> getPokemonList() {
        return getPokemons(POKEMON_MAX);
    }

    @Override
    public HashMap<String, Object> getPokemon(String name) {
        String URL = POKE_API_BASE_URL + POKEMON_URL + name;
        HashMap<String, Object> pokemon = this.httpClient.toBlocking().retrieve(URL, HashMap.class);
        return pokemon;
    }

    @Override
    public List<HashMap<String, Object>> getPokemonsByType(String type) {
        String URL = POKE_API_BASE_URL + POKEMON_URL + "type/" + type;
        List<HashMap<String, Object>> pokemons = this.httpClient.toBlocking().retrieve(URL, List.class);
        return pokemons;
    }

    @Override
    public List<String> getPokemonsName(int limit) {
        List<String> pokemonsNames = getPokemons(limit).stream().map((pokemon) -> pokemon.get("name").toString()).toList();
        return pokemonsNames;
    }

    @Override
    public List<String> getPokemonsNameByType(String type) {
        List<String> pokemonsNamesByType = getPokemonsByType(type).stream().map((pokemon) -> pokemon.get("name").toString()).toList();
        return pokemonsNamesByType;
    }

    @Override
    public List<HashMap<String, Object>> getRandomPokemonList() {
        String URL = POKE_API_BASE_URL + RANDOM_TEAM_URL + "/";
        List<HashMap<String, Object>> randomPokemons = httpClient.toBlocking().retrieve(URL, List.class);
        return randomPokemons;
    }

    @Override
    public List<String> getRandomPokemonNameList() {
        return getRandomPokemonList().stream().map((pokemon) -> pokemon.get("name").toString()).toList();
    }


    @Override
    public List<HashMap<String, Object>> getRandomSuggestList() {
        String URL = POKE_API_BASE_URL + RANDOM_TEAM_URL + "/suggest";
        List<HashMap<String, Object>> randomPokemons = httpClient.toBlocking().retrieve(URL, List.class);
        return randomPokemons;
    }

    @Override
    public List<String> getPokemonWithPowerThan(int attack) {
        return getPokemons(POKEMON_MIN).stream()
                .filter(pokemon -> PokemonFilterUtils.searchByPoweredThanValue(pokemon, attack))
                .map((pokemon) -> pokemon.get("name").toString())
                .toList();
    }


}
