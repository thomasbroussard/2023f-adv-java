package fr.epita.titanic.app;


import fr.epita.titanic.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
public class TitanicApp {

    public static void main(String[] args) {
        SpringApplication.run(TitanicApp.class, args);
    }
}
