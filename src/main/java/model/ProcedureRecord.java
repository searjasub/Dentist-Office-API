package model;

import java.io.Serializable;

public class ProcedureRecord implements Serializable {

    private Patient patient;
    private Procedure procedure;
    private double cost;

	public ProcedureRecord(Patient patient, Procedure procedure, double cost) {
		this.setPatient(patient);
		this.setProcedure(procedure);
		this.setCost(cost);
	}

	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Procedure getProcedure() {
		return procedure;
	}
	public void setProcedure(Procedure procedure) {
		this.procedure = procedure;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
    
    
}
