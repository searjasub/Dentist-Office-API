package view;


import interfaces.ConsoleUI;
import model.*;

import java.io.IOException;
import java.util.List;

public class UserInteraction {

    public void welcome() {
        ConsoleUI.displayMessage("Welcome to the managment office", false);
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
                throw new IllegalArgumentException("Something went wrong in the UserMenuInteraction Class (You should not see me)");
        }
    }

    public int getProviderID() {
        return 0;
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

    public String getEmail() {
        return null;
    }

    public int getPhoneNumber() {
        return 0;
    }
}
