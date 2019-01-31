package model;

public class User extends Person {

    private String password;
    private UserType userType;

    public User(String name, String lastName, String password, UserType userType) {
        super(name, lastName);
        this.setPassword(password);
        this.setUserType(userType);
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
