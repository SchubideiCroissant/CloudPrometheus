package de.home.vs.rest.model;

public class Student {
    private int matNr;
    private String firstName;
    private String familyName;

    public Student(int matNr, String firstName, String familyName) {
        this.matNr = matNr;
        this.firstName = firstName;
        this.familyName = familyName;
    }

    public int getMatNr() {
        return matNr;
    }

    public void setMatNr(int matNr) {
        this.matNr = matNr;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
}
