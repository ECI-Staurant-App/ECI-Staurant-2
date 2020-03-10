package edu.eci.arsw.EciStaurant;

import edu.eci.arsw.EciStaurant.controllers.EcistaurantController;
import edu.eci.arsw.EciStaurant.model.Estudiante;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = {"ecistaurant"})
public class EciStaurantApplication {


	public static void main(String[] args) {

		/*Estudiante estudiante = new Estudiante(2146195, "johan", 15000, "johan.arias", "holamundo");
		EcistaurantController controller = new EcistaurantController();
		controller.InsertarEstudiante(estudiante);*/
		SpringApplication.run(EciStaurantApplication.class, args);


	}

}
