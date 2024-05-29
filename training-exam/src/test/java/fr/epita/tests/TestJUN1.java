package fr.epita.tests;

import fr.epita.training.datamodel.Insurance;
import fr.epita.training.datamodel.Patient;
import fr.epita.training.services.InsuranceReader;
import fr.epita.training.services.PatientReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestJUN1 {

    private InsuranceReader insuranceReader;
    private PatientReader patientReader;
    private String patientFilePath;
    private String insuranceFilePath;

    @Test
    public void test(){
        System.out.println("Hi from JUnit");
    }

    @Test
    public void testLoading(){
        List<Insurance> insurances = this.insuranceReader.readAll(this.insuranceFilePath);
        List<Patient> patients = this.patientReader.readAll(this.patientFilePath);
        System.out.println(insurances);
        System.out.println(patients);
    }

    @AfterEach
    public void afterEachTest(){

    }

    @BeforeEach
    public void setup(){
        this.insuranceFilePath = "training-exam/insurances.csv";
        this.patientFilePath = "training-exam/patients.csv";
        this.patientReader = new PatientReader();
        this.insuranceReader = new InsuranceReader();
    }

    @AfterAll
    public static void closeTest(){

    }
}
