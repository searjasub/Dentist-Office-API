package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Clinic implements Serializable {

    private List<Provider> providers = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private List<PaymentCard> cardPayments = new ArrayList<>();
    private List<FutureAppointment> futureAppointments = new ArrayList<>();
    private List<AppointmentRecord> pastAppointments = new ArrayList<>();
    private List<Procedure> procedures = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Insurance> insurances = new ArrayList<>();

    public void receivePayment(Payment payment) {
    }

    public double getAccountBalance(int patientId) {
        return 0;
    }


    //GETTER AND SETTERS
    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<PaymentCard> getCardPayments() {
        return cardPayments;
    }

    public void setCardPayments(List<PaymentCard> cardPayments) {
        this.cardPayments = cardPayments;
    }

    public List<FutureAppointment> getFutureAppointments() {
        return futureAppointments;
    }

    public void setFutureAppointments(List<FutureAppointment> futureAppointments) {
        this.futureAppointments = futureAppointments;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public List<AppointmentRecord> getPastAppointments() {
        return pastAppointments;
    }

    public void setPastAppointments(List<AppointmentRecord> pastAppointments) {
        this.pastAppointments = pastAppointments;
    }
}
