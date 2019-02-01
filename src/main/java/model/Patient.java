package model;

public class Patient extends Person{

    private Insurance insurance;
    private PaymentCard paymentCard;

    public Patient(String name, String lastName, int uniqueId, String email, long phoneNumber, Insurance insurance, PaymentCard paymentCard) {
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
}
