package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {

    private Patient patient;
    private LocalDateTime dateTime;

    public Appointment(Patient patient, LocalDateTime dateTime) {
        this.setPatient(patient);
        this.setDateTime(dateTime);
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
