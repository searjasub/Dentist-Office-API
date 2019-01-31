package model;

import java.io.Serializable;

public class User implements Serializable {

    private String name, lastName;
    private String username;
    private String password;
    private UserRole userRole;

    public User(String name, String lastName, String username, String password, UserRole userRole) {
        this.setName(name);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserRole(userRole);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public void changePassword(String password){

    }
}
