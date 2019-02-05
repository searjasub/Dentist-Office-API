package model;

public class ProcedureRecord {

    private Patient patient;
    private Provider provider;
    private Procedure procedure;
    private double cost;

	public ProcedureRecord(Patient patient, Provider provider, Procedure procedure, double cost) {
		this.setPatient(patient);
		this.setProvider(provider);
		this.setProcedure(procedure);
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
