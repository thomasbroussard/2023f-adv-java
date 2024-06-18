package fr.epita.tests;


import fr.epita.di.conf.ApplicationConfiguration;
import fr.epita.di.datamodel.Appointment;
import fr.epita.di.datamodel.Doctor;
import fr.epita.di.datamodel.Patient;
import fr.epita.di.services.impl.DataService;
import fr.epita.di.services.impl.JPADoctorDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Transactional
@Commit
public class TestEntityManager {


    @Autowired
    JPADoctorDAO doctorDAO;

    @Autowired
    DataService service;


    @PersistenceContext
    EntityManager em;

    @Test
    public void test() {

        Doctor d = new Doctor();
        d.setName("test");
        d.setId(1);
        doctorDAO.create(d);
    }

    @Test
    public void testCreateAppointment() {
        //given
        Doctor d = new Doctor();
        d.setName("test");
        d.setId(1);
        Patient patient = new Patient();
        patient.setName("testPatient");
        em.persist(patient);
        em.persist(d);

        //when
        Appointment appointment = service.createAppointment(patient, d, new Date());

        //then
        Assertions.assertNotNull(em.find(Appointment.class, appointment.getId()));

        System.out.println(appointment);
    }


}
