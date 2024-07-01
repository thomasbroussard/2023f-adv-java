package fr.epita.titanic.api.rest.resources;

import fr.epita.titanic.datamodel.Passenger;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class PassengerDTO {

    private String name;

    private int id;
    private String passengerClass;
    private String embarked;
    private String gender;

    private int legalTutor;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassengerClass() {
        return passengerClass;
    }

    public void setPassengerClass(String passengerClass) {
        this.passengerClass = passengerClass;
    }

    public String getEmbarked() {
        return embarked;
    }

    public void setEmbarked(String embarked) {
        this.embarked = embarked;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getLegalTutor() {
        return legalTutor;
    }

    public void setLegalTutor(int legalTutor) {
        this.legalTutor = legalTutor;
    }
}
