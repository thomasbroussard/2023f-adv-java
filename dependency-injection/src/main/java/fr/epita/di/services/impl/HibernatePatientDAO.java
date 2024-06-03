package fr.epita.di.services.impl;

import fr.epita.di.datamodel.Patient;
import fr.epita.di.services.api.IPatientDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class HibernatePatientDAO  implements IPatientDAO {

    @Autowired
    SessionFactory sf;

    @Override
    public void create(Patient patient) throws Exception {
        Session session = sf.openSession();
        session.persist(patient);
    }

    @Override
    public Patient read(int id) throws Exception {
        Session session = sf.openSession();
        return session.get(Patient.class, id);
    }

    @Override
    public void update(Patient patient) throws Exception {
        Session session = sf.openSession();
        session.merge(patient);
    }

    @Override
    public void delete(int id) throws Exception {
        Session session = sf.openSession();
        session.remove(id);
    }

    @Override
    public List<Patient> getAll() throws Exception {
        Session session = sf.openSession();
        List<Patient> patients = session.createQuery("from Patient", Patient.class).list();
        return patients;
    }
}
