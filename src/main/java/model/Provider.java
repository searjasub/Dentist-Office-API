package model;

/**
 * Defines a provider. A Provider is a person and extends all its parameters and methods
 * @extends Person with all his information
 */
public class Provider extends Person {

    private static final long serialVersionUID = 8291776832764371464L;

    private ProviderType title;

    /**
     * Constructor of Provider
     * @param name Name of provider
     * @param lastName Last of Provider
     * @param uniqueId unique identifier
     * @param email Provider's email
     * @param phoneNumber Provider's phone number
     * @param title Provider's title (Enum ProviderType)
     */
    public Provider(String name, String lastName, int uniqueId, String email, String phoneNumber, ProviderType title) {
        super(name, lastName, uniqueId, email, phoneNumber);
        this.setTitle(title);
    }

    /**
     * @return the provider type of the provider
     */
    public ProviderType getTitle() {
        return title;
    }

    /**
     * Sets the title of the provider
     * @param title the Enum of provider type
     */
    public void setTitle(ProviderType title) {
        if (title == null) {
            throw new IllegalArgumentException("This is not an allowed ProviderType");
        }
        this.title = title;
    }

    /**
     * @return basic toString for provider
     */
    @Override
    public String toString() {
        return super.toString() + " | Title: " + getTitle() + "ID: " + getUniqueId();
    }
}
