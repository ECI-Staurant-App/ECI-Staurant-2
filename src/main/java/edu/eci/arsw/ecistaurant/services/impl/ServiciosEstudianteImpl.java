package edu.eci.arsw.ecistaurant.services.impl;

import edu.eci.arsw.ecistaurant.model.*;
import edu.eci.arsw.ecistaurant.persistence.*;
import edu.eci.arsw.ecistaurant.services.ServiciosEstudiante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    MenuRepository menuRepository;
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
    public Usuario getUserByEmail(String user) throws EcistaurantPersistenceException {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(user);
        if (!optionalUsuario.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.STUDENT_NOT_FOUND);
        }
        return optionalUsuario.get();
    }

    @Override
    public Pedido realizarPedido(String correo,String restaurante,String menu) throws EcistaurantPersistenceException {

        Pedido pedido = new Pedido();
        pedido.setUsuario(getUserByEmail(correo));
        pedido.setRestaurante(restaurantRepository.findByNombre(restaurante));
        pedido.setMenu(findMenuByName(menu));
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
    public Menu findMenuByName(String menu) throws EcistaurantPersistenceException {
        Optional<Menu> optionalMenu = menuRepository.findByNombre(menu);
        if (!optionalMenu.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.MENU_NOT_FOUND);
        }
        return optionalMenu.get();
    }

    @Override
    public List<Mesa> buscarMesas() {
        return mesaRepository.findAll();
    }

    @Override
    public List<Mesa> buscarMesasDisponibles() {
        return mesaRepository.findAllByEstaDisponible() ;
    }

    @Override
    public List<Menu> getAllMenuByRestaurant(String restaurante) {
        Restaurante restaurantSelected = restaurantRepository.findByNombre(restaurante);
        return menuRepository.findAllByRestaurante(restaurantSelected.getNombre());
    }

}
