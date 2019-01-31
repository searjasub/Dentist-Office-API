package controller;

import model.Person;
import model.User;
import model.UserType;
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

    public void start() throws IOException, ClassNotFoundException {
        int selection;
        if (userList.isEmpty()) {
            addAdmin();
        } else {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(directory + "\\sav.db"));
            ArrayList<User> temp;
            temp =  in.readObject();
            for (User user : temp) {
                String name = user.getName();
                String lastName = user.getLastName();
                String username = user.getUsername();
                String password = user.getPassword();
                UserType type = user.getUserType();
                userList.add(new User(name, lastName, username, password, type));
            }
            in.close();

            loginScreen();
            if (currentUser.getUserType() == UserType.ADMINISTRATIVE) {
                if (currentUser.getPassword().equals("1234Password")) {
                    passwordVerified();
                }
                selection = userInteraction.mainMenu(true);
                adminMenuHandler(selection);
            } else {
                selection = userInteraction.mainMenu(false);
                standardMenuHandler(selection);
            }
        }

    }

    private void addAdmin() throws IOException, ClassNotFoundException {
        User admin = new User("Administrator", "one", "admin", "1234Password", UserType.ADMINISTRATIVE);
        userList.add(admin);
        save();
        start();
    }

    private void adminMenuHandler(int selection) throws IOException, ClassNotFoundException {
        switch (selection) {
            case 0:
                int choice = userInteraction.manageUsersMenu();
                manageUsers(choice);
                break;
            case 1:
                //change password
                passwordVerified();
                break;
            case 2:
                //search
                break;
            case 3:
                //reports
                break;
            case 4:
                userInteraction.println("You have successfully logged out\n\n");
                currentUser = null;
                start();
                break;
        }
    }

    private void passwordVerified() throws IOException {
        boolean isValid = false;
        while (!isValid) {
            String newPassword = userInteraction.changePassword();
            String verification = userInteraction.verifyPassword();
            if (newPassword.equals(verification)) {
                isValid = true;
                currentUser.setPassword(newPassword);
            } else {
                userInteraction.println("The passwords don't match, please try again.");
            }
        }
    }

    private void manageUsers(int choice) throws IOException {
        switch (choice) {
            case 0:
                addUser();
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private void addUser() throws IOException {
        Person person = basicInfo();
        User user = new User(person.getName(), person.getLastName(), userInteraction.getUsername(), userInteraction.getPassword(), userInteraction.getUserType());
        userList.add(user);
        save();
    }

    private void save() throws IOException {
        makeDirIfNotExists();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(directory + "\\save.db"));
        out.writeObject(userList);
        out.close();
    }

    private void makeDirIfNotExists() throws IOException {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            System.out.println("Directory created");
        } else {

            System.out.println("Directory already exists");
        }
    }

    private Person basicInfo() throws IOException {
        String newUserName = userInteraction.getName();
        String newUserLastName = userInteraction.getLastName();
        return new Person(newUserName, newUserLastName);
    }

    private void standardMenuHandler(int selection) {

    }

    private void loginScreen() throws IOException {
        boolean isValid = false;
        while (!isValid) {

            User userTryingToLogin = userInteraction.selectUser(userList, "Choose user to login");
            String username = userInteraction.getLoginUsername();
            String password = userInteraction.getLoginPassword();

            if (username.equals(userTryingToLogin.getUsername()) && password.equals(userTryingToLogin.getPassword()) && userTryingToLogin.getUserType() == UserType.ADMINISTRATIVE) {
                currentUser = userTryingToLogin;
                isValid = true;
            } else if (username.equals(userTryingToLogin.getUsername()) && password.equals(userTryingToLogin.getPassword()) && userTryingToLogin.getUserType() == UserType.STANDARD) {
                currentUser = userTryingToLogin;
                isValid = true;
            } else {
                userInteraction.println("The credentials are incorrect, please try again.\n");
            }
        }
    }


}
