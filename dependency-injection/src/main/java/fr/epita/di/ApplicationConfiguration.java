package fr.epita.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public IService initializeService(){
      //  return new Service(// do all the config required
        //        );
      //  return new MySQLService();
        return new Neo4JService();
    }
}
