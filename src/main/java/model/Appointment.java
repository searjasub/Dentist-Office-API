package model;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Defines what an appointment is
 */
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
     * @return Patient the patient inside of this classs
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient who has/had the appointment
     * @param patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @return the time that the appointment was performed
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the time of the appointment
     * @param dateTime the new time
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return if the appointment has been completed or not
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * set the status of the appointment
     * @param completed if it has been completed or not
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    /**
     * Starting information of an appointment
     * @return the information
     */
    @Override
    public String toString() {
        return "Patient: " + patient.getName() + " " + patient.getLastName() + " [" + dateTime.toString() + "]";
    }
}
