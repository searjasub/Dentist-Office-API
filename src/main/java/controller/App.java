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

            userList = deserializeUser();

            for (User user : userList) {
                if (!user.getUsername().equals("admin")) {
                    String name = user.getName();
                    String lastName = user.getLastName();
                    String username = user.getUsername();
                    String password = user.getPassword();
                    UserType type = user.getUserType();
                    userList.add(new User(name, lastName, username, password, type));
                }
            }

            loginScreen();
            if (currentUser.getUserType() == UserType.ADMINISTRATIVE) {
                if (currentUser.getPassword().equals("1234Password")) {
                    userInteraction.print("Welcome to the Dentist Office App, since is your first login please ");

                    String newPass = passwordVerified(true);

                    //currentUser.setPassword(newPass);
                    for (User u :
                            userList) {
                        if(u.getUsername().equals(currentUser.getUsername())){
                            u.setPassword(newPass);
                        }
                    }
                    save();
                }
                selection = userInteraction.mainMenu(true);
                adminMenuHandler(selection);
            } else {
                selection = userInteraction.mainMenu(false);
                standardMenuHandler(selection);
            }
        }

    }

    private List<User> deserializeUser() throws IOException, ClassNotFoundException {
        List<User> users;
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(directory + "\\save.db"));
        users = (List<User>) in.readObject();
        in.close();
        return users;
    }

    private void addAdmin() throws IOException, ClassNotFoundException {
        User admin = new User("Administrator", "one", "admin", "1234Password", UserType.ADMINISTRATIVE);
        userList.add(admin);
        save();
        start();
    }

    private void adminMenuHandler(int selection) throws IOException, ClassNotFoundException {

        boolean wantToBeOut = false;
        while (!wantToBeOut) {
            switch (selection) {
                case 0:
                    int choice = userInteraction.manageUsersMenu();
                    manageUsers(choice);
                    break;
                case 1:
                    //change password
                    currentUser.setPassword(passwordVerified(false));
                    save();
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
                    wantToBeOut = true;
                    break;
            }
        }
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
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }
    }

    private void addUser() throws IOException {
        Person person = basicInfo();
        User user = new User(person.getName(), person.getLastName(), userInteraction.getUsername(), userInteraction.getPassword(), userInteraction.getUserType());
        userList.add(user);
        save();
        userInteraction.println("User " + person.getName() + " has been created");
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
