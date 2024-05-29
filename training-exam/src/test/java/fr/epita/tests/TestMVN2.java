package fr.epita.tests;

import fr.epita.training.services.InsuranceReader;
import fr.epita.training.services.PatientReader;

public class TestMVN2 {

    public static void main(String[] args) {
        InsuranceReader insuranceReader = new InsuranceReader();
        PatientReader patientReader = new PatientReader();

        System.out.println(insuranceReader.readAll("training-exam/insurances.csv"));
        System.out.println(patientReader.readAll("training-exam/patients.csv"));
    }
}
