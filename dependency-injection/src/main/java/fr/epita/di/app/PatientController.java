package fr.epita.di.app;

import fr.epita.di.datamodel.Patient;
import fr.epita.di.services.impl.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

//    @Autowired
//    DataService service;


    @GetMapping(value = "/api/patients", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Patient>> getPatients(){
        Patient e1 = new Patient();
        e1.setName("test From REST");
        //TODO call the dataservice
        return ResponseEntity.ok(List.of(e1));
    }
}
