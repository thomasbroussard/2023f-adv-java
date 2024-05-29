package fr.epita.training.services;

import fr.epita.training.datamodel.Patient;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PatientReader {

    public List<Patient> readAll(String filePath) {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // Skip header line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                String patNumHC = values[0];
                String patLastname = values[1];
                String patFirstname = values[2];
                String patAddress = values[3];
                String patTel = values[4];
                int patInsuranceId = Integer.parseInt(values[5]);
                String patSubDate = values[6];

                Patient patient = new Patient(patNumHC, patLastname, patFirstname, patAddress, patTel, patInsuranceId, patSubDate);
                patients.add(patient);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return patients;
    }

    public static void main(String[] args) {
        PatientReader reader = new PatientReader();
        List<Patient> patients = reader.readAll("patients.csv");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }
}
