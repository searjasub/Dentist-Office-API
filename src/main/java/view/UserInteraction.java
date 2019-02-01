package view;


import interfaces.ConsoleUI;
import model.ProviderType;
import model.User;
import model.UserRole;

import java.io.IOException;
import java.util.List;

public class UserInteraction {

    private final String defaultQuestion = "\nWhat would you like to do?";

    public void welcome() {
        ConsoleUI.displayMessage("Welcome to the managment office", false);
    }


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

    public int createMenu() throws IOException {
        return ConsoleUI.promptForMenuSelection(fillCreateMenu(), defaultQuestion);
    }

    private String[] fillCreateMenu() {
        String[] menuOptions = new String[5];
        menuOptions[0] = "Add Provider";
        menuOptions[1] = "Add Patient";
        menuOptions[2] = "Add Appointment";
        menuOptions[3] = "Add Procedure";
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

    public String[] fillDeleteStandardMenu() {
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
        String[] menuOptions = new String[3];
        menuOptions[0] = "Dentist";
        menuOptions[1] = "Assistant";
        menuOptions[2] = "Hygenist";
        int selection = ConsoleUI.promptForMenuSelection(menuOptions, "Who is the provider?");
        switch (selection) {
            case 0:
                return ProviderType.DENTIST;
            case 1:
                return ProviderType.ASSISTANT;
            case 2:
                return ProviderType.HYGIENIST;
            default:
                throw new IllegalArgumentException("Something went wrong in the UserInteraction Class (You should not see me)");
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

}
