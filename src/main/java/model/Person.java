package model;

public class Person {

    private String name, lastName;
    private String id = "D";

    public Person(String name, String lastName, String id) {
        this.setName(name);
        this.setLastName(lastName);
        this.setId(id);
    }

    public Person() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + " " + getLastName() +
                "ID: " + getId();
    }
}
