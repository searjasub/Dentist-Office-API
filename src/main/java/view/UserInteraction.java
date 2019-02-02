package view;


import interfaces.ConsoleUI;
import model.ProviderType;
import model.Source;
import model.UserRole;
import java.io.IOException;

public class UserInteraction {

    public void welcome() {
        ConsoleUI.displayMessage("Welcome to the management office", false);
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

    public String getName() throws IOException {
        return ConsoleUI.promptForInput("Enter the name: ", false, true);
    }

    public String getLastName() throws IOException {
        return ConsoleUI.promptForInput("Enter the last name: ", false, true);
    }

    public String getUsername() throws IOException {
        return ConsoleUI.promptForInput("Enter the username: ", false, true);
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
        menuOptions[2] = "Hygienist";
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

    public Source getSource() throws IOException {
        String[] menuOptions = new String[2];
        menuOptions[0] = "Insurance";
        menuOptions[1] = "Patient";
        int selection = ConsoleUI.promptForMenuSelection(menuOptions, "What type of user?");
        if (selection == 0) {
            return Source.INSURANCE;
        } else {
            return Source.PATIENT;
        }
    }

    public int getProviderID() throws IOException {
        int id;
        while (true) {
            id = ConsoleUI.promptForInt("Please enter unique ID number", 0, 99999);
            if (id < 9999) {
                println("The id is too short, please try again. (minimum 5 digits)");
            } else {
                return id;
            }
        }
    }

    public String getEmail() throws IOException {
        String email;
        while (true) {
            email = ConsoleUI.promptForInput("Enter email", false, false);
            for (int i = email.length() - 1; i > 0; i--) {
                if (email.charAt(i) == '@') {
                    for (int j = email.length() - 1; j > i; j--) {
                        if (email.charAt(j) == '.') {
                            return email;
                        }
                    }
                }
            }
            println("The email does not looks like \"example@email.com\"");
        }
    }

    public String getPhoneNumber() throws IOException {
        String phone;
        while (true) {
            phone = ConsoleUI.promptForInput("Please enter phone number", false, false);
            if (phone.length() > 10) {
                println("The phone number is too long, please try again. (maximum 10 digits)");
            } else if (phone.length() < 10) {
                println("The phone number is too short, please try again. (minimum 10 digits)");
            } else if (phone.matches("[a-zA-Z]")) {
                println("Only numbers allowed");
            } else {
                return phone;
            }
        }
    }

    public String getCode() throws IOException {
        String code;

        while (true) {
            code = ConsoleUI.promptForInput("Please enter code\nD", false, true);
            if (code.length() > 5) {
                println("The code is too long, please try again. (maximum 5 digits)");
            } else if (code.length() < 5) {
                println("The code is too short, please try again. (minimum 5 digits)");
            } else if (code.matches("[a-zA-Z]+")) {
                println("Only numbers allowed");
            } else {
                return code;
            }
        }
    }

    public String getDescription() throws IOException {
        return ConsoleUI.promptForInput("Description", false, false);
    }

    public double getCost() throws IOException {
        return ConsoleUI.promptForDouble("How much did it cost?", 0, Double.MAX_VALUE);
    }

    public String getInsuranceName() throws IOException {
        return ConsoleUI.promptForInput("Enter Insurance Name", false, false);
    }

    public String getGroupId() throws IOException {
        return ConsoleUI.promptForInput("Enter GroupID", false, false);
    }

    public String getMemberId() throws IOException {
        return ConsoleUI.promptForInput("Enter MemberID", false, false);
    }

    public String getCardNumber() throws IOException {
        return ConsoleUI.promptForInput("Please Enter A New Card Number", false, false);
    }

    public int getExpMonth() throws IOException {
        return ConsoleUI.promptForInt("Please Enter An Expiration Month", 1, 12);
    }

    public int getExpYear() throws IOException {
        return ConsoleUI.promptForInt("Please Enter An Expiration Year", 2019, 2100);
    }

    public String getCardName() throws IOException {
        return ConsoleUI.promptForInput("Please Enter The Name On The Car", false, false);
    }

    public int getCvv() throws IOException {
        return ConsoleUI.promptForInt("Please Enter a New CVV Number", 0, 999);
    }

    public int getZipCode() throws IOException {
        return ConsoleUI.promptForInt("Please Enter A New Zip Code", 10000, 99999);
    }
}
