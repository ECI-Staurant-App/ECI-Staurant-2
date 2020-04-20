package edu.eci.arsw.ecistaurant.cache;

import edu.eci.arsw.ecistaurant.model.Restaurante;


public interface RestaurantCache {

    Restaurante getRestaurante (String restaurante);

    void saveRestaurante(Restaurante restaurante);

    Long tiempoEnCache(String restaurante);

    boolean estaEnCache(String restaurante);

}
