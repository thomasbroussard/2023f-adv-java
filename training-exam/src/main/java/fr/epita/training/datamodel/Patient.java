package fr.epita.training.datamodel;

public class Patient {
    private String patNumHC;
    private String patLastname;
    private String patFirstname;
    private String patAddress;
    private String patTel;
    private int patInsuranceId;
    private String patSubDate;

    // Constructor
    public Patient(String patNumHC, String patLastname, String patFirstname, String patAddress, String patTel, int patInsuranceId, String patSubDate) {
        this.patNumHC = patNumHC;
        this.patLastname = patLastname;
        this.patFirstname = patFirstname;
        this.patAddress = patAddress;
        this.patTel = patTel;
        this.patInsuranceId = patInsuranceId;
        this.patSubDate = patSubDate;
    }

    // Getter and Setter methods
    public String getPatNumHC() {
        return patNumHC;
    }

    public void setPatNumHC(String patNumHC) {
        this.patNumHC = patNumHC;
    }

    public String getPatLastname() {
        return patLastname;
    }

    public void setPatLastname(String patLastname) {
        this.patLastname = patLastname;
    }

    public String getPatFirstname() {
        return patFirstname;
    }

    public void setPatFirstname(String patFirstname) {
        this.patFirstname = patFirstname;
    }

    public String getPatAddress() {
        return patAddress;
    }

    public void setPatAddress(String patAddress) {
        this.patAddress = patAddress;
    }

    public String getPatTel() {
        return patTel;
    }

    public void setPatTel(String patTel) {
        this.patTel = patTel;
    }

    public int getPatInsuranceId() {
        return patInsuranceId;
    }

    public void setPatInsuranceId(int patInsuranceId) {
        this.patInsuranceId = patInsuranceId;
    }

    public String getPatSubDate() {
        return patSubDate;
    }

    public void setPatSubDate(String patSubDate) {
        this.patSubDate = patSubDate;
    }

    // Override toString method for better representation
    @Override
    public String toString() {
        return "Patient{" +
                "patNumHC='" + patNumHC + '\'' +
                ", patLastname='" + patLastname + '\'' +
                ", patFirstname='" + patFirstname + '\'' +
                ", patAddress='" + patAddress + '\'' +
                ", patTel='" + patTel + '\'' +
                ", patInsuranceId=" + patInsuranceId +
                ", patSubDate='" + patSubDate + '\'' +
                '}';
    }
}
