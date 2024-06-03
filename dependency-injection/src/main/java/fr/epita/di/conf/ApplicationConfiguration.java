package fr.epita.di.conf;

import fr.epita.di.services.api.IPatientDAO;
import fr.epita.di.services.api.IService;
import fr.epita.di.services.impl.H2Service;
import fr.epita.di.services.impl.PatientDAODerbyImpl;
import fr.epita.di.services.impl.PatientDAOH2Impl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
public class ApplicationConfiguration {

    private static final Logger LOGGER = LogManager.getLogger(ApplicationConfiguration.class);

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
        LOGGER.debug("about to load the dao instance");
      // return new PatientDAOH2Impl();
        PatientDAODerbyImpl dao = new PatientDAODerbyImpl();
        LOGGER.debug("returning this instance {}", dao);
        return dao;
    }
}
