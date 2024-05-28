package fr.epita.di.conf;

import fr.epita.di.services.api.IPatientDAO;
import fr.epita.di.services.api.IService;
import fr.epita.di.services.impl.H2Service;
import fr.epita.di.services.impl.PatientDAODerbyImpl;
import fr.epita.di.services.impl.PatientDAOH2Impl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public IService initializeService() {
        //  return new Service(// do all the config required
        //        );
        //  return new MySQLService();
        //return new Neo4JService();
        return new H2Service();
    }


    @Bean
    public IPatientDAO initializePatientDAO() throws Exception {
      // return new PatientDAOH2Impl();
        return new PatientDAODerbyImpl();
    }
}
