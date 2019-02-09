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

    /**
     * 
     * @return the first name of user when called
     */
    
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of user
     */
    
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid name input");
        }
        this.name = name;
    }

    /**
     * 
     * @return last name of the user
     */
    
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of user
     */
    
    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid last name input");
        }
        this.lastName = lastName;
    }

    /**
     * 
     * @return username of the user
     */
    
    public String getUsername() {
        return username;
    }

    /**
     * Sets username of user
     */
    
    public void setUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid username input");
        }
        this.username = username;
    }

    /**
     * @return password of the user
     */
    
    public String getPassword() {
        return password;
    }

    /**
     * Sets the passowrd of the user
     */
    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid password input");
        }
        this.password = password;
    }

    /**
     * 
     * @return userRole of the user
     */
    
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * sets userrole fo the user
     */
    public void setUserRole(UserRole userRole) {
        if (userRole == null) {
            throw new IllegalArgumentException("This should not happen, but the setUserRole is not working.");
        }

        this.userRole = userRole;
    }

    /**
     * 
     * @return whether or not this is the first time the user logged in or not
     */
    public boolean isFirstTimePassChange() {
        return firstTimePassChange;
    }

    /**
     * 
     * @return last name of the user
     */
    public void setFirstTimePassChange(boolean firstTimePassChange) {
        this.firstTimePassChange = firstTimePassChange;
    }
}
