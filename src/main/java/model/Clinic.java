package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a Clinic and its functionality of storing all the information and what's going on in the Dentist Office
 */
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

    /**
     * The clinic will receive a payment
     * @param payment the Payment received
     */
    public void receivePayment(Payment payment) {
        this.getPayments().add(payment);
    }

    /**
     * @param patientId to search through patient to see that patient's balance
     * @return the account balance of that patient
     */
    public double getAccountBalance(int patientId) {
        for (int i = 0; i < this.getPatients().size(); i++) {
            if(this.getPatients().get(i).getUniqueId() == patientId){
                return this.getPatients().get(i).getBalance();
            }
        }
        return 0;
    }

    /**
     * @return The list of providers
     */
    public List<Provider> getProviders() {
        return providers;
    }

    /**
     * @return The list of patients
     */
    public List<Patient> getPatients() {
        return patients;
    }

    /**
     * @return The list of payments done
     */
    public List<Payment> getPayments() {
        return payments;
    }

    /**
     * @return The list of future appointments scheduled
     */
    public List<FutureAppointment> getFutureAppointments() {
        return futureAppointments;
    }

    /**
     * @return The list of procedures available to performed
     */
    public List<Procedure> getProcedures() {
        return procedures;
    }

    /**
     * @return The list of users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @return The list of patient's insurances that have been added to the dentist office
     */
    public List<Insurance> getInsurances() {
        return insurances;
    }

    /**
     * @return The list of past appointments performed
     */
    public List<AppointmentRecord> getPastAppointments() {
        return pastAppointments;
    }
}
