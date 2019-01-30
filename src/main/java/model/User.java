package model;

public class User extends Person {

    private String password;
    private Privilege privilege;

    public User(String name, String lastName, String id, String password, Privilege privilege) {
        super(name, lastName, id);
        this.setPassword(password);
        this.setPrivilege(privilege);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
