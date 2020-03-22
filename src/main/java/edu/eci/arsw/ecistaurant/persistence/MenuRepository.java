package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {

    @Query(value = "SELECT * from menu WHERE fk_restaurante = :restaurante",nativeQuery = true)
    List<Menu> findAllByRestaurante(@Param("restaurante") int restaurante);

    Menu save(Menu menu);

    Optional<Menu> findById(int id);

    Optional<Menu> findByNombre(String menu);

}
