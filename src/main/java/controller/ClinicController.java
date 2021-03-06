package controller;

import model.*;
import view.DentistOfficeUserInteraction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

/**
 * @author Searjasub Lopez
 * @author Daniel Baydak
 */

/**
 * Controller of the API
 * <p>
 * The Clinic controller represents a Dentist Office Management App.
 */
public class ClinicController {

    private static final String directory = "savables";
    private final String start = "Enter the time range.\nFrom:";
    private final String end = "\nUntil:";
    private Clinic clinic = new Clinic();
    private User currentUser;
    private DentistOfficeUserInteraction userInteraction;
    private HashMap<String, String> loginCredentials = new HashMap<>();
    private Map<Month, Double> whenWasPaidByMonth = new HashMap<>();
    private Map<Integer, Double> whenWasPaidByDay = new HashMap<>();

    /**
     * @param ui instance of the user interaction calling project
     */
    public ClinicController(DentistOfficeUserInteraction ui) {
        this.userInteraction = ui;
    }

    /**
     * Makes sure there is a directory. If not it will create one with an admin.
     * It enters in a infinite loop to run the app until you close it.
     * The information persist and is automatically saved and load to ensure you are always working wth the current information and not having to restart the app.
     * Prompts the main menu and everything else depends on that main menu
     */
    public void start() throws IOException, ClassNotFoundException {
        if (makeDirectory()) {
            save();
        }
        load();
        if (clinic.getUsers().isEmpty()) {
            addAdmin();
        } else {
            do {
                userInteraction.welcome();
                loginScreen();
                boolean isDone = false;
                while (!isDone) {
                    if (currentUser.isFirstTimePassChange()) {
                        userInteraction.print("Welcome to the Dentist Office, since is your first login please ");
                        String newPass = passwordVerified(true);
                        for (User u : clinic.getUsers()) {
                            if (u.getUsername().equals(currentUser.getUsername())) {
                                u.setPassword(newPass);
                                u.setFirstTimePassChange(false);
                            }
                        }
                        save();

                        int selection = userInteraction.mainMenu();
                        isDone = mainMenuHandler(selection);
                    } else {
                        int selection = userInteraction.mainMenu();
                        isDone = mainMenuHandler(selection);

                    }
                }
            } while (true);
        }
    }

    private void addAdmin() throws IOException, ClassNotFoundException {
        User admin = new User("Administrator", "one", "Administrator", "1234Password", UserRole.ADMINISTRATIVE, true);
        clinic.getUsers().add(admin);
        autoSaveLoad();
        start();
    }

    private boolean mainMenuHandler(int selection) throws IOException, ClassNotFoundException {
        switch (selection) {
            case 0:
                //VIEW
                int choice0 = userInteraction.viewMenu();
                viewMenuHandler(choice0);
                return false;
            case 1:
                //CREATE
                int choice1;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice1 = userInteraction.createAdminMenu();
                    createAdminMenuHandler(choice1);
                } else {
                    choice1 = userInteraction.createStandardMenu();
                    createStandardMenuHandler(choice1);
                }
                return false;
            case 2:
                //EDIT
                int choice2;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice2 = userInteraction.editAdminMenu();
                    editAdminMenuHandler(choice2);
                } else {
                    choice2 = userInteraction.editStandardMenu();
                    editStandardMenuHandler(choice2);
                }
                return false;
            case 3:
                //DELETE
                int choice3;
                if (currentUser.getUserRole() == UserRole.ADMINISTRATIVE) {
                    choice3 = userInteraction.deleteAdminMenu();
                    deleteAdminMenuHandler(choice3);
                } else {
                    choice3 = userInteraction.deleteStandardMenu();
                    deleteStandardMenuHandler(choice3);
                }
                return false;
            case 4:
                //SEARCH
                int choice4 = userInteraction.searchMenu();
                searchMenuHandler(choice4);
                return false;
            case 5:
                //LOG OUT
                userInteraction.println("You have successfully logged out\n\n");
                currentUser = null;
                save();
                return true;
            default:
                break;
        }
        return false;
    }

    private LocalDate getStart() throws IOException {
        userInteraction.print(start);
        return userInteraction.getLocalDate();

    }

    private LocalDate getEnd() throws IOException {
        userInteraction.print(end);
        return userInteraction.getLocalDate();
    }

    private void viewMenuHandler(int selection) throws IOException, ClassNotFoundException {
        switch (selection) {
            case 0:

                LocalDate start = getStart();
                LocalDate end = getEnd();
                double jan = 0, feb = 0, mar = 0, apr = 0, may = 0, jun = 0, jul = 0, aug = 0, sept = 0, oct = 0, nov = 0, dec = 0;

                int monthOrDay = userInteraction.selectMonthOrDay();
                if (monthOrDay == 1) {
                    for (int i = 0; i < clinic.getPastAppointments().size(); i++) {
                        LocalDate tmp = LocalDate.of(
                                clinic.getPastAppointments().get(i).getDateTime().getYear(),
                                clinic.getPastAppointments().get(i).getDateTime().getMonth(),
                                clinic.getPastAppointments().get(i).getDateTime().getDayOfMonth());

                        if (tmp.isAfter(start) && tmp.isBefore(end)) {
                            if (tmp.getMonth() == Month.JANUARY) {
                                jan += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.FEBRUARY) {
                                feb += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.MARCH) {
                                mar += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.APRIL) {
                                apr += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.MAY) {
                                may += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.JUNE) {
                                jun += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.JULY) {
                                jul += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.AUGUST) {
                                aug += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.SEPTEMBER) {
                                sept += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.OCTOBER) {
                                oct += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.NOVEMBER) {
                                nov += whenWasPaidByMonth.get(tmp.getMonth());
                            } else if (tmp.getMonth() == Month.DECEMBER) {
                                dec += whenWasPaidByMonth.get(tmp.getMonth());
                            }
                        }
                    }
                    if (jan != 0) {
                        userInteraction.println("January: $" + jan);
                    }
                    if (feb != 0) {
                        userInteraction.println("February: $" + feb);
                    }
                    if (mar != 0) {
                        userInteraction.println("March: $" + mar);
                    }
                    if (apr != 0) {
                        userInteraction.println("April: $" + apr);
                    }
                    if (may != 0) {
                        userInteraction.println("March: $" + may);
                    }
                    if (jun != 0) {
                        userInteraction.println("March: $" + jun);
                    }
                    if (jul != 0) {
                        userInteraction.println("March: $" + jul);
                    }
                    if (aug != 0) {
                        userInteraction.println("March: $" + aug);
                    }
                    if (sept != 0) {
                        userInteraction.println("March: $" + sept);
                    }
                    if (oct != 0) {
                        userInteraction.println("March: $" + oct);
                    }
                    if (nov != 0) {
                        userInteraction.println("March: $" + nov);
                    }
                    if (dec != 0) {
                        userInteraction.println("December: $" + dec);
                    }
                } else {
                    //full date month and day
                    //testing will add two appointment same day

                    LocalDate start1 = getStart();
                    LocalDate end1 = getEnd();
                    int amountByDay = 0;



                    for (int i = 1; i < clinic.getPastAppointments().size(); i++) {
//                        LocalDate tmp = LocalDate.of(
//                                clinic.getPastAppointments().get(i).getDateTime().getYear(),
//                                clinic.getPastAppointments().get(i).getDateTime().getMonth(),
//                                clinic.getPastAppointments().get(i).getDateTime().getDayOfMonth());


                        while (start1.isEqual(end1)) {

                            if (start1.getDayOfMonth() == i) {
                                amountByDay += whenWasPaidByDay.get(i);
                            }

                            start1 = start1.plusDays(1);
                            i++;

                            if (i == 31) {
                                i = 1;
                            }
                        }

                    }

                    if (amountByDay != 0) {

                    }


                }
                break;
            case 1:
                //PATIENT BALANCE
                int selection3 = userInteraction.gruopBy();
                switch (selection3) {
                    case 0://By largest balance
                        double[] balanceList = new double[clinic.getPatients().size()];
                        String[] printList = new String[clinic.getPatients().size()];
                        for (int i = 0; i < clinic.getPatients().size(); i++) {
                            balanceList[i] = clinic.getPatients().get(i).getBalance();
                        }
                        Arrays.sort(balanceList);
                        for (int x = 0; x < balanceList.length; x++) {
                            for (int y = 0; y < balanceList.length; y++) {
                                if (balanceList[x] == clinic.getPatients().get(y).getBalance()) {
                                    printList[x] = clinic.getPatients().get(y).getLastName() + " $" + balanceList[x];
                                }
                            }
                        }
                        for (int q = 0; q < printList.length; q++) {
                            System.out.println(printList[q]);
                        }
                        break;
                    case 1: // by last name, first name

                        String[] printList2 = new String[clinic.getPatients().size()];
                        String[] lastNameList = new String[clinic.getPatients().size()];
                        for (int i = 0; i < clinic.getPatients().size(); i++) {
                            lastNameList[i] = clinic.getPatients().get(i).getLastName();
                        }
                        Arrays.sort(lastNameList);
                        for (int x = 0; x < lastNameList.length; x++) {
                            for (int y = 0; y < lastNameList.length; y++) {
                                if (lastNameList[x] == clinic.getPatients().get(y).getLastName()) {
                                    printList2[x] = lastNameList[x] + " $" + clinic.getPatients().get(y).getBalance();
                                }
                            }
                        }
                        for (int q = 0; q < printList2.length; q++) {
                            System.out.println(printList2[q]);
                        }
                        break;
                    case 2:
                        break;
                }
                break;
            case 2:
                //todo

                LocalDate start2 = getStart();
                LocalDate end2 = getEnd();

                double jan1 = 0, feb1 = 0, mar1 = 0, apr1 = 0, may1 = 0, jun1 = 0, jul1 = 0, aug1 = 0, sept1 = 0, oct1 = 0, nov1 = 0, dec1 = 0;

                double totalAmount = 0;
                for (int i = 0; i < clinic.getPastAppointments().size(); i++) {
                    LocalDate tmp = LocalDate.of(
                            clinic.getPastAppointments().get(i).getDateTime().getYear(),
                            clinic.getPastAppointments().get(i).getDateTime().getMonth(),
                            clinic.getPastAppointments().get(i).getDateTime().getDayOfMonth());

                    if (tmp.isAfter(start2) && tmp.isBefore(end2)) {
                        if (clinic.getPastAppointments().get(i).isCompleted()) {

                                totalAmount += clinic.getPastAppointments().get(i).getPayment().getAmount();

                        }
                    }
                }








                userInteraction.print("The total amount collected by the clinic from: " + start2.toString() + " until: " + end2.toString() + " is: " + totalAmount + "\n");
                break;
            case 3:
                //APPOINTMENTS
                int selection2 = userInteraction.appointmentsMenu();
                switch (selection2) {
                    case 0://SHOW PAST APPOINTMENT
                        for (int i = 0; i < clinic.getPastAppointments().size(); i++) {
                            if (clinic.getPastAppointments().get(i) != null) {
                                userInteraction.println(userInteraction.removeCharacters("Patient: " + clinic.getPastAppointments().get(i).getPatient().getName() + " " + clinic.getPastAppointments().get(i).getPatient().getLastName() + " | Time: " + clinic.getPastAppointments().get(i).getDateTime().getMonth() + "/" + clinic.getPastAppointments().get(i).getDateTime().getDayOfMonth() + " " + clinic.getPastAppointments().get(i).getDateTime().getHour() + ":" + clinic.getPastAppointments().get(i).getDateTime().getMinute() + "\n\t\tProcedures performed" + clinic.getPastAppointments().get(i).toString() + "\n"));
                            }
                        }
                        break;
                    case 1://MARK APPOINTMENTS AS COMPLETED
                        try {
                            FutureAppointment tmp = userInteraction.selectFutureAppointment(clinic.getFutureAppointments(), "Select an appointment to mark as complete");

                            if (userInteraction.isCompleted("Would you like to mark this appointment as complete")) {
                                tmp.setCompleted(true);
                                Appointment appointment = new Appointment(
                                        tmp.getPatient(),
                                        tmp.getDateTime(),
                                        tmp.isCompleted());


                                Payment payment = new Payment(
                                        userInteraction.chargeAmount(),
                                        appointment.getPatient(),
                                        userInteraction.getSource());
                                AppointmentRecord ap = new AppointmentRecord(
                                        appointment.getPatient(),
                                        appointment.getDateTime(),
                                        appointment.isCompleted(),
                                        getProcedureRecords(appointment.getPatient(), tmp),
                                        payment);

                                double amountTotal = 0;
                                for (int i = 0; i < ap.getProcedures().size(); i++) {
                                    amountTotal += ap.getProcedures().get(i).getCost();
                                }
                                whenWasPaidByMonth.put(appointment.getDateTime().getMonth(), amountTotal);

                                clinic.receivePayment(payment);
                                appointment.getPatient().setBalance(-payment.getAmount());
                                clinic.getPastAppointments().add(ap);
                                clinic.getFutureAppointments().remove(tmp);
                                autoSaveLoad();
                            }
                        } catch (NotFoundException ex) {
                            userInteraction.println(ex.getObject());
                        }
                        break;
                    case 2://Exit
                        break;
                    default:
                        break;
                }
                break;
            case 4://Exit
                break;
            default:
                break;
        }
    }

    private List<ProcedureRecord> getProcedureRecords(Patient patient, FutureAppointment fa) {
        List<ProcedureRecord> list = new ArrayList<>();
        ProcedureRecord procedureRecord;
        for (int i = 0; i < fa.getProcedures().size(); i++) {
            procedureRecord = new ProcedureRecord(
                    patient,
                    fa.getProcedures().get(i),
                    fa.getProcedures().get(i).getCost());
            list.add(procedureRecord);
        }
        return list;
    }


    private void createAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                User newUser = new User(
                        userInteraction.getName(false),
                        userInteraction.getLastName(false),
                        checkUniqueUsername(),
                        passwordVerified(false),
                        userInteraction.getUserType(),
                        true);
                clinic.getUsers().add(newUser);
                autoSaveLoad();
                break;
            case 1:
                addProvider();
                break;
            case 2:
                addPatient();
                break;
            case 3:
                addAppointment();
                break;
            case 4:
                addProcedure();
                break;
            case 5:
                //EXIT
                break;
            default:
                break;
        }
    }

    private String checkUniqueUsername() throws IOException {
        String username;
        while (true) {
            username = userInteraction.getUsername(false);
            if (loginCredentials.get(username) == null) {
                return username;
            }
            userInteraction.println("\nUsername already exist. Please choose another different Username.");
        }
    }

    private void createStandardMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                addProvider();
                break;
            case 1:
                addPatient();
                break;
            case 2:
                addAppointment();
                autoSaveLoad();
                break;
            case 3:
                addProcedure();
                break;
            case 4:
                //EXIT
                break;
            default:
                break;
        }
    }

    private void addProvider() throws IOException, ClassNotFoundException {
        clinic.getProviders().add(new Provider(
                userInteraction.getName(false),
                userInteraction.getLastName(false),
                userInteraction.getUniqueID(),
                userInteraction.getEmail(),
                userInteraction.getPhoneNumber(),
                userInteraction.getProviderType()));
        autoSaveLoad();
    }

    private void addPatient() throws IOException, ClassNotFoundException {
        clinic.getPatients().add(new Patient(
                userInteraction.getName(false),
                userInteraction.getLastName(false),
                userInteraction.getUniqueID(),
                userInteraction.getEmail(),
                userInteraction.getPhoneNumber(),
                getInsuranceInfo(),
                new PaymentCard(
                        userInteraction.getCardNumber(),
                        userInteraction.getExpMonth(),
                        userInteraction.getExpYear(),
                        userInteraction.getCardName(),
                        userInteraction.getCvv(),
                        userInteraction.getZipCode())));


        autoSaveLoad();
    }

    private Insurance getInsuranceInfo() throws IOException {
        Insurance insurance = new Insurance(
                userInteraction.getInsuranceName(),
                userInteraction.getGroupId(),
                userInteraction.getMemberId());
        clinic.getInsurances().add(insurance);
        return insurance;
    }

    private void addAppointment() throws IOException, ClassNotFoundException {
        try {
            FutureAppointment fa = new FutureAppointment(
                    userInteraction.selectPatient(clinic.getPatients(), "Select Patient", false),
                    userInteraction.getFutureDate(false),
                    false,
                    addProcedures());
            clinic.getFutureAppointments().add(fa);
            autoSaveLoad();
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }

    }

    private List<Procedure> addProcedures() throws IOException {
        List<Procedure> tmp = new ArrayList<>();
        int count = userInteraction.getHowManyProcedures();
        for (int i = 0; i < count; i++) {
            Procedure procedure = userInteraction.selectProcedure(clinic.getProcedures(), "Select a procedure to add");
            tmp.add(procedure);
        }
        return tmp;
    }

    private void addProcedure() throws IOException, ClassNotFoundException {
        try {
            Procedure procedure = new Procedure(
                    userInteraction.getCode(),
                    userInteraction.getDescription(),
                    userInteraction.getCost(),
                    userInteraction.selectProvider(clinic.getProviders(), "Choose a provider", false));
            clinic.getProcedures().add(procedure);
            autoSaveLoad();
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0://CHANGE OWN PASSWORD
                editUserMenu_standardView();
                break;
            case 1://EDIT USERS
                editUserMenu_adminView();
                break;
            case 2: //EDIT PROVIDER
                editProviderMenu();
                break;
            case 3://EDIT PATIENTS
                editPatientMenu();
                break;
            case 4://EDIT APPOINTMENTS
                editAppointmentsMenu();
                break;
            case 5://EDIT PROCEDURES
                editProcedure();
                break;
            case 6:
                userInteraction.println("You have exited the Edit Menu\n");
                //exit
                break;
            default:
                break;
        }
    }

    private void editUserMenu_adminView() throws IOException, ClassNotFoundException {
        User user = userInteraction.selectUser(clinic.getUsers(), "Select which user's information you would like to edit");
        int selection = userInteraction.changeUserInformationMenu();
        switch (selection) {
            case 0:
                editName(user);
                break;
            case 1:
                editLastName(user);
                break;
            case 2:
                editUsername(user);
                break;
            case 3:
                editPassword(user);
                break;
            case 4:
                UserRole role = userInteraction.selectRole();
                user.setUserRole(role);
                autoSaveLoad();
            case 5:
                break;
        }
    }

    private void editStandardMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                editUserMenu_standardView();
                break;
            case 1:
                editProviderMenu();
                break;
            case 2:
                editPatientMenu();
                break;
            case 3:
                editAppointmentsMenu();
                break;
            case 4:
                editProcedure();
                break;
            case 5:
                userInteraction.println("You have exited the Edit Menu\n");
                break;
            default:
                break;
        }
    }

    private void editUserMenu_standardView() throws IOException, ClassNotFoundException {
        int selection = userInteraction.changeUserOwnInformation();
        switch (selection) {
            case 0:
                editName(currentUser);
                break;
            case 1:
                editLastName(currentUser);
                break;
            case 2:
                editUsername(currentUser);
                break;
            case 3:
                editPassword(currentUser);
                break;
            case 4:
                break;
        }
    }

    private void editUsername(User user) throws IOException, ClassNotFoundException {
        String newUsername = userInteraction.getUsername(false);
        user.setUsername(newUsername);
        autoSaveLoad();
    }

    private void editPassword(User user) throws IOException, ClassNotFoundException {
        String newPassword = passwordVerified(false);
        user.setPassword(newPassword);
        autoSaveLoad();
    }

    private void editProviderMenu() throws IOException, ClassNotFoundException {
        try {
            Provider provider = userInteraction.selectProvider(clinic.getProviders(), "Which Provider Would you like to Change?", false);
            int selection = userInteraction.changeProviderInformationMenu();
            switch (selection) {
                case 0:
                    editName(provider);
                    break;
                case 1:
                    editLastName(provider);
                case 2:
                    editPhoneNumber(provider);
                case 3:
                    editEmail(provider);
                case 4:
                    ProviderType type = provider.getTitle();
                    provider.setTitle(type);
                    autoSaveLoad();
                case 5:
                    userInteraction.println("\nReturning to Main Menu...\n");
                default:
                    break;
            }
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editPatientMenu() throws IOException, ClassNotFoundException {
        try {
            Patient patient = userInteraction.selectPatient(clinic.getPatients(), "Choose A Patient to Edit", false);
            int selection = userInteraction.changePatientInformationMenu();
            switch (selection) {
                case 0:
                    editName(patient);
                    break;
                case 1:
                    editLastName(patient);
                    break;
                case 2:
                    editEmail(patient);
                    break;
                case 3:
                    editPhoneNumber(patient);
                    break;
                case 4:
                    Insurance insurance = new Insurance(
                            userInteraction.getInsuranceName(),
                            userInteraction.getGroupId(),
                            userInteraction.getMemberId());
                    patient.setInsurance(insurance);
                    autoSaveLoad();
                    break;
                case 5:
                    PaymentCard card = new PaymentCard(
                            userInteraction.getCardNumber(),
                            userInteraction.getExpMonth(),
                            userInteraction.getExpYear(),
                            userInteraction.getCardName(),
                            userInteraction.getCvv(),
                            userInteraction.getZipCode());
                    patient.setPaymentCard(card);
                    autoSaveLoad();
                    break;
                case 6:
                    userInteraction.println("\nReturning to Main Menu...\n");
                    break;
                default:
                    break;
            }
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editName(Object object) throws IOException, ClassNotFoundException {
        String firstName = userInteraction.getName(false);
        if (object instanceof Provider) {
            ((Provider) object).setName(firstName);
        } else if (object instanceof Patient) {
            ((Patient) object).setName(firstName);
        } else if (object instanceof User) {
            ((User) object).setName(firstName);
        }
        autoSaveLoad();

    }

    private void editLastName(Object object) throws IOException, ClassNotFoundException {
        String lastName = userInteraction.getLastName(false);
        if (object instanceof Provider) {
            ((Provider) object).setLastName(lastName);
        } else if (object instanceof Patient) {
            ((Patient) object).setLastName(lastName);
        } else if (object instanceof User) {
            ((User) object).setLastName(lastName);
        }
        autoSaveLoad();
    }

    private void editPhoneNumber(Object object) throws IOException, ClassNotFoundException {
        String phoneNumber = userInteraction.getPhoneNumber();
        if (object instanceof Provider) {
            ((Provider) object).setPhoneNumber(phoneNumber);
        } else if (object instanceof Patient) {
            ((Patient) object).setPhoneNumber(phoneNumber);
        }
        autoSaveLoad();
    }

    private void editEmail(Object object) throws IOException, ClassNotFoundException {
        String email = userInteraction.getEmail();
        if (object instanceof Provider) {
            ((Provider) object).setEmail(email);
        } else if (object instanceof Patient) {
            ((Patient) object).setEmail(email);
        }
        autoSaveLoad();
    }

    private void editAppointmentsMenu() throws IOException, ClassNotFoundException {
        try {
            FutureAppointment appointmentSelected = userInteraction.selectFutureAppointment(clinic.getFutureAppointments(), "Select an appointment");
            int selection = userInteraction.changeFutureAppointmentMenu();
            switch (selection) {
                case 0:
                    Patient selected = userInteraction.selectPatient(clinic.getPatients(), "Select new a new patient", false);
                    appointmentSelected.setPatient(selected);
                    autoSaveLoad();
                    break;
                case 1:
                    int choice = userInteraction.changeTimeMenu();
                    switch (choice) {
                        case 0:
                            LocalDateTime newTime = LocalDateTime.of(
                                    appointmentSelected.getDateTime().getYear(),
                                    appointmentSelected.getDateTime().getMonth(),
                                    appointmentSelected.getDateTime().getDayOfMonth(),
                                    userInteraction.getHour(),
                                    userInteraction.getMinute());
                            appointmentSelected.setDateTime(newTime);
                            autoSaveLoad();
                            break;
                        case 1:
                            LocalDateTime newDay = LocalDateTime.of(
                                    userInteraction.getYear(),
                                    userInteraction.getMonth(),
                                    userInteraction.getDay(),
                                    appointmentSelected.getDateTime().getHour(),
                                    appointmentSelected.getDateTime().getMinute());
                            appointmentSelected.setDateTime(newDay);
                            autoSaveLoad();
                            break;
                        case 2:
                            LocalDateTime localDateTime = LocalDateTime.of(
                                    userInteraction.getYear(),
                                    userInteraction.getMonth(),
                                    userInteraction.getDay(),
                                    userInteraction.getHour(),
                                    userInteraction.getMinute());
                            appointmentSelected.setDateTime(localDateTime);
                            autoSaveLoad();
                            break;
                        case 3:
                            break;
                    }
                    break;
                case 2:
                    int option = userInteraction.editAppointmentProviderMenu();
                    switch (option) {
                        case 0:
                            Procedure procedure = userInteraction.selectProcedure(clinic.getProcedures(), "Select Procedure to add");
                            appointmentSelected.getProcedures().add(procedure);
                            autoSaveLoad();
                            break;
                        case 1:
                            Procedure procedureToRemove = userInteraction.selectProcedure(clinic.getProcedures(), "Selece Procedure to delete");
                            appointmentSelected.getProcedures().remove(procedureToRemove);
                            autoSaveLoad();
                            break;
                        case 2:
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    break;
            }
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void editProcedure() throws IOException, ClassNotFoundException {
        try {
            Procedure procedure = userInteraction.selectProcedure(clinic.getProcedures(), "Select a procedure");
            int selection = userInteraction.changeProcedureInformation();
            switch (selection) {
                case 0:
                    //CODE
                    String code = userInteraction.getCode();
                    procedure.setCode(code);
                    autoSaveLoad();
                    break;
                case 2:
                    //DESCRIPTION
                    String description = userInteraction.getDescription();
                    procedure.setDescription(description);
                    autoSaveLoad();
                    break;
                case 3:
                    //COST
                    double cost = userInteraction.getCost();
                    procedure.setCost(cost);
                    autoSaveLoad();
                    break;
                case 4:
                    //PROVIDER
                    Provider provider = userInteraction.selectProvider(clinic.getProviders(), "Select a provider", false);
                    procedure.setProvider(provider);
                    autoSaveLoad();
                    break;
                case 5:
                    //EXIT
                    break;
            }
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void deleteAdminMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                User user = userInteraction.selectUser(clinic.getUsers(), "Select an User to delete");
                for (int i = 0; i < clinic.getUsers().size(); i++) {
                    if (user.getUsername().equals(clinic.getUsers().get(i).getUsername())) {
                        clinic.getUsers().remove(user);
                    }
                }
                autoSaveLoad();
                break;
            case 1:
                clinic.getUsers().clear();
                clinic.getUsers().add(currentUser);
                break;
            case 2:
                deleteProvider();
                break;
            case 3:
                deletePatient();
                break;
            case 4:
                deleteAppointment();
                break;
            case 5:
                deleteProcedure();
                break;
            case 6:
                break;
            default:
                break;
        }
    }

    private void deleteStandardMenuHandler(int choice) throws IOException, ClassNotFoundException {
        switch (choice) {
            case 0:
                deleteProvider();
                break;
            case 1:
                deletePatient();
                break;
            case 2:
                deleteAppointment();
                break;
            case 3:
                deleteProcedure();
                break;
            case 4:
                userInteraction.println("Returning to previous menu...\n");
                break;
            default:
                break;
        }
    }

    private void deleteProvider() throws IOException, ClassNotFoundException {
        Provider provider = userInteraction.selectProvider(clinic.getProviders(), "Select a Provider to delete", false);
        for (int i = 0; i < clinic.getProviders().size(); i++) {
            if (provider.getUniqueId() == clinic.getProviders().get(i).getUniqueId()) {
                clinic.getProviders().remove(provider);
            }
        }
        autoSaveLoad();
    }

    private void deletePatient() throws IOException, ClassNotFoundException {
        Patient patient = userInteraction.selectPatient(clinic.getPatients(), "Select a Patient to delete", false);
        for (int i = 0; i < clinic.getPatients().size(); i++) {
            if (patient.getUniqueId() == clinic.getPatients().get(i).getUniqueId()) {
                clinic.getPatients().remove(patient);
            }
        }
        autoSaveLoad();
    }

    private void deleteAppointment() throws IOException, ClassNotFoundException {
        try {
            FutureAppointment appointment = userInteraction.selectFutureAppointment(clinic.getFutureAppointments(), "Select appointment to delete");
            for (int i = 0; i < clinic.getFutureAppointments().size(); i++) {
                if (appointment.getPatient() == clinic.getFutureAppointments().get(i).getPatient() &&
                        appointment.getDateTime() == clinic.getFutureAppointments().get(i).getDateTime()) {
                    clinic.getFutureAppointments().remove(appointment);
                }
            }
            autoSaveLoad();
        } catch (NotFoundException ex) {
            userInteraction.println(ex.getObject());
        }
    }

    private void deleteProcedure() throws IOException, ClassNotFoundException {
        Procedure procedure = userInteraction.selectProcedure(clinic.getProcedures(), "Select a Procedure to delete");
        for (int i = 0; i < clinic.getProcedures().size(); i++) {
            if (procedure.getCode().equals(clinic.getProcedures().get(i).getCode())) {
                clinic.getProcedures().remove(procedure);
            }
        }
        autoSaveLoad();
    }

    private void searchMenuHandler(int choice) throws IOException {
        String defaultMessage = "Instructions: If you leave any field blank it will not be consider for the search. If you leave all of them it will search everything";
        switch (choice) {
            case 0://SEARCH PROVIDER
                List<Provider> providerList = new ArrayList<>();
                userInteraction.println(defaultMessage);
                String providerName = userInteraction.getName(true);
                String providerLastName = userInteraction.getLastName(true);
                ProviderType titleSelected = userInteraction.getProviderTypeWithEmptyEntry();

                for (int i = 0; i < clinic.getProviders().size(); i++) {
                    if (providerName.equals(clinic.getProviders().get(i).getName())) {
                        providerList.add(clinic.getProviders().get(i));
                    } else if (providerLastName.equals(clinic.getProviders().get(i).getLastName())) {
                        providerList.add(clinic.getProviders().get(i));
                    } else if (titleSelected == clinic.getProviders().get(i).getTitle()) {
                        providerList.add(clinic.getProviders().get(i));
                    }
                }
                if (providerList.isEmpty()) {
                    userInteraction.println(userInteraction.removeCharacters(clinic.getProviders().toString()));
                } else {
                    userInteraction.println(userInteraction.removeCharacters(providerList.toString()));
                }
                break;
            case 1://SEARCH PATIENTS
                List<Patient> patientList = new ArrayList<>();
                userInteraction.println(defaultMessage);
                String patientName = userInteraction.getName(true);
                String patientLastName = userInteraction.getLastName(true);
                Insurance insurance = null;
                try {
                    insurance = userInteraction.selectInsurance(clinic.getInsurances(), "Select insurance to search for");
                } catch (NotFoundException ex) {
                    userInteraction.println(ex.getObject());
                }
                for (int i = 0; i < clinic.getPatients().size(); i++) {
                    if (patientName.equals(clinic.getPatients().get(i).getName())) {
                        patientList.add(clinic.getPatients().get(i));
                    } else if (patientLastName.equals(clinic.getPatients().get(i).getLastName())) {
                        patientList.add(clinic.getPatients().get(i));
                    } else if (insurance == clinic.getInsurances().get(i)) {
                        patientList.add(clinic.getPatients().get(i));
                    }
                }
                if (patientList.isEmpty()) {
                    userInteraction.println(userInteraction.removeCharacters(clinic.getPatients().toString()));
                } else {
                    userInteraction.println(userInteraction.removeCharacters(patientList.toString()));
                }
                break;
            case 2:
                //By time frame(all appointments between), by provider, by patient, by procedure code
                List<FutureAppointment> appointmentsList = new ArrayList<>();
                List<AppointmentRecord> appointmentRecords = new ArrayList<>();
                userInteraction.println(defaultMessage);
                userInteraction.println(start);
                LocalDateTime rawStart = userInteraction.getFutureDate(true);
                LocalDate start = rawStart.toLocalDate();
                userInteraction.println(end);
                LocalDateTime rawEnd = userInteraction.getFutureDate(true);
                LocalDate end = rawEnd.toLocalDate();

                for (int i = 0, j = 0; i < clinic.getPastAppointments().size() && j < clinic.getFutureAppointments().size(); i++, j++) {
                    LocalDate tmp1 = LocalDate.of(
                            clinic.getPastAppointments().get(i).getDateTime().getYear(),
                            clinic.getPastAppointments().get(i).getDateTime().getMonth(),
                            clinic.getPastAppointments().get(i).getDateTime().getDayOfMonth());

                    LocalDate tmp2 = LocalDate.of(
                            clinic.getFutureAppointments().get(j).getDateTime().getYear(),
                            clinic.getFutureAppointments().get(j).getDateTime().getMonth(),
                            clinic.getFutureAppointments().get(j).getDateTime().getDayOfMonth());

                    if (tmp1.isAfter(start) && tmp1.isBefore(end)) {
                        appointmentRecords.add(clinic.getPastAppointments().get(i));
                    }
                    if (tmp2.isAfter(start) && tmp2.isBefore(end)) {
                        appointmentsList.add(clinic.getFutureAppointments().get(j));
                    }
                }
                Provider provider = null;
                Patient patient = null;
                try {
                    provider = userInteraction.selectProvider(clinic.getProviders(), "Select a Provider to search for", true);
                    patient = userInteraction.selectPatient(clinic.getPatients(), "Select a Patient to search for", true);
                } catch (NotFoundException ex) {
                    userInteraction.println(ex.getObject());
                }
                String code = userInteraction.getCode();

                for (int i = 0; i < clinic.getFutureAppointments().size(); i++) {
                    for (int j = 0; j < clinic.getFutureAppointments().get(i).getProcedures().size(); j++) {
                        if (clinic.getFutureAppointments().get(i).getProcedures().get(j).getProvider() == provider) {
                            appointmentsList.add(clinic.getFutureAppointments().get(i));
                        } else if (patient == clinic.getFutureAppointments().get(i).getPatient()) {
                            appointmentsList.add(clinic.getFutureAppointments().get(i));
                        } else if (code.equals(clinic.getFutureAppointments().get(i).getProcedures().get(j).getCode())) {
                            appointmentsList.add(clinic.getFutureAppointments().get(i));
                        }
                    }
                }

                for (int i = 0; i < clinic.getPastAppointments().size(); i++) {
                    for (int j = 0; j < clinic.getPastAppointments().get(i).getProcedures().size(); j++) {
                        if (clinic.getPastAppointments().get(i).getProcedures().get(j).getProcedure().getProvider() == provider) {
                            appointmentRecords.add(clinic.getPastAppointments().get(i));
                        } else if (patient == clinic.getPastAppointments().get(i).getPatient()) {
                            appointmentRecords.add(clinic.getPastAppointments().get(i));
                        } else if (code.equals(clinic.getPastAppointments().get(i).getProcedures().get(j).getProcedure().getCode())) {
                            appointmentRecords.add(clinic.getPastAppointments().get(i));
                        }
                    }
                }

                if (appointmentsList.isEmpty() && appointmentRecords.isEmpty()) {
                    userInteraction.println(userInteraction.removeCharacters(clinic.getFutureAppointments().toString()));
                    userInteraction.println(userInteraction.removeCharacters(clinic.getPastAppointments().toString()));
                } else if (!appointmentsList.isEmpty() && appointmentRecords.isEmpty()) {
                    userInteraction.println(userInteraction.removeCharacters(clinic.getFutureAppointments().toString()));
                } else {
                    userInteraction.println(userInteraction.removeCharacters(appointmentRecords.toString()));
                    userInteraction.println(userInteraction.removeCharacters(appointmentsList.toString()));
                }
                break;
            case 3:
                //SEARCH USERS
                List<User> userList = new ArrayList<>();
                userInteraction.println(defaultMessage);
                String username = userInteraction.getUsername(true);
                String firstName = userInteraction.getName(true);
                String lastName = userInteraction.getLastName(true);
                for (int i = 0; i < clinic.getUsers().size(); i++) {
                    if (username.equals(clinic.getUsers().get(i).getUsername())) {
                        userList.add(clinic.getUsers().get(i));
                    } else if (firstName.equals(clinic.getUsers().get(i).getName())) {
                        userList.add(clinic.getUsers().get(i));
                    } else if (lastName.equals(clinic.getUsers().get(i).getLastName())) {
                        userList.add(clinic.getUsers().get(i));
                    }
                }

                if (userList.isEmpty()) {
                    userInteraction.println("I couldn't find anything that matches that");
                } else {
                    userInteraction.println(userInteraction.removeCharacters(userList.toString()));
                }
                break;
            case 4:
                userInteraction.println("Returning to Previous Menu...");
                break;
            default:
                break;
        }
    }

    private String passwordVerified(boolean isFirstTime) throws IOException {
        String newPassword = null;
        boolean isValid = false;
        while (!isValid) {
            if (isFirstTime) {
                newPassword = userInteraction.changePasswordSameLine();
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

    private void loginScreen() throws IOException {
        boolean isValid = false;
        while (!isValid) {
            String username = userInteraction.getLoginUsername();
            String password = userInteraction.getLoginPassword();
            if (password.equals(loginCredentials.get(username))) {
                for (int i = 0; i < clinic.getUsers().size(); i++) {
                    if (clinic.getUsers().get(i).getUsername().equals(username)) {
                        currentUser = clinic.getUsers().get(i);
                    }
                }
                isValid = true;
            } else {
                userInteraction.println("The credentials are incorrect, please try again.\n");
            }
        }
    }

    private void autoSaveLoad() throws IOException, ClassNotFoundException {
        save();
        load();
    }

    private void save() throws IOException {
        makeDirectory();
        FileOutputStream fileOutputStream = new FileOutputStream(directory + "\\save.db");
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(clinic);
        out.close();
        out.flush();
        fileOutputStream.close();
    }

    private void load() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(directory + "\\save.db"));
        clinic = (Clinic) in.readObject();
        for (int i = 0; i < clinic.getUsers().size(); i++) {
            loginCredentials.put(clinic.getUsers().get(i).getUsername(), clinic.getUsers().get(i).getPassword());
        }
        in.close();

        for (int i = 0; i < clinic.getPastAppointments().size(); i++) {
            if (clinic.getPayments().get(i).getPatient() == clinic.getPastAppointments().get(i).getPatient()) {
                whenWasPaidByMonth.put(clinic.getPastAppointments().get(i).getDateTime().getMonth(), clinic.getPayments().get(i).getAmount());
            }
        }

        for (int i = 0; i < clinic.getPastAppointments().size(); i++) {
            if (clinic.getPayments().get(i).getPatient() == clinic.getPastAppointments().get(i).getPatient()) {
                whenWasPaidByDay.put(clinic.getPastAppointments().get(i).getDateTime().getDayOfMonth(), clinic.getPayments().get(i).getAmount());
            }
        }
    }

    private boolean makeDirectory() throws IOException {
        Path path = Paths.get(directory);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            return true;
        }
        return false;
    }
}
