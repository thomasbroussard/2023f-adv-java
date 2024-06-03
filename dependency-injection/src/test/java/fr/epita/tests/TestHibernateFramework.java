package fr.epita.tests;

import fr.epita.di.conf.ApplicationConfiguration;
import fr.epita.di.datamodel.Patient;
import fr.epita.di.services.impl.HibernatePatientDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestHibernateFramework {

    @Autowired
    HibernatePatientDAO dao;


    @Test
    public void test() throws Exception {
        Patient patient = new Patient();
        dao.create(patient);
        dao.read(patient.getId());


    }

}
