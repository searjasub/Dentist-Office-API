package model;

import java.io.Serializable;

public class Payment implements Serializable {

    private static final long serialVersionUID = -226984859277583201L;

    private double amount;
    private Patient patient;
    private Source source;

    public Payment(double amount, Patient patient, Source source) {
        this.amount = amount;
        this.patient = patient;
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}
