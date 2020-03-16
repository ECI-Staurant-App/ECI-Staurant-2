/*
package edu.eci.arsw.ecistaurant;



import edu.eci.arsw.ecistaurant.model.Usuario;
import edu.eci.arsw.ecistaurant.model.Pedido;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.EcistaurantPersistenceException;
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


	@Test
	public void deberiaInsertarEstudiante() {
		Usuario est = new Usuario();
		List<Usuario> usuarioList = repoEst.findAll();
		if (usuarioList.isEmpty()){
			System.out.println("holaaaaaaAAAAA11 ");
			est.setCarne(2146190);
		}else{
			Usuario ultimoUsuario = usuarioList.get(usuarioList.size()-1);
			est.setCarne(ultimoUsuario.getCarne()+1);
		}
		Random num = new Random();
		int generator = num.nextInt();
		est.setEmail("estudianteprueba"+ generator +"@mail.escuela.co");
		est.setName("EstPrueba");
		est.setPasswd("12345");
		est.setSaldo(10000);
		Usuario nuevo = repoEst.save(est);
		assertEquals(nuevo.getCarne(), (est.getCarne()));
	}

	@Test
	public void deberiaInsertarRestaurante(){
		Restaurante  restaurante = new Restaurante();
		List<Restaurante> restaurantes = repoRest.findAll();
		if (restaurantes.isEmpty()) {
			restaurante.setIdRestaurante(1);
		}
		restaurante.setNombre("KIOSKO_PRUEBA");
		Restaurante nuevo = repoRest.save(restaurante);
		assertTrue(nuevo.getNombre().equalsIgnoreCase(restaurante.getNombre()));
	}

	@Test
	public void deberiaRegistrarPedido(){
		Pedido pedido = new Pedido();
		try {
			pedido.setUsuario(studentServices.getStudentById(2146190));
			pedido.setRestaurante(restaurantServices.getRestaurantById(1));
			pedido.setPlatillo(restaurantServices.getPlatilloById(10));
			SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
			Date newDate = dateformat.parse("02-04-2020 13:35:42");
			pedido.setFecha(newDate);
			studentServices.realizarPedido(pedido);
		} catch (EcistaurantPersistenceException | ParseException e) {
			e.printStackTrace();
		}
	}



}
*/