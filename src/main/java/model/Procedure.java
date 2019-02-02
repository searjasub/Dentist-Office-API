package model;

import java.io.Serializable;

public class Procedure implements Serializable {

    private Patient patient;
    private String code = "D";
    private String description;
    private double cost;
    private Provider provider;

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
    	if(code == null || (code.trim()).isEmpty()) {
    		throw new IllegalArgumentException("You cannot have nothing for the code");
    	}
        this.code += code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
    	if(description == null || (description.trim()).isEmpty()) {
    		throw new IllegalArgumentException("Please add a description");
    	}
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
    	if(cost < 0 || cost > Double.MAX_VALUE) {
    		throw new IllegalArgumentException("Cost cannot be negative or bigger than max value");
    	}
        this.cost = cost;
    }
}
