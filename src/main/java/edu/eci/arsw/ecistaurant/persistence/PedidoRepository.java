package edu.eci.arsw.ecistaurant.persistence;

import edu.eci.arsw.ecistaurant.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    List<Pedido> findAll();
    @Query(value = "SELECT * from pedido where id_pedido = ?1",nativeQuery = true)
    Optional<Pedido> findById(int idPedido);

    Pedido save(Pedido pedido);
    @Query(value = "SELECT * from pedido where id_pedido = ?1 AND restaurante = (SELECT id_restaurante from restaurante where nombre=?2 ) ",nativeQuery = true)
    Optional<Pedido> findByIdAndRestaurante(int id, String restaurante);

    @Query(value = "SELECT * from pedido where estudiante = ?1 ORDER BY fecha DESC ",nativeQuery = true)
    List<Pedido> findAllByUsuario(int carne);

    @Query(value = "SELECT * from pedido ORDER BY fecha DESC ",nativeQuery = true)
    List<Pedido> findAllByFecha();

    @Query(value="SELECT * from pedido WHERE pedido.restaurante = (SELECT id_restaurante FROM restaurante WHERE nombre= ?1 LIMIT 1)",nativeQuery = true)
    List<Pedido> findOrOrderByRestaurante(String restaurate);

    @Query(value ="SELECT * from pedido WHERE estudiante=(SELECT carne from usuario WHERE email=?1) order by fecha desc limit 1" ,nativeQuery = true)
    Optional<Pedido> findLastOrderOfUser(String usuario);
}
