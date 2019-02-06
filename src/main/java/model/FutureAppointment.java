package model;

import java.time.LocalDateTime;
import java.util.List;

public class FutureAppointment extends Appointment {

    private List<Procedure> procedures;

    public FutureAppointment(Patient patient, LocalDateTime dateTime, boolean isComplete, List<Procedure> procedures) {
        super(patient, dateTime, isComplete);
        this.setProcedures(procedures);
    }



    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }
}
