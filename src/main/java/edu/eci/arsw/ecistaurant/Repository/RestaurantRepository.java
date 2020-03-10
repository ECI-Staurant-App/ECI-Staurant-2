package edu.eci.arsw.EciStaurant.Repository;

import edu.eci.arsw.EciStaurant.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurante,Integer> {
}
