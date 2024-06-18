package fr.epita.di.services.impl;

import fr.epita.di.datamodel.Doctor;;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

public class JPADoctorDAO {


    @PersistenceContext
    EntityManager em;


    @Transactional(Transactional.TxType.REQUIRED)
    public void create(Doctor d){
        em.persist(d);
    }


}
