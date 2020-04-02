package edu.eci.arsw.ecistaurant.services;
import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Mesa;
import edu.eci.arsw.ecistaurant.model.Usuario;

import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;

import java.util.List;


public interface ServiciosEstudiante {

    List<Usuario> getAllStudents() throws EcistaurantPersistenceException;

    void saveStudent(Usuario usuario) throws EcistaurantPersistenceException;

    Usuario getStudentById(int carne) throws EcistaurantPersistenceException;

    Usuario getUserByEmail(String user) throws EcistaurantPersistenceException;

    Pedido realizarPedido(String email, String restaurante, String platillo) throws EcistaurantPersistenceException;

    void actualizarSaldo(Usuario usuario) throws EcistaurantPersistenceException;

    Menu findMenuByName(String menu) throws EcistaurantPersistenceException;

    List<Mesa> buscarMesas();

    List<Mesa> buscarMesasDisponibles();

    List<Menu> getAllMenuByRestaurant(String restaurante);
}
