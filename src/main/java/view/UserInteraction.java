package view;


import interfaces.ConsoleUI;
import model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class UserInteraction {

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

    public void welcome() {
        ConsoleUI.displayMessage("Welcome to the management office", false);
    }

    public String changePassword() throws IOException {
        return ConsoleUI.promptForInput("Enter new password", false, false);
    }

    public String changePasswordSameLine() throws IOException {
        return ConsoleUI.promptForInput("enter new password\n", false, true);
    }

    public String verifyPassword() throws IOException {
        return ConsoleUI.promptForInput("Re-enter password", false, false);
    }

    public String getLoginUsername() throws IOException {
        ConsoleUI.displayMessage("Username:", true);
        return ConsoleUI.promptForInput(" ", false, true);
    }

    public String getLoginPassword() throws IOException {
        ConsoleUI.displayMessage("Password:", true);
        return ConsoleUI.promptForInput(" ", false, true);
    }

    public void println(String message) {
        System.out.println(message);
    }

    public void print(String message) {
        System.out.print(message);
    }

    public String getName(boolean allowEmpty) throws IOException {
        return ConsoleUI.promptForInput("Enter the name: ", allowEmpty, true);
    }

    public String getLastName(boolean allowEmpty) throws IOException {
        return ConsoleUI.promptForInput("Enter the last name: ", allowEmpty, true);
    }

    public String getUsername() throws IOException {
        return ConsoleUI.promptForInput("Enter the username: ", false, true);
    }

    public UserRole getUserType() throws IOException {
        String[] menuOptions = new String[2];
        menuOptions[0] = "Administrative";
        menuOptions[1] = "Standard";
        int selection = ConsoleUI.promptForMenuSelection(menuOptions, "What type of user?");
        if (selection == 0) {
            return UserRole.ADMINISTRATIVE;
        } else {
            return UserRole.STANDARD;
        }
    }

    public ProviderType getProviderType() throws IOException {
        int selection = ConsoleUI.promptForMenuSelection(fillProviderType(), "Who is the provider?");
        switch (selection) {
            case 0:
                return ProviderType.DENTIST;
            case 1:
                return ProviderType.ASSISTANT;
            case 2:
                return ProviderType.HYGIENIST;
            default:
                throw new IllegalArgumentException("Something went wrong in the UserMenuInteraction Class (You should not see me)");
        }
    }

    public ProviderType getProviderTypeWithEmptyEntry() throws IOException{
        String[] newOne = fillProviderType();
        newOne[3] = "All of them";
        int selection = ConsoleUI.promptForMenuSelection(newOne, "Select a criteria to search for?");
        switch (selection) {
            case 0:
                return ProviderType.DENTIST;
            case 1:
                return ProviderType.ASSISTANT;
            case 2:
                return ProviderType.HYGIENIST;
            case 3:
                return null;
            default:
                throw new IllegalArgumentException("Something went wrong in the UserMenuInteraction Class (You should not see me)");
        }
    }

    private String[] fillProviderType(){
        String[] menuOptions = new String[4];
        menuOptions[0] = "Dentist";
        menuOptions[1] = "Assistant";
        menuOptions[2] = "Hygienist";
        return menuOptions;
    }

    public Source getSource() throws IOException {
        String[] menuOptions = new String[2];
        menuOptions[0] = "Insurance";
        menuOptions[1] = "Patient";
        int selection = ConsoleUI.promptForMenuSelection(menuOptions, "What type of source?");
        if (selection == 0) {
            return Source.INSURANCE;
        } else {
            return Source.PATIENT;
        }
    }

    public int getUniqueID() throws IOException {
        int id;
        while (true) {
            id = ConsoleUI.promptForInt("Please enter unique ID number", 0, 99999);
            if (id < 9999) {
                println("The id is too short, please try again. (minimum 5 digits)");
            } else {
                return id;
            }
        }
    }

    public String getEmail() throws IOException {
        String email;
        while (true) {
            email = ConsoleUI.promptForInput("Enter email", false, false);
            for (int i = email.length() - 1; i > 0; i--) {
                if (email.charAt(i) == '@') {
                    for (int j = email.length() - 1; j > i; j--) {
                        if (email.charAt(j) == '.') {
                            return email;
                        }
                    }
                }
            }
            println("The email does not looks like \"example@email.com\"");
        }
    }

    public String getPhoneNumber() throws IOException {
        String phone;
        while (true) {
            phone = ConsoleUI.promptForInput("Please enter phone number", false, false);
            if (phone.length() > 10) {
                println("The phone number is too long, please try again. (maximum 10 digits)");
            } else if (phone.length() < 10) {
                println("The phone number is too short, please try again. (minimum 10 digits)");
                //TODO MAKE SURE YOU CAN'T PUT ANY LETTERS
            } else if (phone.matches("[a-zA-Z]")) {
                println("Only numbers allowed");
            } else {
                return phone;
            }
        }
    }

    public String getCode() throws IOException {
        String code;

        while (true) {
            code = ConsoleUI.promptForInput("Please enter code\nD", false, true);
            if (code.length() > 5) {
                println("The code is too long, please try again. (maximum 5 digits)");
            } else if (code.length() < 5) {
                println("The code is too short, please try again. (minimum 5 digits)");
                //TODO MAKE SURE YOU CAN'T PUT ANY LETTERS
            } else if (code.matches("[a-zA-Z]+")) {
                println("Only numbers allowed");
            } else {
                return code;
            }
        }
    }

    public String getDescription() throws IOException {
        return ConsoleUI.promptForInput("Description", false, false);
    }

    public double getCost() throws IOException {
        return ConsoleUI.promptForDouble("How much did it cost?", 0, Double.MAX_VALUE);
    }

    public String getInsuranceName() throws IOException {
        return ConsoleUI.promptForInput("Enter Insurance Name", false, false);
    }

    public String getGroupId() throws IOException {
        return ConsoleUI.promptForInput("Enter GroupID", false, false);
    }

    public String getMemberId() throws IOException {
        return ConsoleUI.promptForInput("Enter MemberID", false, false);
    }

    public String getCardNumber() throws IOException {
        return ConsoleUI.promptForInput("Please Enter A New Credit/Debit Card Number", false, false);
    }

    public int getExpMonth() throws IOException {
        return ConsoleUI.promptForInt("Please Enter An Expiration Month", 1, 12);
    }

    public int getExpYear() throws IOException {
        return ConsoleUI.promptForInt("Please Enter An Expiration Year", 2019, 2100);
    }

    public String getCardName() throws IOException {
        return ConsoleUI.promptForInput("Please Enter The Name On The Car", false, false);
    }

    public int getCvv() throws IOException {
        return ConsoleUI.promptForInt("Please Enter a New CVV Number", 0, 999);
    }

    public int getZipCode() throws IOException {
        return ConsoleUI.promptForInt("Please Enter A New Zip Code", 10000, 99999);
    }

    public LocalDateTime getFutureDate() throws IOException {
        return LocalDateTime.of(
                getYear(),
                getMonth(),
                getDay(),
                getHour(),
                getMinute());
    }

    public int getYear() throws IOException {
        return ConsoleUI.promptForInt("Enter Year", 2019, 2100);
    }

    public int getMonth() throws IOException {
        return ConsoleUI.promptForInt("Enter Month Number", 1, 12);
    }

    public int getDay() throws IOException {
        return ConsoleUI.promptForInt("Enter day", 1, 31);
    }

    public int getHour() throws IOException {
        return ConsoleUI.promptForInt("Enter Hour", 1, 24);
    }

    public int getMinute() throws IOException {
        return ConsoleUI.promptForInt("Enter Minutes", 0, 59);
    }

    public String getInput(String msg, boolean allowEmpty) throws IOException {
        return ConsoleUI.promptForInput(msg,allowEmpty,false);
    }

    public String removeCharacters(String content){
        String clean;
        clean = content.replace("[", "");
        clean = clean.replace("]", "");
        clean = clean.replace(",", "\n");
        return clean;
    }
}
