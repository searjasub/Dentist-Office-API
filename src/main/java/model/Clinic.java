package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Clinic implements Serializable {

    private static final long serialVersionUID = -752166931702151527L;

    private List<Provider> providers = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Payment> payments = new ArrayList<>();
    private List<FutureAppointment> futureAppointments = new ArrayList<>();
    private List<AppointmentRecord> pastAppointments = new ArrayList<>();
    private List<Procedure> procedures = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Insurance> insurances = new ArrayList<>();

    public void receivePayment(Payment payment) {
        this.getPayments().add(payment);
    }

    public double getAccountBalance(int patientId) {
        for (int i = 0; i < this.getPatients().size(); i++) {
            if(this.getPatients().get(i).getUniqueId() == patientId){
                return this.getPatients().get(i).getBalance();
            }
        }
        return 0;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<FutureAppointment> getFutureAppointments() {
        return futureAppointments;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public List<AppointmentRecord> getPastAppointments() {
        return pastAppointments;
    }

}
