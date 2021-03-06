package edu.eci.arsw.ecistaurant;
import com.google.common.base.Predicate;
import edu.eci.arsw.ecistaurant.model.Countdown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
@ComponentScan
@EnableCaching
public class EciStaurantApplication implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(EciStaurantApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        //kiosko1
        String password = "12345";
        String passwordBcrypt = passwordEncoder.encode(password);
        System.out.println(passwordBcrypt);
        //kiosko2
        String password1 = "1234";
        String passwordBcrypt1 = passwordEncoder.encode(password1);
        System.out.println(passwordBcrypt1);
        //restaurante
        String password2 = "123456";
        String passwordBcrypt2 = passwordEncoder.encode(password2);
        System.out.println(passwordBcrypt2);
    }

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // .paths(PathSelectors.ant("/users/*"))
                .paths(ecistaurantPaths())
                .apis(RequestHandlerSelectors.basePackage("edu.eci.arsw.ecistaurant"))
                .build();
    }


    private Predicate<String> ecistaurantPaths() {
        return or(
                regex("/users.*"),
                regex("/restaurants.*")
        );
    }

}