package fr.epita.eventure.tests;


import fr.epita.eventure.conf.ApplicationConfiguration;
import fr.epita.eventure.datamodel.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@Commit
public class TestJPA {

    @PersistenceContext
    EntityManager entityManager;


    @Test
    @Transactional
    public void testPersist(){

        //missing then part
        Event event = new Event();
        event.setTitle("Java party!");
        event.setStartDate(new Date());
        entityManager.persist(event);
    }


}
