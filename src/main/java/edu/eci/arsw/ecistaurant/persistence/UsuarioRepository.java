package edu.eci.arsw.ecistaurant.persistence;

//import edu.eci.arsw.ecistaurant.model.Platillo;
import edu.eci.arsw.ecistaurant.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer>{

    List<Usuario> findAll();

    Optional<Usuario> findByCarne(int carne);

    Usuario save(Usuario usuario);

    Optional<Usuario> findByEmail(String email);

}
