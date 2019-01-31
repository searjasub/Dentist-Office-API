package view;


import interfaces.ConsoleUI;
import model.User;
import model.UserType;

import java.io.IOException;
import java.util.List;

public class UserInteraction {

    private final String defaultQuestion = "\nWhat would you like to do?";

    public void welcome() {
        ConsoleUI.displayMessage("Welcome to the managment office", false);
    }

    public int mainMenu(boolean isAdmin) throws IOException {
        String[] menuOptions;
        if (isAdmin){
            menuOptions = fillAdminMenu();
        } else {
            menuOptions = fillStandardMenu();
        }
        return ConsoleUI.promptForMenuSelection(menuOptions, defaultQuestion);
    }


    private String[] fillAdminMenu() {
        String[] menuOptions = new String[5];
        menuOptions[0] = "Manage Users";
        menuOptions[1] = "Change password";
        menuOptions[2] = "Search";
        menuOptions[3] = "Reports";
        menuOptions[4] = "Log out";
        return menuOptions;
    }

    private String[] fillStandardMenu() {
        //TODO search providers
        //TODO reports
        return new String[0];
    }

    public int manageUsersMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillManageUsers(), defaultQuestion);
    }

    private String[] fillManageUsers() {
        String[] menuOptions = new String[4];
        menuOptions[0] = "Add";
        menuOptions[1] = "Delete";
        menuOptions[2] = "Change other user's password";
        menuOptions[3] = "Exit";
        return menuOptions;
    }

    private String[] fillSearchMenu(){
        String[] menuOptions = new String[7];
        menuOptions[0] = "By name";
        menuOptions[1] = "By last name";
        menuOptions[2] = "by time interval";
        menuOptions[3] = "by provider";
        menuOptions[4] = "by patient";
        menuOptions[5] = "by procedure core";
        menuOptions[6] = "Exit";
        return menuOptions;
    }

    private String[] fillReportsMenu(){
        String[] menuOptions = new String[3];
        menuOptions[0] = "Production";
        menuOptions[1] = "Patient Balance";
        menuOptions[2] = "Collections";
        return menuOptions;
    }


    public String getName() throws IOException {
        return ConsoleUI.promptForInput("Enter the name: ", false, true);
    }

    public String getLastName() throws IOException {
        return ConsoleUI.promptForInput("Enter the last name: ", false, true);
    }

    public String getUsername() throws IOException {
        return ConsoleUI.promptForInput("Enter the username: ", false, true);
    }

    public String getPassword() throws IOException {
        return ConsoleUI.promptForInput("Enter the password: ", false, true);
    }

    public UserType getUserType() throws IOException{
        String[] menuOptions = new String[2];
        menuOptions[0] = "Administrative";
        menuOptions[1] = "Standard";
        int selection = ConsoleUI.promptForMenuSelection(menuOptions, "What type of user?");
        if(selection == 0){
            return UserType.ADMINISTRATIVE;
        } else {
            return UserType.STANDARD;
        }
    }
    public User selectUser(List<User> users, String message) throws IOException {
        Object[] list = users.toArray();
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = users.get(i).getName() + " " + users.get(i).getLastName();
        }
        return (User) list[ConsoleUI.promptForMenuSelection(options, message)];
    }

    public String changePassword() throws IOException {
        return ConsoleUI.promptForInput("Enter new password", false, false);
    }

    public String changeFirstPassword() throws IOException {
        return ConsoleUI.promptForInput("enter new password\n", false, true);
    }

    public String verifyPassword() throws IOException {
        return ConsoleUI.promptForInput("Re-enter password", false, false);
    }

    public String getLoginUsername() throws IOException {
        ConsoleUI.displayMessage("Username:" , true);
        return ConsoleUI.promptForInput(" ", false, true);
    }

    public String getLoginPassword() throws IOException {
        ConsoleUI.displayMessage("Password:", true);
        return ConsoleUI.promptForInput(" ", false, true);
    }

    public void println(String message){
        System.out.println(message);
    }

    public void print(String message){
        System.out.print(message);
    }

}
