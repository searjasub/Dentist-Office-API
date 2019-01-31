package model;

public class Provider extends Person {

    private ProviderTitle title;
    private int uniqueId;

    public Provider(String name, String lastName, ProviderTitle title, int uniqueId) {
        super(name, lastName);
        this.setTitle(title);
        this.setUniqueId(uniqueId);
    }

    public ProviderTitle getTitle() {
        return title;
    }

    public void setTitle(ProviderTitle title) {
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
