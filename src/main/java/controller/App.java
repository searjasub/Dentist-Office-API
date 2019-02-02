package controller;

import model.*;
import view.UserInteraction;
import view.menu.UserMenuInteraction;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
                //production

                break;
            case 1:
                //patient balance
                break;
            case 2:
                //Collections
                break;
            case 3:
                //exit
                break;
            default:
                break;
        }
    }


    private void createAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                //user
                User newUser = new User(
                        userInteraction.getName(),
                        userInteraction.getLastName(),
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
                //exit
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

    public void createStandardMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                addProvider();
                break;
            case 1:
                addPatient();
                break;
            case 2:
                addAppointment();
                break;
            case 3:
                addProcedure();
                break;
            case 4:
                //exit
                break;
            default:
                break;
        }
    }


    private void addProvider() throws IOException, ClassNotFoundException {

        clinic.getProviders().add(new Provider(
                userInteraction.getName(),
                userInteraction.getLastName(),
                userInteraction.getProviderID(),
                userInteraction.getEmail(),
                userInteraction.getPhoneNumber(),
                userInteraction.getProviderType()));
        autoSaveLoad();
    }

    private void addPatient() {

    }

    private void addAppointment() {

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
        } catch (NullPointerException ex) {
            notFoundMessage("patient");

        }
    }

    private void editAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
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
            case 4:
                editAppointmentsMenu();
                break;
            case 5:
                //procedure
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
                //provider
                break;
            case 2:
                //patients
                break;
            case 3:
                //appointments
                break;
            case 4:
                //procedure
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
            int selection = userMenuInteraction.changeProviderInformation();
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
        } catch (NullPointerException ex) {
            notFoundMessage("provider");
        }
    }


    private void editPatientMenu() throws IOException, ClassNotFoundException {
        try {
            Patient patient = userMenuInteraction.selectPatient(clinic.getPatients(), "Choose A Patient to Edit");
            int selection = userMenuInteraction.changePatientInformation();
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
        } catch (NullPointerException ex) {
            notFoundMessage("patient");
        }
    }

    private void editName(Object object) throws IOException, ClassNotFoundException {
        String firstName = userInteraction.getName();
        if (object instanceof Provider) {
            ((Provider) object).setName(firstName);
        } else if (object instanceof Patient) {
            ((Patient) object).setName(firstName);
        }
        autoSaveLoad();
    }

    private void editLastName(Object object) throws IOException, ClassNotFoundException {
        String lastName = userInteraction.getLastName();
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
            ((Provider) object).setName(phoneNumber);
        } else if (object instanceof Patient) {
            ((Patient) object).setName(phoneNumber);
        }
        autoSaveLoad();
    }

    private void editEmail(Object object) throws IOException, ClassNotFoundException {
        String email = userInteraction.getEmail();
        if (object instanceof Provider) {
            ((Provider) object).setName(email);
        } else if (object instanceof Patient) {
            ((Patient) object).setName(email);
        }
        autoSaveLoad();
    }

    private void editAppointmentsMenu() {

    }

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

    private void searchMenuHandler(int choice) {
        switch (choice) {
            case 0:
                //providers
                break;
            case 1:
                //search patients
                break;
            case 2:
                //appointments
                break;
            case 3:
                //exit
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
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(directory + "\\save.db");
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(clinic.getUsers());
        out.close();
        out.flush();
        fileOutputStream.close();
    }

    private void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(directory + "\\save.db"));
        clinic.setUsers((List<User>) in.readObject());
        for (int i = 0; i < clinic.getUsers().size(); i++) {
            loginCredentials.put(clinic.getUsers().get(i).getUsername(), clinic.getUsers().get(i).getPassword());
        }
        in.close();
    }

    private void notFoundMessage(String type) {
        userInteraction.println("There are no " + type + "s in record. Please add a patient first");
    }
}
