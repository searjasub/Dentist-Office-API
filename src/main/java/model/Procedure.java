package model;

import java.io.Serializable;

/**
 * Class that will define what a Procedure is
 */
public class Procedure implements Serializable {

    private static final long serialVersionUID = -5055699438924863645L;

    private String code = "D";
    private String description;
    private double cost;
    private Provider provider;

    /**
     * Constructor that creates a Procedure
     * @param code the procedure code
     * @param description the description of what was done during the procedure
     * @param cost how much this procedure cost
     * @param provider who performed this procedure
     */
    public Procedure(String code, String description, double cost, Provider provider) {
        this.setProvider(provider);
        this.setCode(code);
        this.setDescription(description);
        this.setCost(cost);
    }
    
    /**
     * @return the provider that performed this procedure
     */
    public Provider getProvider() {
        return provider;
    }
    
    /**
     * @param provider the Prvodider who will perform the procedure
     */
    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    /**
     * @return The code of the procedure
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code sets the code of the procedure
     */
    public void setCode(String code) {
        if (code == null || (code.trim()).isEmpty()) {
            throw new IllegalArgumentException("You cannot have nothing for the code");
        }
        this.code += code;
    }

    /**
     * @return the description of the procedure
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description sets the description of the procedure
     */
    public void setDescription(String description) {
        if (description == null || (description.trim()).isEmpty()) {
            throw new IllegalArgumentException("Please add a description");
        }
        this.description = description;
    }

    /**
     * @return the cost of te procedure
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost Set's the cost of the procecure
     */
    public void setCost(double cost) {
        if (cost < 0 || cost > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Cost cannot be negative or bigger than max value");
        }
        this.cost = cost;
    }

    /**
     * @return The information of the Procedure
     */
    @Override
    public String toString() {
        return "Code: " + getCode() + " | Description: " + getDescription() + " | Cost: " + getCost() + " | Provider: " + getProvider().getName() + " " + getProvider().getLastName();
    }
}
