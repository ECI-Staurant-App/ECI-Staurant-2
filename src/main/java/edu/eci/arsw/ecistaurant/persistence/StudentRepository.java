package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Estudiante,Integer>{

    List<Estudiante> findAll();

    Optional<Estudiante> findByCarne(int carne);

}
