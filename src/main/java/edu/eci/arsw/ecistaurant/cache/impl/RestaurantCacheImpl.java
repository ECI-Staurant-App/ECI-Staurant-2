package edu.eci.arsw.ecistaurant.cache.impl;

import edu.eci.arsw.ecistaurant.cache.RestaurantCache;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class RestaurantCacheImpl implements RestaurantCache {

    ConcurrentHashMap<String, Restaurante> restaurantes;

    ConcurrentHashMap<String , Long> tiempoCacheByRestaurant;

    public RestaurantCacheImpl() {
        restaurantes = new ConcurrentHashMap<>();
        tiempoCacheByRestaurant = new ConcurrentHashMap<>();
    }

    @Override
    public Restaurante getRestaurante(String restaurante) {
        return restaurantes.get(restaurante);
    }

    @Override
    public void saveRestaurante(Restaurante restaurante) {
        restaurantes.put(restaurante.getNombre(),restaurante);
    }

    @Override
    public Long tiempoEnCache(String restaurante) {
        return null;
    }

    @Override
    public boolean estaEnCache(String restaurante) {
        return restaurantes.containsKey(restaurante);
    }
}
