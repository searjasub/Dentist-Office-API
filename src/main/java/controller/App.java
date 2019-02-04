package controller;

import model.*;
import view.UserInteraction;
import view.menu.UserMenuInteraction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class App {

    private static final String directory = "savables";
    private User currentUser;
    private UserInteraction userInteraction = new UserInteraction();
    private UserMenuInteraction userMenuInteraction = new UserMenuInteraction();
    private Clinic clinic = new Clinic();
    private HashMap<String, String> loginCredentials = new HashMap<>();

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
                    if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                        if (currentUser.getPassword().equals("1234Password")) {
                            userInteraction.print("Welcome to the Dentist Office App, since is your first login please ");
                            String newPass = passwordVerified(true);
                            for (User u : clinic.getUsers()) {
                                if (u.getUsername().equals(currentUser.getUsername())) {
                                    u.setPassword(newPass);
                                }
                            }
                            save();
                        }
                        int selection = userMenuInteraction.mainMenu();
                        isDone = mainMenuHandler(selection);
                    }
                }
            } while (true);
        }
    }

    private void addAdmin() throws IOException, ClassNotFoundException {
        User admin = new User("Administrator", "one", "admin", "1234Password", UserRole.ADMINISTRATIVE);
        clinic.getUsers().add(admin);
        autoSaveLoad();
        start();
    }

    private boolean mainMenuHandler(int selection) throws IOException, ClassNotFoundException {
        switch (selection) {
            case 0:
                //VIEW
                int choice0 = userMenuInteraction.viewMenu();
                viewMenuHandler(choice0);
                return false;
            case 1:
                //CREATE
                int choice1;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice1 = userMenuInteraction.createAdminMenu();
                    createAdminMenuHandler(choice1);
                } else {
                    choice1 = userMenuInteraction.createStandardMenu();
                    createStandardMenuHandler(choice1);
                }
                return false;
            case 2:
                //EDIT
                int choice2;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice2 = userMenuInteraction.editAdminMenu();
                    editAdminMenuHandler(choice2);
                } else {
                    choice2 = userMenuInteraction.editStandardMenu();
                    editStandardMenuHandler(choice2);
                }
                return false;
            case 3:
                //DELETE
                int choice3;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice3 = userMenuInteraction.deleteAdminMenu();
                    deleteAdminMenuHandler(choice3);
                } else {
                    choice3 = userMenuInteraction.deleteStandardMenu();
                    deleteStandardMenuHandler(choice3);
                }
                return false;
            case 4:
                //SEARCH
                int choice4 = userMenuInteraction.searchMenu();
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


    private void viewMenuHandler(int selection) {
        switch (selection) {
            case 0:
                //PRODUCTION
                break;
            case 1:
                //PATIENT BALANCE
                break;
            case 2:
                //COLLECTIONS
                break;
            case 3:
                //EXIT
                break;
            default:
                break;
        }
    }

    private void createAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                User newUser = new User(
                        userInteraction.getName(false),
                        userInteraction.getLastName(false),
                        checkUniqueUsername(),
                        passwordVerified(false),
                        userInteraction.getUserType());
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
                userInteraction.getUniqueID(),
                userInteraction.getEmail(),
                userInteraction.getPhoneNumber(),
                new Insurance(
                        userInteraction.getInsuranceName(),
                        userInteraction.getGroupId(),
                        userInteraction.getMemberId()),
                new PaymentCard(
                        userInteraction.getCardNumber(),
                        userInteraction.getExpMonth(),
                        userInteraction.getExpYear(),
                        userInteraction.getCardName(),
                        userInteraction.getCvv(),
                        userInteraction.getZipCode())));
        autoSaveLoad();
    }

    private void addAppointment() throws IOException {
        try {
            FutureAppointment fa = new FutureAppointment(
                    userMenuInteraction.selectPatient(clinic.getPatients(), "Select Patient"),
                    userInteraction.getFutureDate(),
                    getProcedureByProvider());
            clinic.getAppointments().add(fa);
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private HashMap<Provider, Procedure> getProcedureByProvider() throws IOException {
        HashMap<Provider, Procedure> procedureHashMap = new HashMap<>();
        Provider provider = userMenuInteraction.selectProvider(clinic.getProviders(), "Select Provider");
        Procedure procedure = userMenuInteraction.selectProcedure(clinic.getProcedures(), "Select Procedure");
        procedureHashMap.put(provider, procedure);

        return procedureHashMap;
    }

    private void addProcedure() throws IOException, ClassNotFoundException {
        try {
            Procedure procedure = new Procedure(
                    userMenuInteraction.selectPatient(clinic.getPatients(), "Select a patient."),
                    userInteraction.getCode(),
                    userInteraction.getDescription(),
                    userInteraction.getCost(),
                    userMenuInteraction.selectProvider(clinic.getProviders(), "Choose a provider"));
            clinic.getProcedures().add(procedure);
            autoSaveLoad();
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0://CHANGE OWN PASSWORD
                currentUser.changePassword(passwordVerified(false));
                save();
                break;
            case 1: //CHANGE PASSWORD FOR A SPECIFIED USER
                User who = userMenuInteraction.selectUser(clinic.getUsers(), "Who do you want to change the password");
                String newPassword = passwordVerified(false);
                who.setPassword(newPassword);
                save();
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

    private void editStandardMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                currentUser.changePassword(passwordVerified(false));
                autoSaveLoad();
                break;
            case 1:
                editProviderMenu();
                break;
            case 2:
                editPatientMenu();
                break;
            case 3:
                //appointments
                editAppointmentsMenu();
                break;
            case 4:
                //procedure
                editProcedure();
                break;
            case 5:
                userInteraction.println("You have exited the Edit Menu\n");
                break;
            default:
                break;
        }
    }

    private void editProviderMenu() throws IOException, ClassNotFoundException {
        try {
            Provider provider = userMenuInteraction.selectProvider(clinic.getProviders(), "Which Provider Would you like to Change?");
            int selection = userMenuInteraction.changeProviderInformationMenu();
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
            Patient patient = userMenuInteraction.selectPatient(clinic.getPatients(), "Choose A Patient to Edit");
            int selection = userMenuInteraction.changePatientInformationMenu();
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
        }
        autoSaveLoad();

    }

    private void editLastName(Object object) throws IOException, ClassNotFoundException {
        String lastName = userInteraction.getLastName(false);
        if (object instanceof Provider) {
            ((Provider) object).setLastName(lastName);
        } else if (object instanceof Patient) {
            ((Patient) object).setLastName(lastName);
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
            FutureAppointment appointment = userMenuInteraction.selectFutureAppointment(clinic.getAppointments(), "Select an appointment");
            int selection = userMenuInteraction.changeFutureAppointmentMenu();
            switch (selection) {
                case 0:
                    Patient selected = userMenuInteraction.selectPatient(clinic.getPatients(), "Select new a new patient");
                    appointment.setPatient(selected);
                    autoSaveLoad();
                    break;
                case 1:
                    int choice = userMenuInteraction.changeTimeMenu();
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
            Procedure procedure = userMenuInteraction.selectProcedure(clinic.getProcedures(), "Select a procedure");
            int selection = userMenuInteraction.changeProcedureInformation();
            switch (selection) {
                case 0:
                    //PATIENT
                    Patient patientSelected = userMenuInteraction.selectPatient(clinic.getPatients(), "Select new patient");
                    procedure.setPatient(patientSelected);
                    autoSaveLoad();
                    break;
                case 1:
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
                    Provider provider = userMenuInteraction.selectProvider(clinic.getProviders(), "Select a provider");
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

    //TODO DELETE
    private void deleteAdminMenuHandler(int choice) {
        switch (choice) {
            case 0:
                //user
                break;
            case 1:
                //all users
                break;
            case 2:
                //provider
                break;
            case 3:
                //patients
                break;
            case 4:
                //appointments
                break;
            case 5:
                //procedure
                break;
            case 6:
                //exit
                break;
            default:
                break;
        }
    }

    private void deleteStandardMenuHandler(int choice) {
        switch (choice) {
            case 0:
                //delete provider
                break;
            case 1:
                //delete patients
                break;
            case 2:
                //appointments
                break;
            case 3:
                //procedure
                break;
            case 4:
                //exit
                break;
            default:
                break;
        }
    }

    private void searchMenuHandler(int choice) throws IOException {
        switch (choice) {
            case 0://SEARCH PROVIDER
                List<Provider> found = new ArrayList<>();
                userInteraction.println("Instructions: If you leave any field blank it will not be consider for the search");
                String name = userInteraction.getName(true);
                String lastName = userInteraction.getLastName(true);
                ProviderType titleSelected = userInteraction.getProviderTypeWithEmptyEntry();

                for (int i = 0; i < clinic.getProviders().size(); i++) {
                    if (name.equals(clinic.getProviders().get(i).getName())) {
                        found.add(clinic.getProviders().get(i));
                    } else if (lastName.equals(clinic.getProviders().get(i).getLastName())) {
                        found.add(clinic.getProviders().get(i));
                    } else if (titleSelected == clinic.getProviders().get(i).getTitle()) {
                        found.add(clinic.getProviders().get(i));
                    }
                }
                if (found.isEmpty()) {
                    userInteraction.println(userInteraction.removeCharacters(clinic.getProviders().toString()));
                } else {
                    userInteraction.println(userInteraction.removeCharacters(found.toString()));
                }
                break;
            case 1:
                //SEARCH PATIENTS
                break;
            case 2:
                //SEARCH APPOINTMENTS
                break;
            case 3:
                //EXIT
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
