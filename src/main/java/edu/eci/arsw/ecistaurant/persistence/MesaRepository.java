package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MesaRepository extends JpaRepository<Mesa,Integer> {

    List<Mesa> findAll();

    Optional<Mesa> findById(int id);
}
