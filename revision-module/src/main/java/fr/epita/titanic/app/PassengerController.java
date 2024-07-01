package fr.epita.titanic.app;

import fr.epita.titanic.api.rest.resources.PassengerDTO;
import fr.epita.titanic.datamodel.Passenger;
import fr.epita.titanic.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("/passengers")
    public ResponseEntity<PassengerDTO> createPassenger(@RequestBody PassengerDTO dto) {
        Passenger passenger = new Passenger();
        passenger.setPassengerClass(dto.getPassengerClass());
        passenger.setGender(dto.getGender());
        passenger.setName(dto.getName());
        passenger.setId(dto.getId());

        service.create(passenger);
        dto.setId(passenger.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/passengers/{id}")
    public ResponseEntity<PassengerDTO> updatePassenger(@PathVariable Long id, @RequestBody PassengerDTO dto) {
        Passenger passenger = service.findPassengerById(id);
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }
        passenger.setPassengerClass(dto.getPassengerClass());
        passenger.setGender(dto.getGender());
        passenger.setName(dto.getName());
        service.update(passenger);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/passengers/{id}")
    public ResponseEntity<Void> deletePassenger(@PathVariable Long id) {
        Passenger passenger = service.findPassengerById(id);
        if (passenger == null) {
            return ResponseEntity.notFound().build();
        }
        service.delete(passenger);
        return ResponseEntity.noContent().build();
    }
}
