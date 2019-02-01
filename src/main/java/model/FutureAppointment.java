package model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class FutureAppointment extends Appointment {


    //    private List<Provider> providers;
//    private List<Procedure> procedures;
    private Map<Provider, List<Procedure>> procedureByProvider;

    public FutureAppointment(Patient patient, LocalDateTime dateTime, Map<Provider, List<Procedure>> procedureByProvider) {
        super(patient, dateTime);
        this.setProcedureByProvider(procedureByProvider);
    }

    public Map<Provider, List<Procedure>> getProcedureByProvider() {
        return procedureByProvider;
    }

    public void setProcedureByProvider(Map<Provider, List<Procedure>> procedureByProvider) {
        this.procedureByProvider = procedureByProvider;
    }
}
