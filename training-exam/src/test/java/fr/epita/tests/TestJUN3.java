package fr.epita.tests;

import fr.epita.training.datamodel.Insurance;
import fr.epita.training.datamodel.Patient;
import fr.epita.training.services.InsuranceReader;
import fr.epita.training.services.PatientReader;
import org.junit.jupiter.api.*;

import java.util.List;

public class TestJUN3 {


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

        Assertions.assertEquals(insurances.size(), 5);
        Assertions.assertEquals(insurances.get(0).getInsuranceName(),"MACIF");

    }

    @AfterEach
    public void afterEachTest(){

    }

    @BeforeEach
    public void setup(){
        this.insuranceFilePath = "insurances.csv";
        this.patientFilePath = "patients.csv";
        this.patientReader = new PatientReader();
        this.insuranceReader = new InsuranceReader();
    }

    @AfterAll
    public static void closeTest(){

    }
}
