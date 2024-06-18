package fr.epita.tests;


import fr.epita.di.conf.ApplicationConfiguration;
import fr.epita.di.datamodel.Doctor;
import fr.epita.di.services.impl.JPADoctorDAO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Transactional
public class TestEntityManager {


    @Autowired
    JPADoctorDAO doctorDAO;


    @Test
    public void test(){

        Doctor d = new Doctor();
        d.setName("test");
        d.setId(1);
        doctorDAO.create(d);
    }


}
