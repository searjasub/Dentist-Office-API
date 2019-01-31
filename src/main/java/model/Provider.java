package model;

public class Provider extends Person {

    private ProviderType title;
    private int uniqueId;

    public Provider(String name, String lastName, int uniqueId, String email, int phoneNumber, ProviderType title, int uniqueId1) {
        super(name, lastName, uniqueId, email, phoneNumber);
        this.title = title;
        this.uniqueId = uniqueId1;
    }

    public ProviderType getTitle() {
        return title;
    }

    public void setTitle(ProviderType title) {
        this.title = title;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    @Override
    public String toString() {
        return super.toString() + "Title: " + getTitle() + "ID: " + getUniqueId();
    }
}
