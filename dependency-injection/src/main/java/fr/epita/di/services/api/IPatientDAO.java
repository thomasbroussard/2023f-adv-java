package fr.epita.di.services.api;



import fr.epita.di.datamodel.Patient;

import java.util.List;

public interface IPatientDAO {
    void create(Patient patient) throws Exception;
    Patient read(int id) throws Exception;
    void update(Patient patient) throws Exception;
    void delete(int id) throws Exception;
    List<Patient> getAll() throws Exception;
}
