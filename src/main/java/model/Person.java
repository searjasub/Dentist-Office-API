package model;

import java.io.Serializable;

/**
 * Class that define what a Person is
 */
public class Person implements Serializable {

    private static final long serialVersionUID = 6356181592833802673L;

    private String name, lastName;
    private int uniqueId;
    private String email;
    private String phoneNumber;

    /**
     * Empty Constructor
     */
    public Person() {
    }

    /**
     * Constructor that creates a Person
     * @param name Person's name
     * @param lastName Person's last name
     * @param uniqueId Person's unique ID
     * @param email Person's email
     * @param phoneNumber Person's phone number
     */
    public Person(String name, String lastName, int uniqueId, String email, String phoneNumber) {
        this.setName(name);
        this.setLastName(lastName);
        this.uniqueId = uniqueId;
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }

    /**
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Set's the name of the Person
     */
    public void setName(String name) {
        if (name == null || (name.trim()).isEmpty()) {
            throw new IllegalArgumentException("Name cannot be nothing.");
        }
        this.name = name;
    }

    /**
     * @return The last name of the person
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the person
     * @param lastName desire for person
     */
    public void setLastName(String lastName) {
        if (lastName == null || (lastName.trim()).isEmpty()) {
            throw new IllegalArgumentException("Last Name cannot be nothing.");
        }
        this.lastName = lastName;
    }

    /**
     * @return the ID of the erson
     */
    public int getUniqueId() {
        return uniqueId;
    }

    /**
     * @return The email of the person
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email for the Person.
     * @param email the desire email to be given
     */
    public void setEmail(String email) {
        if (email == null || (email.trim()).isEmpty()) {
            throw new IllegalArgumentException("You cannot have nothing for an email");
        }
        this.email = email;
    }

    /**
     * @return the Person's phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the Person
     * @param phoneNumber the phone number desired
     */
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            throw new IllegalArgumentException("Phone number cannot be null");
        }
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return All Person's information in a String
     */
    @Override
    public String toString() {
        return "\tName: " + getName() + " " + getLastName() + " | ID: " + getUniqueId() + " | Email: " + getEmail() + " | Phone: " + getPhoneNumber();
    }
}
