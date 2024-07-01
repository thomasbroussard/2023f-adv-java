package fr.epita.titanic.app;

import fr.epita.titanic.api.rest.resources.PassengerDTO;
import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PassengerController {

    @Autowired
    DataService service;

    @GetMapping
    public ResponseEntity<List<PassengerDTO>> getPassengers(){
        List<Passenger> passengers = service.listPassengers();
        List<PassengerDTO> dtos = new ArrayList<>();
        for (Passenger p:passengers){
            PassengerDTO dto = new PassengerDTO();
            dto.setPassengerClass(p.getPassengerClass());
            dto.setGender(p.getGender());
            dto.setName(p.getName());
            dto.setId(p.getId());
            dto.setLegalTutor(p.getLegalTutor().getId());
            dtos.add(dto);
        }

        return ResponseEntity.ok(dtos);

    }

}
