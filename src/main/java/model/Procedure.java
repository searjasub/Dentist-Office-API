package model;

public class Procedure {

    private Patient patient;
    private Provider provider;
    private String code = "D";
    private String description;
    private int amountCharged;

    public Procedure(Patient patient, String code, String description, int amountCharged, Provider provider) {
        this.setProvider(provider);
        this.setPatient(patient);
        this.setCode(code);
        this.setDescription(description);
        this.setAmountCharged(amountCharged);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(int amountCharged) {
        this.amountCharged = amountCharged;
    }
}
