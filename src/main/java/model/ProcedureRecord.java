package model;

import java.io.Serializable;

public class ProcedureRecord implements Serializable {

    private static final long serialVersionUID = 1108593470942822454L;
    
    private Patient patient;
    private Procedure procedure;
    private double cost;

    /**
     * Creates the procedure
     */
    
	public ProcedureRecord(Patient patient, Procedure procedure, double cost) {
		this.setPatient(patient);
		this.setProcedure(procedure);
		this.setCost(cost);
	}

	/**
     * 
     * @return patient that will do this procedure
     */
	public Patient getPatient() {
		return patient;
	}
	/**
     * set the patient who will do this procedure
     */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	/**
     * 
     * @return te name of the procedure
     */
	public Procedure getProcedure() {
		return procedure;
	}
	/**
     * sets the procedure to somthing else
     */
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	/**
     * 
     * @return cost of the cost
     */
	public double getCost() {
		return cost;
	}
	/**
     * sets cost of procedure
     */
	public void setCost(double cost) {
		this.cost = cost;
	}
    
    
}
