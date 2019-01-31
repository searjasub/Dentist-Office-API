package model;

public class Procedure {

    private Patient patient;
    private Provider provider;
    private String code = "D";
    private String description;
    private double cost;

    public Procedure(Patient patient, String code, String description, double cost, Provider provider) {
        this.setProvider(provider);
        this.setPatient(patient);
        this.setCode(code);
        this.setDescription(description);
        this.setCost(cost);
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
