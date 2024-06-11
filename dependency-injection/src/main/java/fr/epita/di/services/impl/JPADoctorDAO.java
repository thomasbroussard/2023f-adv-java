package fr.epita.di.services.impl;

import fr.epita.di.datamodel.Doctor;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

public class JPADoctorDAO {


    @PersistenceContext
    EntityManager em;


    public void create(Doctor d){
        em.persist(d);
    }


}
