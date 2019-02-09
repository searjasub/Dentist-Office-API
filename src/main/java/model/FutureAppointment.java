package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Defines a future appointment
 */
public class FutureAppointment extends Appointment {

    private static final long serialVersionUID = -6048462759180827808L;

    private List<Procedure> procedures;

    /**
     * Constructor of the future appointment
     * @param procedures a List of procedures performed at the time of appointment
     * @see Appointment for information about the information inherited from Appointment
     */
    public FutureAppointment(Patient patient, LocalDateTime dateTime, boolean isComplete, List<Procedure> procedures) {
        super(patient, dateTime, isComplete);
        this.setProcedures(procedures);
    }

    /**
     * @return The list of procedures performed
     */
    public List<Procedure> getProcedures() {
        return procedures;
    }

    /**
     * Sets the list of procedures performed
     * @param procedures New list of procedures
     */
    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    /**
     * @return appointment's information
     * @see Appointment for more info about this method
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
