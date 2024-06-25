package fr.epita.di.conf;

import fr.epita.di.services.api.IPatientDAO;
import fr.epita.di.services.api.IService;
import fr.epita.di.services.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ComponentScan(basePackages = "fr.epita.di.services.impl")
@EnableTransactionManagement
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


    //@Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        DriverManagerDataSource dataSource = getDriverManagerDataSource();
        sessionFactoryBean.setDataSource(dataSource);
        Properties hibernateProperties = hibernateProperties();
        sessionFactoryBean.setHibernateProperties(hibernateProperties);
        sessionFactoryBean.setPackagesToScan("fr.epita.di.datamodel");
        return sessionFactoryBean;
    }

    @Bean
    public DriverManagerDataSource getDriverManagerDataSource() {
        return new DriverManagerDataSource("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "test", "test");
    }

    private static Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create");
        return hibernateProperties;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("fr.epita.di.datamodel"); // your package for entity classes
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());
        return em;
    }

    @Bean public JPADoctorDAO getDoctorJPADAO(){
        return new JPADoctorDAO();
    }

    @Bean
    public HibernatePatientDAO hibernatePatientDAO(){
        return new HibernatePatientDAO();
    }


    @Bean
    public HibernateDoctorDAO getDoctorDAO(SessionFactory sf) throws Exception {
        return new HibernateDoctorDAO(sf);
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }


}
