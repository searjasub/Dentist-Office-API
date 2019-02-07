package model;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentRecord extends Appointment {

    private List<ProcedureRecord> procedures;

    public AppointmentRecord(Patient patient, LocalDateTime dateTime, boolean isCompleted, List<ProcedureRecord> procedures) {
        super(patient, dateTime, isCompleted);
        this.setProcedures(procedures);
    }

    public List<ProcedureRecord> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<ProcedureRecord> procedures) {
        this.procedures = procedures;
    }

    @Override
    public String toString() {

        String string = "";

        for (int i = 0; i < this.getProcedures().size(); i++) {
            string += "Provider: " + procedures.get(i).getProvider().getName() + procedures.get(i).getProvider().getLastName() + " | Description: " + procedures.get(i).getProcedure().getDescription() + " | Cost: " + procedures.get(i).getProcedure().getCost();
        }
        return string;
    }
}
