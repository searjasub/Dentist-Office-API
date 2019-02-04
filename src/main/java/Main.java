import controller.ClinicController;
import view.DentistOfficeUserInteraction;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ClinicController clinicController = new ClinicController();
        clinicController.start();
    }
}
