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

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Commit
public class TestJPA1 {

    @Autowired
    DataService service;

    @Autowired
    DataSource ds;


    @Test
    @Transactional
    public void testCreation() {
        Passenger passenger = new Passenger();
        passenger.setName("Jack");
        passenger.setGender("M");
        passenger.setPassengerClass("3rd");

        //when
        service.create(passenger);

        //then
        try (
                ResultSet rs = ds.getConnection().prepareStatement("select * from PASSENGER").executeQuery();
        ) {
            while (rs.next()) {
                Assertions.assertThat(rs.getString("name")).isEqualTo("Jack");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("should not have an exception");
        }
    }

}
