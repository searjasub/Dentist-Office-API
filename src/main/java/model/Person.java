package model;

import java.io.Serializable;

public class Person implements Serializable {

    private String name, lastName;

    public Person() {
    }

    public Person(String name, String lastName) {
        this.setName(name);
        this.setLastName(lastName);
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

    @Override
    public String toString() {
        return "Name: " + getName() + " " + getLastName() ;
    }


}
