package fr.epita.tests;

import fr.epita.titanic.api.rest.resources.PassengerDTO;
import fr.epita.titanic.app.TitanicApp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootTest(classes = TitanicApp.class)
public class TestSPB1 {

    @LocalServerPort
    int port;



    @Test
    public void check(){
        RestTemplate template = new RestTemplate();
        ResponseEntity<List> forEntity = template.getForEntity("http://localhost:" + port + "/", List.class);

        List passengerDTOS = forEntity.getBody();
        System.out.println(passengerDTOS);

    }



}



