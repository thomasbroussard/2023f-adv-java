package fr.epita.di.services.impl;

import fr.epita.di.datamodel.Doctor;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HibernateDoctorDAO {


    SessionFactory sf;

    public HibernateDoctorDAO(@Autowired SessionFactory sf) {
        this.sf = sf;
    }

    public void create(Doctor d){
        sf.openSession().persist(d);
    }

    //do the rest of the methods


}
