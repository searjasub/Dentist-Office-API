package model;

public class User extends Person {

    private String username;
    private String password;
    private UserType userType;

    public User(String name, String lastName, String username, String password, UserType userType) {
        super(name, lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setUserType(userType);
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
