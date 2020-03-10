package edu.eci.arsw.ecistaurant;

import edu.eci.arsw.ecistaurant.controllers.EcistaurantController;
import edu.eci.arsw.ecistaurant.model.Estudiante;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan
public class EciStaurantApplication {


	public static void main(String[] args) {
		SpringApplication.run(EciStaurantApplication.class, args);


	}

}
