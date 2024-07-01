package fr.epita.titanic.datamodel;


import javax.persistence.*;

@Entity
public class Passenger {

    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String passengerClass;
    private String embarked;
    private String gender;

    @ManyToOne
    private Passenger legalTutor;

    public Passenger getLegalTutor() {
        return legalTutor;
    }

    public void setLegalTutor(Passenger legalTutor) {
        this.legalTutor = legalTutor;
    }

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

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", passengerClass='" + passengerClass + '\'' +
                ", embarked='" + embarked + '\'' +
                ", gender='" + gender + '\'' +
                ", legalTutor=" + (legalTutor != null ? String.valueOf(legalTutor.getId()) : "")+
                '}';
    }
}
