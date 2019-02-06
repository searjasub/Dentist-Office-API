package controller;

import model.*;
import view.DentistOfficeUserInteraction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClinicController {

    private static final String directory = "savables";
    private Clinic clinic = new Clinic();
    private User currentUser;
    //private UserInteraction userInteraction = new UserInteraction();
    private DentistOfficeUserInteraction userInteraction;
    private HashMap<String, String> loginCredentials = new HashMap<>();

    public ClinicController(DentistOfficeUserInteraction ui) {
        this.userInteraction = ui;
    }

    public void start() throws IOException, ClassNotFoundException {
        if (makeDirectory()) {
            save();
        }
        load();
        if (clinic.getUsers().isEmpty()) {
            addAdmin();
        } else {
            do {
                userInteraction.welcome();
                loginScreen();
                boolean isDone = false;
                while (!isDone) {
                    if (currentUser.isFirstTimePassChange()) {
                        userInteraction.print("Welcome to the Dentist Office, since is your first login please ");
                        String newPass = passwordVerified(true);
                        for (User u : clinic.getUsers()) {
                            if (u.getUsername().equals(currentUser.getUsername())) {
                                u.setPassword(newPass);
                                u.setFirstTimePassChange(false);
                            }
                        }
                        save();

                        int selection = userInteraction.mainMenu();
                        isDone = mainMenuHandler(selection);
                    } else {
                        int selection = userInteraction.mainMenu();
                        isDone = mainMenuHandler(selection);

                    }
                }
            } while (true);
        }
    }

    private void addAdmin() throws IOException, ClassNotFoundException {
        User admin = new User("Administrator", "one", "admin", "1234Password", UserRole.ADMINISTRATIVE, true);
        clinic.getUsers().add(admin);
        autoSaveLoad();
        start();
    }

    private boolean mainMenuHandler(int selection) throws IOException, ClassNotFoundException {
        switch (selection) {
            case 0:
                //VIEW
                int choice0 = userInteraction.viewMenu();
                viewMenuHandler(choice0);
                return false;
            case 1:
                //CREATE
                int choice1;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice1 = userInteraction.createAdminMenu();
                    createAdminMenuHandler(choice1);
                } else {
                    choice1 = userInteraction.createStandardMenu();
                    createStandardMenuHandler(choice1);
                }
                return false;
            case 2:
                //EDIT
                int choice2;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice2 = userInteraction.editAdminMenu();
                    editAdminMenuHandler(choice2);
                } else {
                    choice2 = userInteraction.editStandardMenu();
                    editStandardMenuHandler(choice2);
                }
                return false;
            case 3:
                //DELETE
                int choice3;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice3 = userInteraction.deleteAdminMenu();
                    deleteAdminMenuHandler(choice3);
                } else {
                    choice3 = userInteraction.deleteStandardMenu();
                    deleteStandardMenuHandler(choice3);
                }
                return false;
            case 4:
                //SEARCH
                int choice4 = userInteraction.searchMenu();
                searchMenuHandler(choice4);
                return false;
            case 5:
                //LOG OUT
                userInteraction.println("You have successfully logged out\n\n");
                currentUser = null;
                save();
                return true;
            default:
                break;
        }
        return false;
    }


    private void viewMenuHandler(int selection) throws IOException {
        switch (selection) {
            case 0:
                //PRODUCTION
                int totalAmount = 0;

                userInteraction.print("Select the time range for the production\nFrom:");
                LocalDate start = userInteraction.getLocalDate();
                userInteraction.print("\nUntil:");
                LocalDate end = userInteraction.getLocalDate();
                for (int i = 0; i < clinic.getPastAppointments().size(); i++) {
                    LocalDate tmp = LocalDate.of(
                            clinic.getPastAppointments().get(i).getDateTime().getYear(),
                            clinic.getPastAppointments().get(i).getDateTime().getMonth(),
                            clinic.getPastAppointments().get(i).getDateTime().getDayOfMonth());

                    if (tmp.isAfter(start) && tmp.isBefore(end)) {
                        if (clinic.getPastAppointments().get(i).isCompleted()) {
                            for (int j = 0; j < clinic.getPastAppointments().get(i).getProcedures().size(); j++) {
                                totalAmount += clinic.getPastAppointments().get(i).getProcedures().get(j).getCost();
                            }
                        }
                    }
                }
                userInteraction.print("The total amount collected by the clinic from\n" + start + "  -  " + end + "\n is: " + totalAmount);
                break;
            case 1:
                //PATIENT BALANCE
                break;
            case 2:
                //COLLECTIONS
                break;
            case 3:
                //APPOINTMENTS
                int selection2 = userInteraction.appointmentsMenu();
                switch (selection2) {
                    case 0://SHOW PAST APPOINTMENT
                        break;
                    case 1://MARK APPOINTMENTS AS COMPLETED
                        try {
                            FutureAppointment tmp = userInteraction.selectFutureAppointment(clinic.getFutureAppointments(), "Select an appointment to mark as complete");
                            if (!userInteraction.isCompleted("Would you like to mark this appointment as complete")) {
                                tmp.setCompleted(true);

                            }
                        } catch (NotFoundException ex) {
                            userInteraction.println(ex.getObject());
                        }


//                            clinic.getPastAppointments().add(new AppointmentRecord(
//                                    tmp.getPatient(),
//                                    tmp.getDateTime(),
//                                    true,
//                                    getProcedureRecords(tmp.getPatient())
//                            ));

                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
                break;
            case 4:
                break;
            default:
                break;
        }

    }

    private List<ProcedureRecord> getProcedureRecords(Patient patient) throws IOException {
        List<ProcedureRecord> list = new ArrayList<>();

        ProcedureRecord procedureRecord = new ProcedureRecord(
                patient,
                userInteraction.selectProvider(clinic.getProviders(), "Select Provider"),
                userInteraction.selectProcedure(clinic.getProcedures(), "Select Procedure"),
                userInteraction.getCost());
        list.add(procedureRecord);


        return list;
    }


    private void createAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                User newUser = new User(
                        userInteraction.getName(false),
                        userInteraction.getLastName(false),
                        checkUniqueUsername(),
                        passwordVerified(false),
                        userInteraction.getUserType(),
                        true);
                clinic.getUsers().add(newUser);
                autoSaveLoad();
                break;
            case 1:
                addProvider();
                break;
            case 2:
                addPatient();
                break;
            case 3:
                addAppointment();
                break;
            case 4:
                addProcedure();
                break;
            case 5:
                //EXIT
                break;
            default:
                break;
        }
    }

    private String checkUniqueUsername() throws IOException {
        String username;
        while (true) {
            username = userInteraction.getUsername();
            if (loginCredentials.get(username) == null) {
                return username;
            }
            userInteraction.println("\nUsername already exist. Please choose another one.");
        }
    }

    private void createStandardMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                addProvider();
                break;
            case 1:
                addPatient();
                break;
            case 2:
                addAppointment();
                autoSaveLoad();
                break;
            case 3:
                addProcedure();
                break;
            case 4:
                //EXIT
                break;
            default:
                break;
        }
    }

    private void addProvider() throws IOException, ClassNotFoundException {
        clinic.getProviders().add(new Provider(
                userInteraction.getName(false),
                userInteraction.getLastName(false),
                userInteraction.getUniqueID(),
                userInteraction.getEmail(),
                userInteraction.getPhoneNumber(),
                userInteraction.getProviderType()));
        autoSaveLoad();
    }

    private void addPatient() throws IOException, ClassNotFoundException {
        clinic.getPatients().add(new Patient(
                userInteraction.getName(false),
                userInteraction.getLastName(false),
                //TODO MAKE SURE IS A UNIQUE ID
                userInteraction.getUniqueID(),
                userInteraction.getEmail(),
                userInteraction.getPhoneNumber(),
                getInsuranceInfo(),
                new PaymentCard(
                        userInteraction.getCardNumber(),
                        userInteraction.getExpMonth(),
                        userInteraction.getExpYear(),
                        userInteraction.getCardName(),
                        userInteraction.getCvv(),
                        userInteraction.getZipCode())));


        autoSaveLoad();
    }

    private Insurance getInsuranceInfo() throws IOException {
        Insurance insurance = new Insurance(
                userInteraction.getInsuranceName(),
                userInteraction.getGroupId(),
                userInteraction.getMemberId());
        clinic.getInsurances().add(insurance);
        return insurance;
    }

    private void addAppointment() throws IOException {
        try {
            FutureAppointment fa = new FutureAppointment(
                    userInteraction.selectPatient(clinic.getPatients(), "Select Patient"),
                    userInteraction.getFutureDate(),
                    false,
                    addProcedures());
            clinic.getFutureAppointments().add(fa);
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private List<Procedure> addProcedures() throws IOException {
        List<Provider> tmp;

        while (true) {
            for (int i = 0; i < clinic.getProviders().size(); i++) {
                Provider provider = userInteraction.selectProvider()
            }
        }

    }

    private void addProcedure() throws IOException, ClassNotFoundException {
        try {
            Procedure procedure = new Procedure(
                    userInteraction.getCode(),
                    userInteraction.getDescription(),
                    userInteraction.getCost(),
                    userInteraction.selectProvider(clinic.getProviders(), "Choose a provider"));
            clinic.getProcedures().add(procedure);
            autoSaveLoad();
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0://CHANGE OWN PASSWORD
                editUserMenu_standardView();
                break;
            case 1://EDIT USERS
                editUserMenu_adminView();
                break;
            case 2: //EDIT PROVIDER
                editProviderMenu();
                break;
            case 3://EDIT PATIENTS
                editPatientMenu();

                break;
            case 4://EDIT APPOINTMENTS
                editAppointmentsMenu();
                break;
            case 5://EDIT PROCEDURES
                editProcedure();
                break;
            case 6:
                userInteraction.println("You have exited the Edit Menu\n");
                //exit
                break;
            default:
                break;
        }
    }

    private void editUserMenu_adminView() throws IOException, ClassNotFoundException {
        User user = userInteraction.selectUser(clinic.getUsers(), "Select which user you would like to edit his information");
        int selection = userInteraction.changeUserInformationMenu();
        switch (selection) {
            case 0:
                editName(user);
                break;
            case 1:
                editLastName(user);
                break;
            case 2:
                editUsername(user);
                break;
            case 3:
                editPassword(user);
                break;
            case 4:
                UserRole role = userInteraction.selectRole();
                user.setUserRole(role);
                autoSaveLoad();
            case 5:
                break;
        }
    }

    private void editStandardMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                editUserMenu_standardView();
                break;
            case 1:
                editProviderMenu();
                break;
            case 2:
                editPatientMenu();
                break;
            case 3:
                editAppointmentsMenu();
                break;
            case 4:
                editProcedure();
                break;
            case 5:
                userInteraction.println("You have exited the Edit Menu\n");
                break;
            default:
                break;
        }
    }

    private void editUserMenu_standardView() throws IOException, ClassNotFoundException {
        int selection = userInteraction.changeUserOwnInformation();
        switch (selection) {
            case 0:
                editName(currentUser);
                break;
            case 1:
                editLastName(currentUser);
                break;
            case 2:
                editUsername(currentUser);
                break;
            case 3:
                editPassword(currentUser);
                break;
            case 4:
                break;
        }
    }

    private void editUsername(User user) throws IOException, ClassNotFoundException {
        String newUsername = userInteraction.getUsername();
        user.setUsername(newUsername);
        autoSaveLoad();
    }

    private void editPassword(User user) throws IOException, ClassNotFoundException {
        String newPassword = passwordVerified(false);
        user.setPassword(newPassword);
        autoSaveLoad();
    }

    private void editProviderMenu() throws IOException, ClassNotFoundException {
        try {
            Provider provider = userInteraction.selectProvider(clinic.getProviders(), "Which Provider Would you like to Change?");
            int selection = userInteraction.changeProviderInformationMenu();
            switch (selection) {
                case 0:
                    editName(provider);
                    break;
                case 1:
                    editLastName(provider);
                case 2:
                    editPhoneNumber(provider);
                case 3:
                    editEmail(provider);
                case 4:
                    ProviderType type = provider.getTitle();
                    provider.setTitle(type);
                    autoSaveLoad();
                case 5:
                    userInteraction.println("\nReturning to Main Menu...\n");
                default:
                    break;
            }
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editPatientMenu() throws IOException, ClassNotFoundException {
        try {
            Patient patient = userInteraction.selectPatient(clinic.getPatients(), "Choose A Patient to Edit");
            int selection = userInteraction.changePatientInformationMenu();
            switch (selection) {
                case 0:
                    editName(patient);
                    break;
                case 1:
                    editLastName(patient);
                    break;
                case 2:
                    editEmail(patient);
                    break;
                case 3:
                    editPhoneNumber(patient);
                    break;
                case 4:
                    Insurance insurance = new Insurance(
                            userInteraction.getInsuranceName(),
                            userInteraction.getGroupId(),
                            userInteraction.getMemberId());
                    patient.setInsurance(insurance);
                    autoSaveLoad();
                    break;
                case 5:
                    PaymentCard card = new PaymentCard(
                            userInteraction.getCardNumber(),
                            userInteraction.getExpMonth(),
                            userInteraction.getExpYear(),
                            userInteraction.getCardName(),
                            userInteraction.getCvv(),
                            userInteraction.getZipCode());
                    patient.setPaymentCard(card);
                    autoSaveLoad();
                    break;
                case 6:
                    userInteraction.println("\nReturning to Main Menu...\n");
                    break;
                default:
                    break;
            }
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editName(Object object) throws IOException, ClassNotFoundException {
        String firstName = userInteraction.getName(false);
        if (object instanceof Provider) {
            ((Provider) object).setName(firstName);
        } else if (object instanceof Patient) {
            ((Patient) object).setName(firstName);
        } else if (object instanceof User) {
            ((User) object).setName(firstName);
        }
        autoSaveLoad();

    }

    private void editLastName(Object object) throws IOException, ClassNotFoundException {
        String lastName = userInteraction.getLastName(false);
        if (object instanceof Provider) {
            ((Provider) object).setLastName(lastName);
        } else if (object instanceof Patient) {
            ((Patient) object).setLastName(lastName);
        } else if (object instanceof User) {
            ((User) object).setLastName(lastName);
        }
        autoSaveLoad();
    }

    private void editPhoneNumber(Object object) throws IOException, ClassNotFoundException {
        String phoneNumber = userInteraction.getPhoneNumber();
        if (object instanceof Provider) {
            ((Provider) object).setPhoneNumber(phoneNumber);
        } else if (object instanceof Patient) {
            ((Patient) object).setPhoneNumber(phoneNumber);
        }
        autoSaveLoad();
    }

    private void editEmail(Object object) throws IOException, ClassNotFoundException {
        String email = userInteraction.getEmail();
        if (object instanceof Provider) {
            ((Provider) object).setEmail(email);
        } else if (object instanceof Patient) {
            ((Patient) object).setEmail(email);
        }
        autoSaveLoad();
    }

    private void editAppointmentsMenu() throws IOException, ClassNotFoundException {
        try {
            FutureAppointment appointment = userInteraction.selectFutureAppointment(clinic.getFutureAppointments(), "Select an appointment");
            int selection = userInteraction.changeFutureAppointmentMenu();
            switch (selection) {
                case 0:
                    Patient selected = userInteraction.selectPatient(clinic.getPatients(), "Select new a new patient");
                    appointment.setPatient(selected);
                    autoSaveLoad();
                    break;
                case 1:
                    int choice = userInteraction.changeTimeMenu();
                    switch (choice) {
                        case 0:
                            LocalDateTime newTime = LocalDateTime.of(
                                    appointment.getDateTime().getYear(),
                                    appointment.getDateTime().getMonth(),
                                    appointment.getDateTime().getDayOfMonth(),
                                    userInteraction.getHour(),
                                    userInteraction.getMinute());
                            appointment.setDateTime(newTime);
                            autoSaveLoad();
                            break;
                        case 1:
                            LocalDateTime newDay = LocalDateTime.of(
                                    userInteraction.getYear(),
                                    userInteraction.getMonth(),
                                    userInteraction.getDay(),
                                    appointment.getDateTime().getHour(),
                                    appointment.getDateTime().getMinute());
                            appointment.setDateTime(newDay);
                            autoSaveLoad();
                            break;
                        case 2:
                            break;
                    }
                    break;
                case 2:


                    break;
                case 3:
                    break;
            }
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editProcedure() throws IOException, ClassNotFoundException {
        try {
            Procedure procedure = userInteraction.selectProcedure(clinic.getProcedures(), "Select a procedure");
            int selection = userInteraction.changeProcedureInformation();
            switch (selection) {
                case 0:
                    //CODE
                    String code = userInteraction.getCode();
                    procedure.setCode(code);
                    autoSaveLoad();
                    break;
                case 2:
                    //DESCRIPTION
                    String description = userInteraction.getDescription();
                    procedure.setDescription(description);
                    autoSaveLoad();
                    break;
                case 3:
                    //COST
                    double cost = userInteraction.getCost();
                    procedure.setCost(cost);
                    autoSaveLoad();
                    break;
                case 4:
                    //PROVIDER
                    Provider provider = userInteraction.selectProvider(clinic.getProviders(), "Select a provider");
                    procedure.setProvider(provider);
                    autoSaveLoad();
                    break;
                case 5:
                    //EXIT
                    break;
            }
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void deleteAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                User user = userInteraction.selectUser(clinic.getUsers(), "Select an User to delete");
                for (int i = 0; i < clinic.getUsers().size(); i++) {
                    if (user.getUsername().equals(clinic.getUsers().get(i).getUsername())) {
                        clinic.getUsers().remove(user);
                    }
                }
                autoSaveLoad();
                break;
            case 1:
                clinic.getUsers().clear();
                clinic.getUsers().add(currentUser);
                break;
            case 2:
                deleteProvider();
                break;
            case 3:
                deletePatient();
                break;
            case 4://TODO
                //Appointment

                break;
            case 5:
                deleteProcedure();
                break;
            case 6://exit
                break;
            default:
                break;
        }
    }

    private void deleteStandardMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                deleteProvider();
                break;
            case 1:
                deletePatient();
                break;
            case 2:
                //appointments
                break;
            case 3:
                deleteProcedure();
                break;
            case 4://exit
                break;
            default:
                break;
        }
    }

    private void deleteProvider() throws IOException, ClassNotFoundException {
        Provider provider = userInteraction.selectProvider(clinic.getProviders(), "Select a Provider to delete");
        for (int i = 0; i < clinic.getProviders().size(); i++) {
            if (provider.getUniqueId() == clinic.getProviders().get(i).getUniqueId()) {
                clinic.getProviders().remove(provider);
            }
        }
        autoSaveLoad();
    }

    private void deletePatient() throws IOException, ClassNotFoundException {
        Patient patient = userInteraction.selectPatient(clinic.getPatients(), "Select a Patient to delete");
        for (int i = 0; i < clinic.getPatients().size(); i++) {
            if (patient.getUniqueId() == clinic.getPatients().get(i).getUniqueId()) {
                clinic.getPatients().remove(patient);
            }
        }
        autoSaveLoad();
    }

    private void deleteProcedure() throws IOException, ClassNotFoundException {
        Procedure procedure = userInteraction.selectProcedure(clinic.getProcedures(), "Select a Procedure to delete");
        for (int i = 0; i < clinic.getProcedures().size(); i++) {
            if (procedure.getCode().equals(clinic.getProcedures().get(i).getCode())) {
                clinic.getProcedures().remove(procedure);
            }
        }
        autoSaveLoad();
    }

    private void searchMenuHandler(int choice) throws IOException {
        String defaultMessage = "Instructions: If you leave any field blank it will not be consider for the search. If you leave all of them it will search everything";
        switch (choice) {
            case 0://SEARCH PROVIDER
                List<Provider> providerList = new ArrayList<>();
                userInteraction.println(defaultMessage);
                String providerName = userInteraction.getName(true);
                String providerLastName = userInteraction.getLastName(true);
                ProviderType titleSelected = userInteraction.getProviderTypeWithEmptyEntry();


                for (int i = 0; i < clinic.getProviders().size(); i++) {
                    if (providerName.equals(clinic.getProviders().get(i).getName())) {
                        providerList.add(clinic.getProviders().get(i));
                    } else if (providerLastName.equals(clinic.getProviders().get(i).getLastName())) {
                        providerList.add(clinic.getProviders().get(i));
                    } else if (titleSelected == clinic.getProviders().get(i).getTitle()) {
                        providerList.add(clinic.getProviders().get(i));
                    }
                }
                if (providerList.isEmpty()) {
                    userInteraction.println(userInteraction.removeCharacters(clinic.getProviders().toString()));
                } else {
                    userInteraction.println(userInteraction.removeCharacters(providerList.toString()));
                }
                break;
            case 1://SEARCH PATIENTS
                List<Patient> patientList = new ArrayList<>();
                userInteraction.println(defaultMessage);
                String patientName = userInteraction.getName(true);
                String patientLastName = userInteraction.getLastName(true);
                Insurance insurance = null;
                try {
                    insurance = userInteraction.selectInsurance(clinic.getInsurances(), "Select insurance to search for");
                } catch (NotFoundException ex) {
                    System.out.println(ex.getObject());
                }
                for (int i = 0; i < clinic.getPatients().size(); i++) {
                    if (patientName.equals(clinic.getPatients().get(i).getName())) {
                        patientList.add(clinic.getPatients().get(i));
                    } else if (patientLastName.equals(clinic.getPatients().get(i).getLastName())) {
                        patientList.add(clinic.getPatients().get(i));
                    } else if (insurance == clinic.getInsurances().get(i)) {
                        patientList.add(clinic.getPatients().get(i));
                    }
                }
                if (patientList.isEmpty()) {
                    userInteraction.println(userInteraction.removeCharacters(clinic.getPatients().toString()));
                } else {
                    userInteraction.println(userInteraction.removeCharacters(patientList.toString()));
                }
                break;
            case 2://TODO
                //SEARCH APPOINTMENTS
                //By time frame(all appointments between), by provider, by patient, by procedure code


                break;
            case 3://EXIT
                break;
            default:
                break;
        }
    }

    private String passwordVerified(boolean isFirstTime) throws IOException {
        String newPassword = null;
        boolean isValid = false;
        while (!isValid) {
            if (isFirstTime) {
                newPassword = userInteraction.changePasswordSameLine();
            } else {
                newPassword = userInteraction.changePassword();
            }
            String verification = userInteraction.verifyPassword();
            if (newPassword.equals(verification)) {
                isValid = true;
                currentUser.setPassword(newPassword);
            } else {
                userInteraction.println("The passwords don't match, please try again.");
            }
        }
        return newPassword;
    }

    private void loginScreen() throws IOException {
        boolean isValid = false;
        while (!isValid) {
            String username = userInteraction.getLoginUsername();
            String password = userInteraction.getLoginPassword();
            if (password.equals(loginCredentials.get(username))) {
                for (int i = 0; i < clinic.getUsers().size(); i++) {
                    if (clinic.getUsers().get(i).getUsername().equals(username)) {
                        currentUser = clinic.getUsers().get(i);
                    }
                }
                isValid = true;
            } else {
                userInteraction.println("The credentials are incorrect, please try again.\n");
            }
        }
    }

    private void autoSaveLoad() throws IOException, ClassNotFoundException {
        save();
        load();
    }

    private void save() throws IOException {
        makeDirectory();
        FileOutputStream fileOutputStream = new FileOutputStream(directory + "\\save.db");
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(clinic);
        out.close();
        out.flush();
        fileOutputStream.close();
    }

    private void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(directory + "\\save.db"));
        clinic = (Clinic) in.readObject();
        for (int i = 0; i < clinic.getUsers().size(); i++) {
            loginCredentials.put(clinic.getUsers().get(i).getUsername(), clinic.getUsers().get(i).getPassword());
        }
        in.close();
    }

    private boolean makeDirectory() throws IOException {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            return true;
        }
        return false;
    }
}
