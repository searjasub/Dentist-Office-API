package view.menu;

import interfaces.ConsoleUI;
import model.*;

import java.io.IOException;
import java.util.List;

public class UserMenuInteraction {

    private final String defaultQuestion = "\nWhat would you like to do?";

    public int mainMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillMainMenu(), defaultQuestion);
    }

    private String[] fillMainMenu() {
        String[] menuOptions = new String[6];
        menuOptions[0] = "View";
        menuOptions[1] = "Create";
        menuOptions[2] = "Edit";
        menuOptions[3] = "Delete";
        menuOptions[4] = "Search";
        menuOptions[5] = "Log out";
        return menuOptions;
    }

    public int viewMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillViewMenu(), defaultQuestion);
    }

    private String[] fillViewMenu() {
        String[] menuOptions = new String[4];
        menuOptions[0] = "Production";
        menuOptions[1] = "Patient Balance";
        menuOptions[2] = "Collections";
        menuOptions[3] = "Exit";
        return menuOptions;
    }

    public int createAdminMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillCreateAdminMenu(), defaultQuestion);
    }

    private String[] fillCreateAdminMenu() {
        String[] menuOptions = new String[7];
        menuOptions[0] = "User";
        menuOptions[1] = "Provider";
        menuOptions[2] = "Patient";
        menuOptions[3] = "Appointment";
        menuOptions[4] = "Procedure";
        menuOptions[5] = "Insurance Company";
        menuOptions[6] = "Exit";
        return menuOptions;
    }

    public int createStandardMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillCreateStandardMenu(), defaultQuestion);
    }

    private String[] fillCreateStandardMenu() {
        String[] menuOptions = new String[6];
        menuOptions[0] = "Provider";
        menuOptions[1] = "Patient";
        menuOptions[2] = "Appointment";
        menuOptions[3] = "Procedure";
        menuOptions[4] = "Insurance Company";
        menuOptions[5] = "Exit";
        return menuOptions;
    }

    public int editStandardMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillStandardEditMenu(), defaultQuestion);
    }

    private String[] fillStandardEditMenu() {
        String[] menuOptions = new String[6];
        menuOptions[0] = "Own Password";
        menuOptions[1] = "Provider";
        menuOptions[2] = "Patient";
        menuOptions[3] = "Appointment";
        menuOptions[4] = "Procedure";
        menuOptions[5] = "Exit";
        return menuOptions;
    }

    public int editAdminMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillAdminEditMenu(), defaultQuestion);
    }

    private String[] fillAdminEditMenu() {
        String[] menuOptions = new String[7];
        menuOptions[0] = "Own Password";
        menuOptions[1] = "Other User Password";
        menuOptions[2] = "Provider";
        menuOptions[3] = "Patient";
        menuOptions[4] = "Appointment";
        menuOptions[5] = "Procedure";
        menuOptions[6] = "Exit";
        return menuOptions;
    }

    public int deleteAdminMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillDeleteAdminMenu(), defaultQuestion);
    }

    private String[] fillDeleteAdminMenu() {
        String[] menuOptions = new String[7];
        menuOptions[0] = "User";
        menuOptions[1] = "All users";
        menuOptions[2] = "Providers";
        menuOptions[3] = "Patients";
        menuOptions[4] = "Appointments";
        menuOptions[5] = "Procedure";
        menuOptions[6] = "Exit";
        return menuOptions;
    }

    public int deleteStandardMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillDeleteStandardMenu(), defaultQuestion);
    }

    private String[] fillDeleteStandardMenu() {
        String[] menuOptions = new String[5];
        menuOptions[0] = "Providers";
        menuOptions[1] = "Patients";
        menuOptions[2] = "Appointments";
        menuOptions[3] = "Procedure";
        menuOptions[4] = "Exit";
        return menuOptions;
    }

    public int searchMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillSearchMenu(), defaultQuestion);
    }

    private String[] fillSearchMenu() {
        String[] menuOptions = new String[4];
        menuOptions[0] = "Providers";
        menuOptions[1] = "Patients";
        menuOptions[2] = "Appointments";
        menuOptions[3] = "Exit";
        return menuOptions;
    }

    public int changeProviderInformation() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangeProviderInformation(), defaultQuestion);
    }

    public String[] fillChangeProviderInformation() {
        String[] options = new String[6];
        options[0] = "First Name";
        options[1] = "Last Name";
        options[2] = "Phone-Number";
        options[3] = "E-Mail";
        options[4] = "Provider Type";
        options[5] = "Exit";
        return options;
    }

    public int changePatientInformation() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangePatientInformation(), defaultQuestion);
    }

    public String[] fillChangePatientInformation() {
        String[] options = new String[7];
        options[0] = "First Name";
        options[1] = "Last Name";
        options[2] = "E-Mail";
        options[3] = "Phone Number";
        options[4] = "Insurance";
        options[5] = "Payment Card";
        options[6] = "Exit";
        return options;
    }

    public User selectUser(List<User> users, String message) throws IOException {
        Object[] list = users.toArray();
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = users.get(i).getName() + " " + users.get(i).getLastName();
        }
        return (User) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public Provider selectProvider(List<Provider> providers, String message) throws IOException {
        Object[] list = providers.toArray();
        if (providers.isEmpty()) {
            throw new NullPointerException();
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = providers.get(i).getName() + " " + providers.get(i).getLastName() + " ("
                    + providers.get(i).getTitle().getType() + ")";
        }
        return (Provider) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public Insurance selectInsurance(List<Insurance> insurances, String message) throws IOException {
        Object[] list = insurances.toArray();
        if (insurances.isEmpty()) {
            throw new NullPointerException();
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = insurances.get(i).getName() + " | Group ID: " + insurances.get(i).getGroupId();
        }
        return (Insurance) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public Patient selectPatient(List<Patient> patients, String message) throws IOException {

        Object[] list = patients.toArray();
        if (patients.isEmpty()) {
            throw new NullPointerException();
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = patients.get(i).getName();
        }
        return (Patient) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public FutureAppointment selectFutureAppointment(List<FutureAppointment> appointments, String message)
            throws IOException {
        Object[] list = appointments.toArray();
        if (appointments.isEmpty()) {
            throw new NullPointerException();
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
//            options[i] = appointments.get(i)
        }
        return (FutureAppointment) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public Procedure selectProcedure(List<Procedure> procedures, String message) throws IOException {
        Object[] list = procedures.toArray();
        if (procedures.isEmpty()) {
            throw new NullPointerException();
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = "Patient: " + procedures.get(i).getPatient().getName()
                    + " " + procedures.get(i).getPatient().getLastName()
                    + " | Code: " + procedures.get(i).getCode();
        }
        return (Procedure) list[ConsoleUI.promptForMenuSelection(options, message)];

    }
}
