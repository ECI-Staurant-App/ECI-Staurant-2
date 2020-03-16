package edu.eci.arsw.ecistaurant.services.impl;

import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.persistence.PedidoRepository;
import edu.eci.arsw.ecistaurant.persistence.UsuarioRepository;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiciosEstudianteImpl implements ServiciosEstudiante {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public List<Usuario> getAllStudents() throws EcistaurantPersistenceException {
        return usuarioRepository.findAll();
    }

    @Override
    public void saveStudent(Usuario student) throws EcistaurantPersistenceException {
        Optional<Usuario> optionalStudent = usuarioRepository.findByCarne(student.getCarne());
        if (optionalStudent.isPresent()) {
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.STUDENT_REGISTERED);
        } else {
            usuarioRepository.save(student);
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
    public Pedido realizarPedido(Pedido pedido) throws EcistaurantPersistenceException {

        pedido.setUsuario(pedido.getUsuario());
        pedido.setRestaurante(pedido.getRestaurante());
        pedido.setPlatillo(pedido.getPlatillo());
        pedido.setFecha(pedido.getFecha());
        return pedidoRepository.save(pedido);
    }

    @Override
    public void actualizarSaldo(Usuario usuario) throws EcistaurantPersistenceException {

        Usuario usuario1 = getStudentById(usuario.getCarne());
        usuario1.setSaldo(usuario.getSaldo());

    }

}
