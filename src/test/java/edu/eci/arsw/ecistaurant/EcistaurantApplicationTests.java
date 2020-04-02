
package edu.eci.arsw.ecistaurant;
import edu.eci.arsw.ecistaurant.model.Menu;
import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
import edu.eci.arsw.ecistaurant.persistence.MenuRepository;
import edu.eci.arsw.ecistaurant.persistence.RestaurantRepository;
import edu.eci.arsw.ecistaurant.persistence.UsuarioRepository;
import edu.eci.arsw.ecistaurant.services.impl.ServiciosEstudianteImpl;
import edu.eci.arsw.ecistaurant.services.impl.ServiciosRestauranteImpl;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EcistaurantApplicationTests extends TestCase
{

	@Autowired
	private UsuarioRepository repoEst;
	@Autowired
	private RestaurantRepository repoRest;
	@Autowired
	private ServiciosEstudianteImpl studentServices;
	@Autowired
	private ServiciosRestauranteImpl restaurantServices;
	@Autowired
	private MenuRepository menuRepository;

	@Test
	public void deberiaInsertarEstudiante() {
		Usuario est = new Usuario();
		List<Usuario> usuarioList = repoEst.findAll();
		if (usuarioList.isEmpty()){
			est.setCarne(2146190);
		}else{
			Usuario ultimoUsuario = usuarioList.get(usuarioList.size()-1);
			est.setCarne(ultimoUsuario.getCarne()+1);
		}
		Random num = new Random();
		int generator = num.nextInt(1000);
		est.setEmail("estudianteprueba"+ generator +"@mail.escuela.co");
		est.setEnabled(true);
		est.setName("EstPrueba");
		est.setPasswd("12345");
		est.setSaldo(10000);
		Usuario nuevo = repoEst.save(est);
		assertEquals(nuevo.getCarne(), (est.getCarne()));
	}

	@Test
	public void deberiaInsertarRestaurante(){
		Random num = new Random();
		int generator = num.nextInt(1000);
		Restaurante  restaurante = new Restaurante();
		List<Restaurante> restaurantes = repoRest.findAll();
		if (restaurantes.isEmpty()) {
			restaurante.setIdRestaurante(1);
		}
		restaurante.setNombre("KIOSKO_PRUEBA"+generator);
		Restaurante nuevo = repoRest.save(restaurante);
		assertTrue(nuevo.getNombre().equalsIgnoreCase(restaurante.getNombre()));
	}

	@Test
	public void deberiaRegistrarPedido(){
		try {
			Pedido pedido = new Pedido();
			List<Pedido> pedidos = restaurantServices.getAllPedidos();
			int pedidosAntes = pedidos.size();
			int pedidosDespues;
			if (pedidos.isEmpty()) {
				studentServices.realizarPedido("johan.arias@mail", "kiosko1", "Bandeja paisa");
			} else {
				Random num = new Random();
				List<Menu> menus = menuRepository.findAll();
				int generatorMenu = num.nextInt(menus.size()-1);
				String menuAleatorio = menus.get(generatorMenu).getNombre();
				List<Restaurante> restaurantes = restaurantServices.getAllRestaurants();
				int generatorRestaurant = num.nextInt(restaurantes.size()-1);
				String restauranteAleatorio = restaurantes.get(generatorRestaurant).getNombre();
				studentServices.realizarPedido("estudianteprueba131@mail.escuela.co",restauranteAleatorio,menuAleatorio);
			}
			pedidosDespues = restaurantServices.getAllPedidos().size();
			assertEquals(pedidosAntes + 1 , pedidosDespues);
		} catch (EcistaurantPersistenceException e) {
			System.out.println(e.getMessage());
		}
	}



}
