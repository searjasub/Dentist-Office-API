package model;

public enum ProviderType {
    DENTIST("Dentist"),
    ASSISTANT("Assistant"),
    HYGIENIST("Hygienist");

    private String type;

    ProviderType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
