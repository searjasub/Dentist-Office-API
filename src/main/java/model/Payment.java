package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Define the payment functionality
 */
public class Payment implements Serializable {

    private static final long serialVersionUID = -226984859277583201L;

    private double amount;
    private Patient patient;
    private Source source;
    private LocalDate time;

    /**
     * Constructor of Payment
     * @param amount How much was paid
     * @param patient Which patient is paying for it
     * @param source If the the payment came from Insurance of the Patient
     */
    public Payment(double amount, Patient patient, Source source) {
        this.amount = amount;
        this.patient = patient;
        this.source = source;
    }

    public Payment(double amount, Patient patient, Source source, LocalDate time) {
        this.amount = amount;
        this.patient = patient;
        this.source = source;
        this.setTime(time);
    }

    /**
     * @return the amount paid
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @return the Patient who did the payment
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Set's the Patient who performed the payment
     * @param patient Who did it
     * @see Patient for more information about the Patient's information
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return The source of the payment
     */
    public Source getSource() {
        return source;
    }

    /**
     * Sets the source of payment
     * @param source either patient or insurance
     * @see Source
     */
    public void setSource(Source source) {
        this.source = source;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }
}
