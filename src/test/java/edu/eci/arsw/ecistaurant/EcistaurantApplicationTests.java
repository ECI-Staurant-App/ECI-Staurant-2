package edu.eci.arsw.ecistaurant;


import edu.eci.arsw.ecistaurant.model.Estudiante;
import edu.eci.arsw.ecistaurant.model.Restaurante;
import edu.eci.arsw.ecistaurant.persistence.RestaurantRepository;
import edu.eci.arsw.ecistaurant.persistence.StudentRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EcistaurantApplicationTests extends TestCase
{
	@Autowired
	private StudentRepository repoEst;
	@Autowired
	private RestaurantRepository repoRest;

	@Test
	public void deberiaInsertarEstudiante() {
		Estudiante est = new Estudiante();
		List<Estudiante> estudianteList = repoEst.findAll();
		if (estudianteList.isEmpty()){
			est.setCarne(2146190);
		}else{
			Estudiante ultimoEstudiante = estudianteList.get(estudianteList.size()-1);
			est.setCarne(ultimoEstudiante.getCarne()+1);
		}
		est.setEmail("estudianteprueba@mail.escuela.co");
		est.setName("EstPrueba");
		est.setPasswd("12345");
		est.setSaldo(10000);
		Estudiante nuevo = repoEst.save(est);
		assertEquals(nuevo.getCarne(), (est.getCarne()));
	}

	@Test
	public void deberiaInsertarRestaurante(){
		Restaurante  restaurante = new Restaurante();
		restaurante.setNombre("KIOSKO_PRUEBA");
		Restaurante nuevo = repoRest.save(restaurante);
		assertTrue(nuevo.getNombre().equalsIgnoreCase(restaurante.getNombre()));
	}



}
