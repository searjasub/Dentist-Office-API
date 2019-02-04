package view.menu;

import interfaces.ConsoleUI;
import model.*;

import java.io.IOException;
import java.util.List;

public class UserMenuInteraction {

    private final String defaultQuestion = "\nWhat would you like to do?";
    private final String editQuestion = "\nWhat would you like to change?";

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
        String[] menuOptions = new String[6];
        menuOptions[0] = "User";
        menuOptions[1] = "Provider";
        menuOptions[2] = "Patient";
        menuOptions[3] = "Appointment";
        menuOptions[4] = "Procedure";
        menuOptions[5] = "Exit";
        return menuOptions;
    }

    public int createStandardMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillCreateStandardMenu(), defaultQuestion);
    }

    private String[] fillCreateStandardMenu() {
        String[] menuOptions = new String[5];
        menuOptions[0] = "Provider";
        menuOptions[1] = "Patient";
        menuOptions[2] = "Appointment";
        menuOptions[3] = "Procedure";
        menuOptions[4] = "Exit";
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

    public int changeProviderInformationMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangeProviderInformation(), defaultQuestion);
    }

    private String[] fillChangeProviderInformation() {
        String[] options = new String[6];
        options[0] = "First Name";
        options[1] = "Last Name";
        options[2] = "Phone-Number";
        options[3] = "E-Mail";
        options[4] = "Provider Type";
        options[5] = "Exit";
        return options;
    }

    public int changePatientInformationMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangePatientInformation(), editQuestion);
    }

    private String[] fillChangePatientInformation() {
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

    public int changeFutureAppointmentMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangeFutureAppointment(), editQuestion);
    }

    private String[] fillChangeFutureAppointment() {
        String[] options = new String[4];
        options[0] = "Patient";
        options[1] = "Time";
        options[2] = "Procedure by provider";
        options[3] = "Exit";
        return options;
    }

    public int changeTimeMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangeTimeMenu(), editQuestion);
    }

    private String[] fillChangeTimeMenu() {
        String[] options = new String[3];
        options[0] = "Time";
        options[1] = "Day";
        options[2] = "Exit";
        return options;
    }

    public int changeProcedureInformation() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangeProcedureInformation(), editQuestion);
    }

    private String[] fillChangeProcedureInformation() {
        String[] options = new String[6];
        options[0] = "Patient";
        options[1] = "Code";
        options[2] = "Description";
        options[3] = "Cost";
        options[4] = "Provider";
        options[5] = "Exit";
        return options;
    }

    public int changeUserInformationMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangeUserInformationMenu(), editQuestion);
    }

    private String[] fillChangeUserInformationMenu() {
        String[] options = new String[6];
        options[0] = "Name";
        options[1] = "Last Name";
        options[2] = "Username";
        options[3] = "Password";
        options[4] = "User Role";
        options[5] = "Exit";
        return options;
    }

    public int changeUserOwnInformation() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillChangeOwnInformation(), editQuestion);
    }

    private String[] fillChangeOwnInformation() {
        String[] options = new String[5];
        options[0] = "Name";
        options[1] = "Last Name";
        options[2] = "Username";
        options[3] = "Password";
        options[4] = "Exit";
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
            throw new NotFoundException("There are no providers in record. Please add a provider first");
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = providers.get(i).getName() + " " + providers.get(i).getLastName() + " ("
                    + providers.get(i).getTitle().getType() + ")";
        }
        return (Provider) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public Patient selectPatient(List<Patient> patients, String message) throws IOException {

        Object[] list = patients.toArray();
        if (patients.isEmpty()) {
            throw new NotFoundException("There are no patients in record. Please add a patient first");
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = patients.get(i).getName() + " " + patients.get(i).getLastName();
        }
        return (Patient) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public FutureAppointment selectFutureAppointment(List<FutureAppointment> appointments, String message)
            throws IOException {
        Object[] list = appointments.toArray();
        if (appointments.isEmpty()) {
            throw new NotFoundException("There are no appointments in record. Please add an appointment first");
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = appointments.get(i).getPatient().getName() + appointments.get(i).getPatient().getLastName()
                    + "@ [" + appointments.get(i).getDateTime().toString() + "]";
        }
        return (FutureAppointment) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public Procedure selectProcedure(List<Procedure> procedures, String message) throws IOException {
        Object[] list = procedures.toArray();
        if (procedures.isEmpty()) {
            throw new NotFoundException("There are no procedures in record. Please add a procedure first");
        }
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = "Patient: " + procedures.get(i).getPatient().getName()
                    + " " + procedures.get(i).getPatient().getLastName()
                    + " | Code: " + procedures.get(i).getCode();
        }
        return (Procedure) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public Insurance selectInsurance(List<Insurance> insurances, String message) throws IOException {
        Object[] list = insurances.toArray();
        if (insurances.isEmpty()) {
            throw new NotFoundException("There are no insurances in record. Please add an insurance first");
        }
        String[] options = new String[list.length + 1];
        for (int i = 0; i < options.length - 1; i++) {
            options[i] = "Insurance Name: " + insurances.get(i).getName() + " | Group ID: " + insurances.get(i).getGroupId();
            if (i == options.length - 2) {
                options[i + 1] = "Include all insurances";
            }
        }
        return (Insurance) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public UserRole selectRole() throws IOException {
        UserRole role = null;
        String[] roleMenu = new String[2];
        roleMenu[0] = "Administrative";
        roleMenu[1] = "Standard";
        int selection = ConsoleUI.promptForMenuSelection(roleMenu, "Select new role");
        switch (selection){
            case  0:
                role = UserRole.ADMINISTRATIVE;
                break;
            case 1:
                role = UserRole.STANDARD;
                break;
        }
        return role;
    }
}
