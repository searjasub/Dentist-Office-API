package controller;

import model.Clinic;
import model.User;
import model.UserRole;
import view.UserInteraction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class App {

    //TODO dentist database with helper methods of list?
    private static final String directory = "savables";
    private User currentUser;
    private UserInteraction userInteraction = new UserInteraction();
    private List<User> userList = new ArrayList<>();

    private Clinic clinic = new Clinic();

    public void start() throws IOException, ClassNotFoundException {
        int selection;

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(directory + "\\save.db"));
        userList = (List<User>) in.readObject();
        in.close();

        if (userList.isEmpty()) {
            addAdmin();
        } else {

            //clinic = deserializeUser();
            do {
                loginScreen();
                boolean isDone = false;
                while (!isDone) {

                    if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                        if (currentUser.getPassword().equals("1234Password")) {
                            userInteraction.print("Welcome to the Dentist Office App, since is your first login please ");

                            String newPass = passwordVerified(true);
                            //currentUser.setPassword(newPass);
                            for (User u : userList) {
                                if (u.getUsername().equals(currentUser.getUsername())) {
                                    u.setPassword(newPass);
                                }
                            }
                            save();
                        }
                        selection = userInteraction.mainMenu(true);
                        isDone = adminMenuHandler(selection);
                    } else {
                        selection = userInteraction.mainMenu(false);
                        isDone = standardMenuHandler(selection);
                    }
                }

            } while (true);
        }
    }

    private void addAdmin() throws IOException, ClassNotFoundException {
        User admin = new User("Administrator", "one", "admin", "1234Password", UserRole.ADMINISTRATIVE);
        userList.add(admin);
        save();
        start();
    }

    private boolean adminMenuHandler(int selection) throws IOException, ClassNotFoundException {

        switch (selection) {
            case 0:
                int choice = userInteraction.manageUsersMenu();
                manageUsers(choice);
                return false;
            case 1:
                //change password
                currentUser.setPassword(passwordVerified(false));
                save();
                return false;
            case 2:
                //search
                return false;
            case 3:
                //reports
                return false;
            case 4:
                userInteraction.println("You have successfully logged out\n\n");
                currentUser = null;
                save();
                return true;
            default:
                break;
        }

        return false;
    }


    private String passwordVerified(boolean isFirstTime) throws IOException {
        String newPassword = null;
        boolean isValid = false;
        while (!isValid) {
            if (isFirstTime) {
                newPassword = userInteraction.changeFirstPassword();
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

    private void manageUsers(int choice) throws IOException {
        switch (choice) {
            case 0:
                addUser();
                break;
            case 1:
                //Remove
                break;
            case 2:
                //Chance password
                break;
            case 3:
                //Change the role
                break;
            case 4:
                //Exit
                break;
            default:
                break;
        }
    }

    private void addUser() throws IOException {

        User user = new User(userInteraction.getName(), userInteraction.getLastName(), userInteraction.getUsername(), userInteraction.getPassword(), userInteraction.getUserType());
        userList.add(user);
        save();
        userInteraction.println("User " + user.getName() + " has been created");
    }

    private void save() throws IOException {
        makeDirIfNotExists();
        FileOutputStream fileOutputStream = new FileOutputStream(directory + "\\save.db");
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(userList);
        out.close();
        out.flush();
        fileOutputStream.close();
    }

    private void makeDirIfNotExists() throws IOException {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

    }

    private boolean standardMenuHandler(int selection) {

        return false;
    }

    private void loginScreen() throws IOException {
        boolean isValid = false;
        while (!isValid) {

            User userTryingToLogin = userInteraction.selectUser(userList, "Choose user to login");
            String username = userInteraction.getLoginUsername();
            String password = userInteraction.getLoginPassword();

            if (username.equals(userTryingToLogin.getUsername()) && password.equals(userTryingToLogin.getPassword()) && userTryingToLogin.getUserRole() == UserRole.ADMINISTRATIVE) {
                currentUser = userTryingToLogin;
                isValid = true;
            } else if (username.equals(userTryingToLogin.getUsername()) && password.equals(userTryingToLogin.getPassword()) && userTryingToLogin.getUserRole() == UserRole.STANDARD) {
                currentUser = userTryingToLogin;
                isValid = true;
            } else {
                userInteraction.println("The credentials are incorrect, please try again.\n");
            }
        }
    }


}
