package fr.epita.di.app;

import fr.epita.di.app.conversion.Mappers;
import fr.epita.di.app.messages.PatientDTO;
import fr.epita.di.datamodel.Patient;
import fr.epita.di.services.impl.DataService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private static final Logger LOGGER = LogManager.getLogger(PatientController.class);

    @Autowired
    DataService service;


    @GetMapping(value = "/api/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PatientDTO>> getPatients(){
        Patient e1 = new Patient();
        e1.setName("test From REST");
        //TODO call the dataservice
        return ResponseEntity.ok(List.of(e1));
    }

    @GetMapping(value = "/api/patients/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> createPatient(@PathVariable("id") Integer id){
        LOGGER.info("retrieving patient with id {}", id);
        //TODO log
        Patient patient = service.getPatient(id);
        return ResponseEntity.ok( Mappers.dtoFromPatient(patient));
    }

    @PostMapping(value = "/api/patients",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patient){
        Patient p = Mappers.patientFromDTO(patient);
        service.createPatient(p);
        return ResponseEntity.ok(Mappers.dtoFromPatient(p));
    }


}
