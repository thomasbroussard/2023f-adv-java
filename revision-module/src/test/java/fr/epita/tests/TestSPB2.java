package fr.epita.tests;

import fr.epita.titanic.ApplicationConfiguration;
import fr.epita.titanic.app.TitanicApp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class TestSPB2 {



    @Test
    public void startApplication(){

        //given
        TitanicApp.main(new String[0]);

        //when
        RestTemplate template = new RestTemplate();
        ResponseEntity<List> forEntity = template.getForEntity("http://localhost:8080/", List.class);
        List passengerDTOS = forEntity.getBody();

        //then
        Assertions.assertTrue(passengerDTOS.isEmpty());
    }

}

