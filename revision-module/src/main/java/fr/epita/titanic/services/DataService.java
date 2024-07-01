package fr.epita.titanic.services;

import fr.epita.titanic.datamodel.Passenger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DataService {


    @PersistenceContext
    EntityManager em;

    public void create(Passenger passenger){
        em.persist(passenger);
    }


    public List<Passenger> listPassengers() {
        List<Passenger> o = em.createQuery("from Passenger p").getResultList();
        return o;

    }
}
