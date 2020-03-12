package edu.eci.arsw.ecistaurant.services;
import edu.eci.arsw.ecistaurant.model.Usuario;

import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;

import java.util.List;


public interface ServiciosEstudiante {

    List<Usuario> getAllStudents() throws EcistaurantPersistenceException;

    void saveStudent(Usuario student) throws EcistaurantPersistenceException;

    Usuario getStudentById(int carne) throws EcistaurantPersistenceException;

    Pedido realizarPedido(Pedido pedido) throws EcistaurantPersistenceException;
}
