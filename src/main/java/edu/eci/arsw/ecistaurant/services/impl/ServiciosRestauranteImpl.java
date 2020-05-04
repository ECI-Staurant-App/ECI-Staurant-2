package edu.eci.arsw.ecistaurant.services.impl;

import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.*;
import edu.eci.arsw.ecistaurant.services.ServiciosRestaurante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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


    @Override
    @Cacheable(value = "restaurantCache")
    public List<Restaurante> getAllRestaurants(){
        return restaurantRepo.findAll();
    }

    @Override
    @CacheEvict(value = "restaurantCache" , allEntries = true)
    public void saveRestaurant(String restaurante) throws EcistaurantPersistenceException {
        Optional<Restaurante> optionalRestaurante = restaurantRepo.findByNombre(restaurante);
        if (optionalRestaurante.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.RESTAURANT_REGISTERED);
        }
        Restaurante rest = new Restaurante();
        rest.setNombre(restaurante);
        restaurantRepo.save(rest);
    }

    @Override
    public Restaurante getRestaurantByName(String restaurante) throws EcistaurantPersistenceException {
        Optional<Restaurante> optionalRestaurante = restaurantRepo.findByNombre(restaurante);
        if (!optionalRestaurante.isPresent())
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.RESTAURANT_NOT_FOUND);
        return optionalRestaurante.get();
    }

    @Override
    public void changeOrderState(String estado,int pedido, String restaurante) throws EcistaurantPersistenceException {
        try{
            Pedido order = getPedidoByIdAndRestaurant(pedido,restaurante);
            order.setEstado(estado);
            pedidoRepository.save(order);
        }catch (EcistaurantPersistenceException e){
            throw new EcistaurantPersistenceException(e.getMessage());
        }
    }

    @Override
    public Pedido getPedidoByIdAndRestaurant(int id,String restaurante) throws EcistaurantPersistenceException {
        Optional<Pedido> optionalPedido = pedidoRepository.findByIdAndRestaurante(id,restaurante);
        if (!optionalPedido.isPresent())
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.PEDIDO_NOT_FOUND);
        return optionalPedido.get();
    }

    @Override
    @CacheEvict(value = "menusCache" , allEntries = true)
    public void saveMenu(String restaurante,String menu,int precio) throws EcistaurantPersistenceException {
        Optional<Menu> optionalMenu = menuRepository.findByNombre(menu);
        Optional<Restaurante> optionalRestaurante = restaurantRepo.findByNombre(restaurante);
        if (! optionalRestaurante.isPresent()) {
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.RESTAURANT_NOT_FOUND);
        }
        if (optionalMenu.isPresent()){
            throw new EcistaurantPersistenceException(EcistaurantPersistenceException.MENU_REGISTERED);
        }else
        {
            Restaurante rest = optionalRestaurante.get();
            Menu nuevo = new Menu();
            nuevo.setRestaurante(rest);
            nuevo.setNombre(menu);
            nuevo.setPrecio(precio);
            menuRepository.save(nuevo);
        }
    }

    @Override
    @Cacheable(value="menusCache")
    public List<Menu> getMenusByrestaurant(String restaurante) throws EcistaurantPersistenceException {

        try{
            getRestaurantByName(restaurante);
        }catch (EcistaurantPersistenceException e){
            throw new EcistaurantPersistenceException(e.getMessage());
        }
        return menuRepository.findAllByRestaurante(restaurante);
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
    @Cacheable(value = "pedidosCache")
    public List<Pedido> getPedidosByRestaurant(String restaurant) throws EcistaurantPersistenceException {

        try{
            getRestaurantByName(restaurant);
        }catch (EcistaurantPersistenceException e){
            throw new EcistaurantPersistenceException(e.getMessage());
        }
        return pedidoRepository.findOrOrderByRestaurante(restaurant);
    }






}
