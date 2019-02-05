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


}
