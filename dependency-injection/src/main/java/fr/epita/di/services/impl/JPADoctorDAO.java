package fr.epita.di.services.impl;

import fr.epita.di.datamodel.Doctor;

import javax.persistence.EntityManager;

public class JPADoctorDAO {


    EntityManager em;

    public void create(Doctor d){
        em.persist(d);
    }


}
