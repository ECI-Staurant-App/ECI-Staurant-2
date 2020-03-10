package edu.eci.arsw.ecistaurant.services.impl;

import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.persistence.RestaurantRepository;
import edu.eci.arsw.ecistaurant.services.ServiciosRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiciosRestauranteImpl implements ServiciosRestaurante {

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Override
    public List<Restaurante> getAllRestaurants() throws EcistaurantPersistenceException {
        return restaurantRepo.findAll();
    }

    @Override
    public void saveRestaurant(Restaurante restaurante) throws EcistaurantPersistenceException {
        restaurantRepo.save(restaurante);
    }

    @Override
    public Restaurante getRestaurantById(int id) throws EcistaurantPersistenceException {
        Optional<Restaurante> optionalRestaurante = restaurantRepo.findById(id);
        if (!optionalRestaurante.isPresent())
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.STUDENT_NOT_FOUND);
        return optionalRestaurante.get();
    }
}
