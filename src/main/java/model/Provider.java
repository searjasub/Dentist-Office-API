package model;

public class Provider extends Person {

    private static final long serialVersionUID = 8291776832764371464L;

    private ProviderType title;

    public Provider(String name, String lastName, int uniqueId, String email, String phoneNumber, ProviderType title) {
        super(name, lastName, uniqueId, email, phoneNumber);
        this.setTitle(title);
    }

    public ProviderType getTitle() {
        return title;
    }

    public void setTitle(ProviderType title) {
        if (title == null) {
            throw new IllegalArgumentException("This is not an allowed ProviderType");
        }
        this.title = title;
    }

    @Override
    public String toString() {
        return super.toString() + " | Title: " + getTitle() + "ID: " + getUniqueId();
    }
}
