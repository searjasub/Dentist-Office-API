package model;

import java.io.Serializable;
import java.util.List;

public class Clinic implements Serializable {

    private List<Provider> providers;
    private List<Patient> patients;
    private List<Payment> payments;
    private List<Appointment> appointments;

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

    //TODO view details
    public double getAccountBalance(int patientId) {
        return 0;
    }

    public List<Appointment> getFutureAppointment() {
        return null;
    }

    public List<Appointment> getPastAppointment() {
        return null;
    }
}
