import fr.epita.di.Patient;
import fr.epita.di.conf.ApplicationConfiguration;
import fr.epita.di.services.api.IPatientDAO;
import fr.epita.di.services.api.IService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestSpringFramework {

    private static final Logger LOGGER = LogManager.getLogger(TestSpringFramework.class);

    @Autowired
    IService service;

    @Autowired
    IPatientDAO dao;

    @Test
    public void test(){
        System.out.println(service.getClass());
    }

    @Test
    public void testDB() throws Exception {
        //given
        Patient patient = new Patient();
        patient.setName("toto");

        System.out.println(patient.getId());
        //when
        dao.create(patient);

       LOGGER.info(patient.getId());

        //then
        Patient read = dao.read(patient.getId());
        Assertions.assertNotNull(read);
        Assertions.assertEquals(read.getName(), patient.getName());


    }


}
