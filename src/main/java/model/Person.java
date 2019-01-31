package model;

import java.io.Serializable;

public class Person implements Serializable {

    private String name, lastName;
    private int uniqueId;
    private String email;
    private int phoneNumber;

    public Person() {
    }

    public Person(String name, String lastName, int uniqueId, String email, int phoneNumber) {
        this.setName(name);
        this.setLastName(lastName);
        this.setUniqueId(uniqueId);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + " " + getLastName();
    }


}
