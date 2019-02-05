package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FutureAppointment extends Appointment {

    private HashMap<Provider, Procedure> procedureByProvider = new HashMap<>();

    public FutureAppointment(Patient patient, LocalDateTime dateTime, boolean isCompleted, HashMap<Provider, Procedure> procedureByProvider) {
        super(patient, dateTime, isCompleted);
        this.setProcedureByProvider(procedureByProvider);
    }

    public HashMap<Provider, Procedure> getProcedureByProvider() {
        return procedureByProvider;
    }

    public void setProcedureByProvider(HashMap<Provider, Procedure> procedureByProvider) {
        this.procedureByProvider = procedureByProvider;
    }
}
