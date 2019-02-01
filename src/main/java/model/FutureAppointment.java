package model;

import java.util.List;
import java.util.Map;

public class FutureAppointment extends Appointment {

    private Patient patient;
//    private List<Provider> providers;
//    private List<Procedure> procedures;
    private Map<Provider, List<Procedure>> procedureByProvider;


}
