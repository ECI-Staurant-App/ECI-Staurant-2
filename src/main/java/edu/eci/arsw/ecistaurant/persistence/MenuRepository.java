package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu,Integer> {

    List<Menu> findAll();

    Optional<Menu> findById(int id);
}
