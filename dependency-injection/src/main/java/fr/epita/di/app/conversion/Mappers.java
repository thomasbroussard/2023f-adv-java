package fr.epita.di.app.conversion;

import fr.epita.di.app.messages.PatientDTO;
import fr.epita.di.datamodel.Patient;

public class Mappers {


    public static PatientDTO dtoFromPatient(Patient p){
        PatientDTO dto = new PatientDTO();
        dto.setName(p.getName());
        dto.setId(p.getId());
        return dto;
    }
    public static Patient patientFromDTO(PatientDTO dto){
        Patient patient = new Patient();
        return mergePatientFromDTO(dto, patient);
    }

    private static Patient mergePatientFromDTO(PatientDTO dto, Patient patient) {
        patient.setId(dto.getId());
        patient.setName(dto.getName());
        return patient;
    }
}
