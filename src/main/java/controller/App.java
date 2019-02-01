package controller;

import model.Clinic;
import model.Provider;
import model.User;
import model.UserRole;
import view.UserInteraction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class App {

    //TODO dentist database with helper methods of list?
    private static final String directory = "savables";
    private User currentUser;
    private UserInteraction userInteraction = new UserInteraction();
    private Clinic clinic = new Clinic();
    private HashMap<String, String> loginCredentials = new HashMap<>();

    private void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(directory + "\\save.db"));
        clinic.setUsers((List<User>) in.readObject());
        for (int i = 0; i < clinic.getUsers().size(); i++) {
            loginCredentials.put(clinic.getUsers().get(i).getUsername(), clinic.getUsers().get(i).getPassword());
        }
        in.close();
    }

    public void start() throws IOException, ClassNotFoundException {

        load();
        if (clinic.getUsers().isEmpty()) {
            addAdmin();
        } else {
            do {
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
                        int selection = userInteraction.mainMenu();
                        isDone = mainMenuHandler(selection);
                    }
                }
            } while (true);
        }
    }

    private void addAdmin() throws IOException, ClassNotFoundException {
        User admin = new User("Administrator", "one", "admin", "1234Password", UserRole.ADMINISTRATIVE);
        clinic.getUsers().add(admin);
        save();
        start();
    }

    private boolean mainMenuHandler(int selection) throws IOException, ClassNotFoundException {

        switch (selection) {
            case 0:
                //view
                int choice0 = userInteraction.viewMenu();
                viewMenuHandler(choice0);
                return false;
            case 1:
                //create
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
                //edit
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
                //delete
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
                //search
                int choice4 = userInteraction.searchMenu();
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


    private void viewMenuHandler(int choice) {
        switch (choice) {
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
                save();
                load();
                break;
            case 1:
                addProvider();
                break;
            case 2:
                //add patient
                break;
            case 3:
                //add appointment
                break;
            case 4:
                //add procedure
                break;
            case 5:
                //exit
                break;

            default:
                break;
        }
    }

    private String checkUniqueUsername() throws IOException {
        String username = null;


        boolean isValid = false;
        while (true) {

            username = userInteraction.getUsername();

            if(loginCredentials.get(username) == null){
                return username;
            }

            userInteraction.println("\nUsername already exist. Please choose another one.");

        }

        //System.out.println(username);
        //return username;
    }

    public void createStandardMenuHandler(int choice) {
        switch (choice) {
            case 0:
                addProvider();
                break;
            case 1:
                //add patient
                break;
            case 2:
                //add appointment
                break;
            case 3:
                //add procedure
                break;
            case 4:
                //exit
                break;

            default:
                break;
        }
    }

    private void addProvider() {


    }

    private void editAdminMenuHandler(int choice) throws IOException {
        switch (choice) {
            case 0:
                currentUser.changePassword(passwordVerified(false));
                save();
                break;
            case 1:
                User who = userInteraction.selectUser(clinic.getUsers(), "Who do you want to change the password");
                String newPassword = passwordVerified(false);
                who.setPassword(newPassword);
                save();
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

    private void editStandardMenuHandler(int choice) throws IOException {
        switch (choice) {
            case 0:
                currentUser.changePassword(passwordVerified(false));
                save();
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
    
    private void editProviderMnu() throws IOException {
    	Provider selectedProvider = userInteraction.selectProvider(clinic.getProviders(), "");
    	
    	
    	
    	
    	
    	
    	
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
}
