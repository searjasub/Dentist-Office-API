package model;

import java.io.Serializable;

/**
 * Defines what a Patient is.
 * @see model.Person for more information of what parameters and methods does a person has
 */
public class Patient extends Person implements Serializable, Comparable<Patient> {

    private static final long serialVersionUID = 3225564947257667999L;

    private Insurance insurance;
    private PaymentCard paymentCard;
    private double balance;

    /**
     * Constructor of the Patient
     * @param name Patient's name
     * @param lastName Patient's last name
     * @param uniqueId Patient's unique ID
     * @param email Patient's email address
     * @param phoneNumber Patient's phone number
     * @param insurance Patient's Insurance
     * @param paymentCard Patient's payment card
     */
    public Patient(String name, String lastName, int uniqueId, String email, String phoneNumber, Insurance insurance, PaymentCard paymentCard) {
        super(name, lastName, uniqueId, email, phoneNumber);
        this.setInsurance(insurance);
        this.setPaymentCard(paymentCard);
    }

    /**
     * @return The insurance that the patient has
     * @see Insurance for more information
     */
    public Insurance getInsurance() {
        return insurance;
    }

    /**
     * Sets the Insurance of the Patient
     * @param insurance
     * @see Insurance for more information
     */
    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    /**
     * @return the payment card that the patient has
     * @see PaymentCard
     */
    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    /**
     * Set's the patient payment card
     * @param paymentCard a new card
     */
    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    /**
     * @return the patient clinic balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Add the balance to the patient account balance
     * @param balance the amount to be added
     */
    public void setBalance(double balance) {
        this.balance += balance;
    }

    /**
     * Adds to the end of the toString from Person the patient extra information
     * @return the information of the Patient
     * @see Person for information about the super.toString()
     */
    @Override
    public String toString() {
        return super.toString() + " | Insurance: " + getInsurance().getName() + " | Payment " + getPaymentCard().toString();
    }

    /**
     *
     * @param o other Patient to compare
     * @return negative if this Patient is greater than o | retuns possitve if the parameter o is greater | returns 0 if they are equal
     */
    @Override
    public int compareTo(Patient o) {
        return 0;
    }
}
