package com.example.pokemonapi;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import java.util.HashMap;
import java.util.List;


@Controller("api/v1/pokemon/")
public class PokemonController {

    private final PokemonService<HashMap<String, Object>> pokemonService;

    public PokemonController(PokemonService<HashMap<String, Object>> pokemonService) {
        this.pokemonService = pokemonService;
    }


    @Get("list/{limit}")
    HttpResponse<List<HashMap<String, Object>>> getPokemons(int limit) {
        return HttpResponse.ok(pokemonService.getPokemons(limit));
    }


    @Get("{name}")
    HttpResponse<HashMap<String, Object>> getPokemon(String name) {
        return HttpResponse.ok(pokemonService.getPokemon(name));
    }

    @Get("type/{type}")
    HttpResponse<List<HashMap<String, Object>>> getPokemonsByType(String type) {
        return HttpResponse.ok(pokemonService.getPokemonsByType(type));
    }


    @Get("list/name/{limit}")
    HttpResponse<List<String>> getPokemonsName(@PathVariable(name = "limit", defaultValue = "100") int limit) {
        return HttpResponse.ok(pokemonService.getPokemonsName(limit));
    }


    @Get("teams/random")
    HttpResponse<List<HashMap<String, Object>>> getRandomPokemons() {
        return HttpResponse.ok(pokemonService.getRandomPokemonList());
    }


    @Get("list")
    HttpResponse<List<HashMap<String, Object>>> getPokemonList() {
        return HttpResponse.ok(pokemonService.getPokemonList());
    }


    @Get("list/name")
    HttpResponse<List<String>> getPokemonNameList() {
        return HttpResponse.ok(pokemonService.getRandomPokemonNameList());
    }


    @Get("list/powerful/{power}")
    HttpResponse<List<String>> getPowerfulPokemonList(int power) {
        return HttpResponse.ok(pokemonService.getPokemonWithPowerThan(power));
    }
}
