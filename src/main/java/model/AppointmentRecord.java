package model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Defines what an appointment record is
 */
public class AppointmentRecord extends Appointment {

    private static final long serialVersionUID = 8843901474642222173L;

    private List<ProcedureRecord> procedures;

    /**
     * Constructor of the appointment record
     * @param procedures list of procedures performed
     * @see Appointment for information about constructor, methods and parameter adquired by the parent class
     */
    public AppointmentRecord(Patient patient, LocalDateTime dateTime, boolean isCompleted, List<ProcedureRecord> procedures) {
        super(patient, dateTime, isCompleted);
        this.setProcedures(procedures);
    }

    /**
     * @return The list of procedure records performed
     */
    public List<ProcedureRecord> getProcedures() {
        return procedures;
    }

    /**
     * Sets the list of procedure records
     * @param procedures the list with all procedure records
     */
    public void setProcedures(List<ProcedureRecord> procedures) {
        this.procedures = procedures;
    }

    /**
     * @return the appointment record information
     */
    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < this.getProcedures().size(); i++) {
            string += "\n"+ i +".- Provider: " + procedures.get(i).getProcedure().getProvider().getName() + procedures.get(i).getProcedure().getProvider().getLastName() + " | Description: " + procedures.get(i).getProcedure().getDescription() + " | Cost: " + procedures.get(i).getProcedure().getCost();
        }
        return string;
    }
}
