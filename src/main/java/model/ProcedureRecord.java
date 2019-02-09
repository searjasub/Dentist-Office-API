package model;

import java.io.Serializable;

/**
 * Creates a record of a Procedure (Usually when a Procedure has been completed)
 */
public class ProcedureRecord implements Serializable {

    private static final long serialVersionUID = 1108593470942822454L;

    private Patient patient;
    private Procedure procedure;
    private double cost;

    /**
     * Constructor of ProcedureRecord
     * @param patient The patient on who the procedure was performed
     * @param procedure what specific procedure was performed
     * @param cost the final cost after insurance
     */
    public ProcedureRecord(Patient patient, Procedure procedure, double cost) {
        this.setPatient(patient);
        this.setProcedure(procedure);
        this.setCost(cost);
    }

    /**
     * @return patient that will do this procedure
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @param patient The patient who will do this procedure
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return The Procedure performed
     */
    public Procedure getProcedure() {
        return procedure;
    }

    /**
     * @param procedure Set ProcedureRecord's procedure performed
     */
    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    /**
     * @return final cost of the procedure
     */
    public double getCost() {
        return cost;
    }

    /**
     * @param cost final cost of the procedure
     */
    public void setCost(double cost) {
        this.cost = cost;
    }


}
