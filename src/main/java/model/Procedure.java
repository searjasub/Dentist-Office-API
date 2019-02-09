package model;

import java.io.Serializable;

public class Procedure implements Serializable {

    private static final long serialVersionUID = -5055699438924863645L;

    private String code = "D";
    private String description;
    private double cost;
    private Provider provider;

    /**
     * Constructs the procedure
     * takes in a code, description, cost, and provider
     */
    
    public Procedure(String code, String description, double cost, Provider provider) {
        this.setProvider(provider);
        this.setCode(code);
        this.setDescription(description);
        this.setCost(cost);
    }
    
    /**
     * 
     * @return the provider that the procedure wil use 
     */

    public Provider getProvider() {
        return provider;
    }
    
    /**
     * 
     * Sets the provider that the procedure will use
     */

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    /**
     * 
     * @return the code of the procedure (String)
     */
    
    public String getCode() {
        return code;
    }

    /**
     * 
     * sets the code of the pocedure
     */
    
    public void setCode(String code) {
        if (code == null || (code.trim()).isEmpty()) {
            throw new IllegalArgumentException("You cannot have nothing for the code");
        }
        this.code += code;
    }

    /**
     * 
     * @return the description of the procedure
     */
    
    public String getDescription() {
        return description;
    }
    
    /**
     * 
     * sets the description of the procedure
     */

    public void setDescription(String description) {
        if (description == null || (description.trim()).isEmpty()) {
            throw new IllegalArgumentException("Please add a description");
        }
        this.description = description;
    }

    /**
     * 
     * @return the cost of te procedure
     */
    public double getCost() {
        return cost;
    }

    /**
     * sets the cost of the procedure
     * 
     */
    public void setCost(double cost) {
        if (cost < 0 || cost > Double.MAX_VALUE) {
            throw new IllegalArgumentException("Cost cannot be negative or bigger than max value");
        }
        this.cost = cost;
    }

    /**
     * 
     * @return simple tostring that returns all needed info
     */
    @Override
    public String toString() {
        return "Code: " + getCode() + " | Description: " + getDescription() + " | Cost: " + getCost() + " | Provider: " + getProvider().getName() + " " + getProvider().getLastName();
    }
}
