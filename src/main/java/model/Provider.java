package model;

public class Provider extends Person {

    private ProviderType title;
    private int uniqueId;

    public Provider(String name, String lastName, int uniqueId, String email, int phoneNumber, ProviderType title, int uniqueId1) {
        super(name, lastName, uniqueId, email, phoneNumber);
        this.setTitle(title);
        this.uniqueId = uniqueId1;
    }

    public ProviderType getTitle() {
        return title;
    }

    public void setTitle(ProviderType title) {
        if(title != ProviderType.ASSISTANT || title != ProviderType.DENTIST || title != ProviderType.HYGIENIST) {
        	throw new IllegalArgumentException("This is now an allowed ProviderType");
        }
        this.title = title;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    @Override
    public String toString() {
        return super.toString() + "Title: " + getTitle() + "ID: " + getUniqueId();
    }
}
