package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {

    private Patient patient;
    private LocalDateTime dateTime;
    private boolean isCompleted;

    public Appointment(Patient patient, LocalDateTime dateTime, boolean isCompleted) {
        this.setPatient(patient);
        this.setDateTime(dateTime);
        this.setCompleted(isCompleted);
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
