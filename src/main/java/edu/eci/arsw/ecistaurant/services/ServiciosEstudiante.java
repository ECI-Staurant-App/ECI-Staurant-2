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

    void actualizarSaldo(String user,int saldo) throws EcistaurantPersistenceException;

    Menu findMenuByName(String menu) throws EcistaurantPersistenceException;

    Pedido getLastOrderOfUser(String email) throws  EcistaurantPersistenceException;

    void seleccionarMesa(int id) throws EcistaurantPersistenceException;

    List<Mesa> buscarMesas();

    List<Mesa> buscarMesasDisponibles();

    List<Menu> getAllMenuByRestaurant(String restaurante);

    void cambiarEstadoMesa(int mesa, Boolean state) throws EcistaurantPersistenceException;

    int getSaldoUser(String correo) throws EcistaurantPersistenceException;
}
