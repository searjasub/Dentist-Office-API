package model;

import java.io.Serializable;

public class Patient extends Person implements Serializable {

    private Insurance insurance;
    private PaymentCard paymentCard;
    private AppointmentRecord appointmentRecords;

    public Patient(String name, String lastName, int uniqueId, String email, String phoneNumber, Insurance insurance, PaymentCard paymentCard) {
        super(name, lastName, uniqueId, email, phoneNumber);
        this.setInsurance(insurance);
        this.setPaymentCard(paymentCard);
    }

    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public PaymentCard getPaymentCard() {
        return paymentCard;
    }

    public void setPaymentCard(PaymentCard paymentCard) {
        this.paymentCard = paymentCard;
    }

    @Override
    public String toString() {
        return super.toString() + " | Insurance: " + getInsurance().getName() + " | Payment " + getPaymentCard().toString();
    }
}
