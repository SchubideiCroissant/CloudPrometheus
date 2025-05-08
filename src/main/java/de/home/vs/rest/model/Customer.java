package de.home.vs.rest.model;

public class Customer {
    private String firstName;
    private String familyName;
    private  int id;

    public Customer(String firstName, String familyName, int id) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.id = id;
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
    public int getId() {return id;}
}

