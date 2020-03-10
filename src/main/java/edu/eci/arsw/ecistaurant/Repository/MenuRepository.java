package edu.eci.arsw.ecistaurant.Repository;

import edu.eci.arsw.ecistaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
