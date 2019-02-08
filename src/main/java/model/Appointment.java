package model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Patient patient;
    private LocalDateTime dateTime;
    private boolean isCompleted;

    /**
     * Constructs a new Appointment that has a patient, time and if is completed or not
     * @param patient who schedule the appointment.
     * @param dateTime the time that the appointment will take place.
     * @param isCompleted to manage when the appointment should be moved to a past appointment.
     */
    public Appointment(Patient patient, LocalDateTime dateTime, boolean isCompleted) {
        this.setPatient(patient);
        this.setDateTime(dateTime);
        this.setCompleted(isCompleted);
    }

    /**
     *
     * @return
     */
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
