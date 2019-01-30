package view;

import interfaces.ConsoleUI;
import model.User;

import java.io.IOException;
import java.util.List;

public class UserInteraction {

    public void welcome(){
        ConsoleUI.displayMessage("Welcome to the managment office");
    }

//    public int adminMenu(){
//
//        return ConsoleUI.promptForMenuSelection();
//    }

    private String[] fillAdminMenu(){
        String[] menuOptions = new String[5];
        menuOptions[0] = "Manage Users";
        menuOptions[1] = "";
        menuOptions[2] = "Search";
        menuOptions[3] = "Save";
        menuOptions[4] = "Load";
        return menuOptions;
    }






    private String[] fillManageUsers(){
        String[] menuOptions = new String[4];
        menuOptions[0] = "Add";
        menuOptions[1] = "Delete";
        menuOptions[2] = "Change other user's password";
        menuOptions[3] = "Exit";
        return menuOptions;
    }

    private User selectUser(List<User> users) throws IOException{
        Object[] list = users.toArray();
        String[] options = new String[list.length];
        for (int i = 0; i < options.length; i++) {
            options[i] = users.get(i).getName() + " " + users.get(i).getLastName();
        }
        return (User)list[ConsoleUI.promptForMenuSelection(options,"Select which person you want to delete")];
    }

    public String changePassword() throws IOException {
        return ConsoleUI.promptForInput("Enter new password", false);
    }

    public String verifyPassword() throws IOException {
        return ConsoleUI.promptForInput("Re-enter password", false);
    }

}
