package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Clinic implements Serializable {

    private List<Provider> providers;
    private List<Patient> patients;
    private List<Payment> payments;
    private List<Appointment> appointments;


    private List<Procedure> procedures;
    private List<User> users = new ArrayList<>();


//    public List<Provider> searchProviders(ProviderSearchCriteria criteria){
//        return null;
//    }
//
//    public List<Patient> searchPatients(PatientSearchCriteria criteria){
//        return null;
//    }
//
//    public List<Appointment> searchAppointment(AppointmentSearchCriteria criteria){
//        return null;
//    }


    public void receivePayment(Payment payment) {

    }

    public double getAccountBalance(int patientId) {
        return 0;
    }

    public List<Appointment> getFutureAppointment() {
        return null;
    }

    public List<Appointment> getPastAppointment() {
        return null;
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

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
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
}
