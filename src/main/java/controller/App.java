package controller;

import model.UserType;
import model.User;

public class App {

    public void start() {
        //Create the first user
        User admin = new User("Admin", "istrator", "1234Password", UserType.ADMINISTRATIVE);


    }


}
