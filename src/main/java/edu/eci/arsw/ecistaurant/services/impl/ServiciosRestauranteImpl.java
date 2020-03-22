package edu.eci.arsw.ecistaurant.services.impl;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.*;
import edu.eci.arsw.ecistaurant.services.ServiciosRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiciosRestauranteImpl implements ServiciosRestaurante {

    @Autowired
    private RestaurantRepository restaurantRepo;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private MenuRepository menuRepository;
    //@Autowired
   // private PlatilloRepository platilloRepository;

    @Override
    public List<Restaurante> getAllRestaurants() throws EcistaurantPersistenceException {
        return restaurantRepo.findAll();
    }

    @Override
    public void saveRestaurant(Restaurante restaurante) throws EcistaurantPersistenceException {
        Optional<Restaurante> optionalRestaurante = restaurantRepo.findById(restaurante.getIdRestaurante());
        if (optionalRestaurante.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.RESTAURANT_REGISTERED);
        }else
        {
            restaurantRepo.save(restaurante);
        }
    }

    @Override
    public Restaurante getRestaurantById(int id) throws EcistaurantPersistenceException {
        Optional<Restaurante> optionalRestaurante = restaurantRepo.findById(id);
        if (!optionalRestaurante.isPresent())
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.STUDENT_NOT_FOUND);
        return optionalRestaurante.get();
    }

    @Override
    public List<Pedido> getAllPedidos(){
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido getPedidoById(int id) throws EcistaurantPersistenceException {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (!pedidoOptional.isPresent())
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.PEDIDO_NOT_FOUND);
        return pedidoOptional.get();

    }

    @Override
    public void saveMenu(String menu,int precio) throws EcistaurantPersistenceException {
        Optional<Menu> optionalMenu = menuRepository.findByNombre(menu);
        if (optionalMenu.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.MENU_REGISTERED);
        }else
        {
            optionalMenu.get().setPrecio(precio);
            menuRepository.save(optionalMenu.get());
        }
    }

    @Override
    public List<Pedido> getPedidosByUser(int user) {
        return pedidoRepository.findAllByUsuario(user);
    }

    @Override
    public List<Pedido> getPedidosByFecha() {
        return pedidoRepository.findAllByFecha();
    }

    @Override
    public List<Menu> getAllMenuByRestaurant(String restaurante) {
        return menuRepository.findAllByRestaurante(restaurante);
    }

    /*@Override
    public void savePlatillo(Platillo platillo) throws EcistaurantPersistenceException {
        Optional<Platillo> optionalPlatillo = platilloRepository.findById(platillo.getIdPlatillo());
        if (optionalPlatillo.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.PLATILLO_REGISTERED);
        }else
        {
            platilloRepository.save(platillo);
        }
    }

    @Override
    public List<Platillo> getAllPlatillos() throws EcistaurantPersistenceException {
        return platilloRepository.findAll();
    }

    @Override
    public Platillo getPlatilloById(int id) throws EcistaurantPersistenceException {
        Optional<Platillo> optionalPlatillo= platilloRepository.findById(id);
        if (!optionalPlatillo.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.PLATILLO_NOT_FOUND);
        }else
        {
            return optionalPlatillo.get();
        }
    }
*/

}
