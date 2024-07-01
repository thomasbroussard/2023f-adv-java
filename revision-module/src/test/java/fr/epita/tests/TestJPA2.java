package fr.epita.tests;

import fr.epita.titanic.ApplicationConfiguration;
import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.DataService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Commit
public class TestJPA2 {

    @Autowired
    DataService service;

    @Autowired
    DataSource ds;

    @PersistenceContext
    EntityManager em;



    @Test
    @Transactional
    public void testWithTutor() {
        Passenger passenger = new Passenger();
        passenger.setName("Jack");
        passenger.setGender("M");
        passenger.setPassengerClass("3rd");

        Passenger tutor = new Passenger();
        tutor.setName("Rose");
        tutor.setGender("F");
        tutor.setPassengerClass("1st");
        passenger.setLegalTutor(tutor);


        //when
        //List.of(passenger,tutor).forEach(service::create);
        service.create(tutor);
        service.create(passenger);



        //then
        try (
                Connection connection = ds.getConnection();
        ) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from PASSENGER where id = ?");
            preparedStatement.setInt(1, passenger.getId());
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Assertions.assertThat(rs.getString("name")).isEqualTo("Jack");
                Assertions.assertThat(rs.getString("legalTutor")).isNotEqualTo(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("should not have an exception");
        }

        //then 2
        List<Passenger> list = em.createQuery("from Passenger p").getResultList();
        System.out.println(list);

    }
}
