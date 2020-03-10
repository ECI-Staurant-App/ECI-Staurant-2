package edu.eci.arsw.ecistaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class EciStaurantApplication {

    public static void main(String[] args) {
        SpringApplication.run(EciStaurantApplication.class, args);

    }

}