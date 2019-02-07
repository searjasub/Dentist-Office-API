package model;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 2080035352953171799L;

    private String name, lastName;
    private String username;
    private String password;
    private UserRole userRole;
    private boolean firstTimePassChange;

    public User(String name, String lastName, String username, String password, UserRole userRole, boolean firstTimePassChange) {
        this.setName(name);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserRole(userRole);
        this.setFirstTimePassChange(firstTimePassChange);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid name input");
        }
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid last name input");
        }
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid username input");
        }
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid password input");
        }
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("This should not happen, but the setUserRole is not working.");
        }

        this.userRole = userRole;
    }

    public boolean isFirstTimePassChange() {
        return firstTimePassChange;
    }

    public void setFirstTimePassChange(boolean firstTimePassChange) {
        this.firstTimePassChange = firstTimePassChange;
    }
}
