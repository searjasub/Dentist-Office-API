package model;

import java.io.Serializable;

public class Person implements Serializable {

    private String name, lastName;
    private int uniqueId;
    private String email;
    private long phoneNumber;

    public Person() {
    }

    public Person(String name, String lastName, int uniqueId, String email, long phoneNumber) {
        this.setName(name);
        this.setLastName(lastName);
        this.uniqueId = uniqueId;
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
    	if(name == null || name.trim() == "") {
    		throw new IllegalArgumentException("Name cannot be nothing.");
    	}
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
    	if(lastName == null || lastName.trim() == "") {
    		throw new IllegalArgumentException("Last Name cannot be nothing.");
    	}
        this.lastName = lastName;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    	if(email == null || email.trim() == "") {
    		throw new IllegalArgumentException("You cannot have nothing for an email");
    	}
        this.email = email;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
    	if(phoneNumber < 1000000000 || phoneNumber > 9999999999) {
    		
    	}
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + " " + getLastName();
    }


}
