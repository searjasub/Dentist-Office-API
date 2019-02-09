package model;

import java.io.Serializable;

/**
 * Define a User in the system and all his information
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2080035352953171799L;

    private String name, lastName;
    private String username;
    private String password;
    private UserRole userRole;
    private boolean firstTimePassChange;

    /**
     * Constructor of USer
     * @param name First name of user
     * @param lastName Last name of user
     * @param username User's username
     * @param password User's password
     * @param userRole User's role, either Admin or Standard
     * @param firstTimePassChange When constructing an User will makes sure that the user needs to change the password
     */
    public User(String name, String lastName, String username, String password, UserRole userRole, boolean firstTimePassChange) {
        this.setName(name);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserRole(userRole);
        this.setFirstTimePassChange(firstTimePassChange);

    }

    /**
     * @return the first name of user when called
     */
    public String getName() {
        return name;
    }

    /**
     * Set's the name of the user
     * @param name a given name
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid name input");
        }
        this.name = name;
    }

    /**
     * @return Last name of the user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the USer
     * @param lastName the last name given
     */
    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid last name input");
        }
        this.lastName = lastName;
    }

    /**
     * @return username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set's the username of the User
     * @param username the username
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
     * Set's the password for the user
     * @param password the new password of the user
     */
    public void setPassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("This is not a valid password input");
        }
        this.password = password;
    }

    /**
     * @return the user role
     */
    public UserRole getUserRole() {
        return userRole;
    }


    /**
     * Sets the User Role to whatever parameter
     * @param userRole Enum with the ype of UserRole
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
     * Method that helps valida is user will need to update his password
     * @param firstTimePassChange will set the flag either tru or false is user has changed his password in the past
     */
    public void setFirstTimePassChange(boolean firstTimePassChange) {
        this.firstTimePassChange = firstTimePassChange;
    }

    @Override
    public String toString() {
        return "Username: " + getUsername() + " | Name: " + getName() + " " + getLastName() + " | ["+ getUserRole().toString();
    }
}
