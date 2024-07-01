package fr.epita.tests;

import fr.epita.titanic.ApplicationConfiguration;
import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.DataService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestSPR1 {

    @Autowired
    DataService service;
    @Test
    public void test(){
        //given the configuration is correct
        //when we start the test
        //then the service shouldn't be null
        Assertions.assertNotNull(service);
    }




}
