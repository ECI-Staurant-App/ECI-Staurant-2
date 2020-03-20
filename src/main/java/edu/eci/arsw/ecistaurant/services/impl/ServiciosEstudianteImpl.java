package edu.eci.arsw.ecistaurant.services.impl;

import edu.eci.arsw.ecistaurant.model.Mesa;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.persistence.*;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ServiciosEstudianteImpl implements ServiciosEstudiante {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    PlatilloRepository platilloRepository;
    @Autowired
    MesaRepository mesaRepository;

    @Override
    public List<Usuario> getAllStudents() throws EcistaurantPersistenceException {
        return usuarioRepository.findAll();
    }

    @Override
    public void saveStudent(Usuario user) throws EcistaurantPersistenceException {
        Optional<Usuario> optionalUser = usuarioRepository.findByCarne(user.getCarne());
        if (optionalUser.isPresent()) {
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.STUDENT_REGISTERED);
        } else {
            usuarioRepository.save(user);
        }


    }

    @Override
    public Usuario getStudentById(int carne) throws EcistaurantPersistenceException {
        Optional<Usuario> optionalEstudiante = usuarioRepository.findByCarne(carne);
        if (!optionalEstudiante.isPresent())
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.STUDENT_NOT_FOUND);
        return optionalEstudiante.get();
    }

    @Override
    public Pedido realizarPedido(int usuario,String restaurante,String platillo) throws EcistaurantPersistenceException {

        Pedido pedido = new Pedido();
        pedido.setUsuario(getStudentById(usuario));
        pedido.setRestaurante(restaurantRepository.findByNombre(restaurante));
        pedido.setPlatillo(platilloRepository.findByNombre(platillo));
        Date date = new Date();
        System.out.println(date);
        pedido.setFecha(date);
        return pedidoRepository.save(pedido);
    }

    @Override
    public void actualizarSaldo(Usuario usuario) throws EcistaurantPersistenceException {

        Usuario usuario1 = getStudentById(usuario.getCarne());
        usuario1.setSaldo(usuario.getSaldo());

    }

    @Override
    public List<Mesa> buscarMesas() {
        return mesaRepository.findAll();
    }

    @Override
    public List<Mesa> buscarMesasDisponibles() {
        return mesaRepository.findAllByEstaDisponible() ;
    }

}
