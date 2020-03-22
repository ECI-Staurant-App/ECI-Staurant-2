package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MesaRepository extends JpaRepository<Mesa,Integer> {

    List<Mesa> findAll();

    Mesa save(Mesa mesa);

    Optional<Mesa> findById(int id);

    @Query(value = "SELECT * from mesa where esta_disponible = true",nativeQuery = true)
    List<Mesa> findAllByEstaDisponible();
}
