package model;

import java.io.Serializable;

public class Procedure implements Serializable {

    private static final long serialVersionUID = -5055699438924863645L;

    private String code = "D";
    private String description;
    private double cost;
    private Provider provider;

    public Procedure(String code, String description, double cost, Provider provider) {
        this.setProvider(provider);
        this.setCode(code);
        this.setDescription(description);
        this.setCost(cost);
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
        if (code == null || (code.trim()).isEmpty()) {
            throw new IllegalArgumentException("You cannot have nothing for the code");
        }
        this.code += code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || (description.trim()).isEmpty()) {
            throw new IllegalArgumentException("Please add a description");
        }
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        if (cost < 0 || cost > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Cost cannot be negative or bigger than max value");
        }
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Code: " + getCode() + " | Description: " + getDescription() + " | Cost: " + getCost() + " | Provider: " + getProvider().getName() + " " + getProvider().getLastName();
    }
}
