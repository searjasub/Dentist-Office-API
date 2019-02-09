package model;

/**
 * Enum with different Providers type
 */
public enum ProviderType {
    DENTIST("Dentist"),
    ASSISTANT("Assistant"),
    HYGIENIST("Hygienist");

    private String type;

    /**
     * Constructor of Enum
     * @param type sets type String
     */
    ProviderType(String type) {
        this.type = type;
    }

    /**
     * @return The String version of the Enum.
     */
    public String getType() {
        return type;
    }
}
