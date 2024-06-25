package fr.epita.di.services.impl;

import fr.epita.di.datamodel.Appointment;
import fr.epita.di.datamodel.Doctor;
import fr.epita.di.datamodel.Patient;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;


@Repository
@Transactional
public class DataService {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Appointment createAppointment(Patient patient, Doctor doctor, Date date){
        Appointment appointment = new Appointment();
        appointment.setDate(date);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        em.persist(appointment);
        return appointment;
    }


    @Transactional(Transactional.TxType.MANDATORY)
    public void createPatient(Patient patient){
        em.persist(patient);
    }

    public Patient getPatient(int i) {
       return em.find(Patient.class, i);
    }
}
