package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class FutureAppointment extends Appointment {

    //    private List<Provider> providers;
//    private List<Procedure> procedures;
    private HashMap<Provider, Procedure> procedureByProvider = new HashMap<>();

    public FutureAppointment(Patient patient, LocalDateTime dateTime, HashMap<Provider, Procedure> procedureByProvider) {
        super(patient, dateTime);
        this.setProcedureByProvider(procedureByProvider);
    }

    public Map<Provider, Procedure> getProcedureByProvider() {
        return procedureByProvider;
    }

    public void setProcedureByProvider(HashMap<Provider, Procedure> procedureByProvider) {
        this.procedureByProvider = procedureByProvider;
    }
}
