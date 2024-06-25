package fr.epita.di.datamodel;


import javax.persistence.*;

@Entity
@Table(name = "PATIENTS")
public class Patient {
    @Id
    @Column(name = "pat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name ="pat_name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
