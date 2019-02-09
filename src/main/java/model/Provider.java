package model;

public class Provider extends Person {

    private static final long serialVersionUID = 8291776832764371464L;

    private ProviderType title;

    /**
     * This is the constructor for provider (which extends person)
     * takes in a name , last name, id number, email,phone number and title
     */
    
    public Provider(String name, String lastName, int uniqueId, String email, String phoneNumber, ProviderType title) {
        super(name, lastName, uniqueId, email, phoneNumber);
        this.setTitle(title);
    }

    /**
     * 
     * @return the provider type of the provider
     */
    
    public ProviderType getTitle() {
        return title;
    }

    /**
     * sets the title of the provider in here
     */
    
    public void setTitle(ProviderType title) {
        if (title == null) {
            throw new IllegalArgumentException("This is not an allowed ProviderType");
        }
        this.title = title;
    }

    /**
     * 
     * @return basic toString for provider
     */
    
    @Override
    public String toString() {
        return super.toString() + " | Title: " + getTitle() + "ID: " + getUniqueId();
    }
}
