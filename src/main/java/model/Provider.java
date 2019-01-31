package model;

public class Provider extends Person {

    private ProviderTitle title;

    public Provider(String name, String lastName, String id, ProviderTitle title) {
        super(name, lastName);
        this.setTitle(title);
    }

    public ProviderTitle getTitle() {
        return title;
    }

    public void setTitle(ProviderTitle title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString() + "Title: " + getTitle();
    }
}
