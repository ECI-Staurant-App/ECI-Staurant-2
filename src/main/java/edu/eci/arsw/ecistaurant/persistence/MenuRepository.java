package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    List<Menu> findAllByRestaurante(String restaurante);

    Menu save(Menu menu);

    Optional<Menu> findById(int id);

    Optional<Menu> findByNombre(String menu);

}
