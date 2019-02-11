package view;

import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface available. Make sure project running this API has this view methods.
 */
public interface DentistOfficeUserInteraction {

    /**
     * Prints the welcome line
     */
    void welcome();

    /**
     * @return The selection from the main menu
     */
    int mainMenu() throws IOException;

    /**
     * @return The selection from the view menu
     */
    int viewMenu() throws IOException;

    /**
     * @return The selection from the appointments menu
     */
    int appointmentsMenu() throws IOException;

    /**
     * @return The selection from the Create Menu with the Admin type view
     */
    int createAdminMenu() throws IOException;

    /**
     * @return The selection from the Create Menu with the Standard user view (Can't create other Users)
     */
    int createStandardMenu() throws IOException;

    /**
     * @return The selection from the edit menu with Standard user view
     */
    int editStandardMenu() throws IOException;

    /**
     * @return The selection from the edit menu with Admin user view
     */
    int editAdminMenu() throws IOException;

    /**
     * @return The selection from the delete menu with Admin user view
     */
    int deleteAdminMenu() throws IOException;

    /**
     * @return The selection from the delete menu with Standard user view
     */
    int deleteStandardMenu() throws IOException;

    /**
     * @return The selection from the search menu
     */
    int searchMenu() throws IOException;

    /**
     * @return The selection from the change the Provider information menu
     */
    int changeProviderInformationMenu() throws IOException;

    /**
     * @return The selection from changing the Patient menu
     */
    int changePatientInformationMenu() throws IOException;

    /**
     * @return The selection from changing the future appointment menu
     */
    int changeFutureAppointmentMenu() throws IOException;

    /**
     * @return The selection
     */
    int changeTimeMenu() throws IOException;

    /**
     * @return The selection from changing the Procedure information
     */
    int changeProcedureInformation() throws IOException;

    /**
     * @return The selection from changing another User's information
     */
    int changeUserInformationMenu() throws IOException;

    /**
     * @return The selection from changing User's own information
     */
    int changeUserOwnInformation() throws IOException;

    /**
     * Method that will place in a list all users available to choose from by entering the number associated with.
     * @param users The current List of Users
     * @param message Custom message to display when selecting Users
     * @return The user selected
     * @see NotFoundException Will be thrown if there is nothing inside of the List<>
     */
    User selectUser(List<User> users, String message) throws IOException;

    /**
     * Method that will place in a list all Provider available to choose from by entering the number associated with.
     * @param providers The current List of Providers
     * @param message Custom message to display when selecting Providers
     * @param b if wants to add an option at the of "Include all Providers".
     * @return The Provider selected
     * @see NotFoundException Will be thrown if there is nothing inside of the List<>
     */
    Provider selectProvider(List<Provider> providers, String message, boolean b) throws IOException;

    /**
     * Method that will place in a list all Patients available to choose from by entering the number associated with.
     * @param patients The current List of Patients
     * @param s Custom message to display when selecting Patients
     * @param b if wants to add an option at the of "Include all Patients".
     * @return The Patient selected
     * @see NotFoundException Will be thrown if there is nothing inside of the List<>
     */
    Patient selectPatient(List<Patient> patients, String s, boolean b) throws IOException;

    /**
     * Method that will place in a list all Future Appointments available to choose from by entering the number associated with.
     * @param appointments The current List of Future Appointments
     * @param message Custom message to display when selecting an appointment
     * @return The Future Appointment selected
     * @see NotFoundException Will be thrown if there is nothing inside of the List<>
     */
    FutureAppointment selectFutureAppointment(List<FutureAppointment> appointments, String message) throws IOException;

    /**
     * Method that will place in a list all Procedures available to choose from by entering the number associated with.
     * @param procedures The current List of Procedures
     * @param message Custom message to display when selecting Procedures
     * @return The Procedure selected
     * @see NotFoundException Will be thrown if there is nothing inside of the List<>
     */
    Procedure selectProcedure(List<Procedure> procedures, String message) throws IOException;

    /**
     * Method that will place in a list all Insurances available to choose from by entering the number associated with.
     * @param insurances The current List of Insurances
     * @param message Custom message to display when selecting an insurance
     * @return The Insurance selected
     * @see NotFoundException Will be thrown if there is nothing inside of the List<>
     */
    Insurance selectInsurance(List<Insurance> insurances, String message) throws IOException;

    /**
     * Method that will place in a list the User Roles available to choose from
     * @return The User Role selected
     */
    UserRole selectRole() throws IOException;

    /**
     * @return The new password
     */
    String changePassword() throws IOException;

    /**
     * A difference from changePassword() is that this method will place the cursor to change the password on the same line
     * @return The new password
     */
    String changePasswordSameLine() throws IOException;

    /**
     * Method that will call changePassword() twice and validates that the passwords match
     * @return the approved password
     */
    String verifyPassword() throws IOException;

    /**
     * @return the username
     */
    String getLoginUsername() throws IOException;

    /**
     * @return the logina password
     */
    String getLoginPassword() throws IOException;

    /**
     * Helper method that will print a new line to the conse
     * @param message The message to print
     */
    void println(String message) throws IOException;

    /**
     * Helper method that will print the message in the same line on the console
     * @param message The message to print
     */
    void print(String message) throws IOException;

    /**
     * @param allowEmpty if the user is allowed to enter an empty string
     * @return The name provided
     */
    String getName(boolean allowEmpty) throws IOException;

    /**
     * @param allowEmpty if the user is allowed to enter an empty string
     * @return The last name provided
     */
    String getLastName(boolean allowEmpty) throws IOException;

    /**
     * @param allowEmpty if the user is allowed to enter an empty string
     * @return The username provided
     */
    String getUsername(boolean allowEmpty) throws IOException;

    /**
     * Method that will print a choice of either Administrative or Standard when creating a new User
     * @return the User Role selected
     */
    UserRole getUserType() throws IOException;

    /**
     * Method that will print a choice of the available provider types when creating a new Provider
     * @return The Provider Type selected
     */
    ProviderType getProviderType() throws IOException;

    /**
     * Method that will print a choice of the available provider types when creating a new Provider adding an extra option that will return null.
     * @return The Provider Type selected. If return null, calling method needs to handle that action
     */
    ProviderType getProviderTypeWithEmptyEntry() throws IOException;

    /**
     * Method that will print a choice of the available Sources when choosing who paid for the procedure
     * @return The source selected
     */
    Source getSource() throws IOException;

    /**
     * @return the unique ID entered
     */
    int getUniqueID() throws IOException;

    /**
     * Method that will search the string for a . and @ from the end to the front.
     * @return the valid email
     */
    String getEmail() throws IOException;

    /**
     * Method using Regex to identify if the entry is a valid phone number
     * @return The valid 10 digit phone number
     */
    String getPhoneNumber() throws IOException;

    /**
     * @return The code entered
     */
    String getCode() throws IOException;

    /**
     * @return The description of the procedure entered
     */
    String getDescription() throws IOException;

    /**
     * @return The cost of the procedure
     */
    double getCost() throws IOException;

    /**
     * @return The insurance name
     */
    String getInsuranceName() throws IOException;

    /**
     * @return The group ID
     */
    String getGroupId() throws IOException;

    /**
     * @return The member ID
     */
    String getMemberId() throws IOException;

    /**
     * Method that will verify that input is a 16 digit (Valid card number)
     * @return a valid card number
     */
    String getCardNumber() throws IOException;

    /**
     * @return The month of expiration
     */
    int getExpMonth() throws IOException;

    /**
     * @return The expiration year
     */
    int getExpYear() throws IOException;

    /**
     * @return the card name
     */
    String getCardName() throws IOException;

    /**
     * Method that will prompt for a number of 3 digit
     * @return the valid number
     */
    int getCvv() throws IOException;

    /**
     * Method that will prompt for a valid zip code (5 digits)
     * @return The valid zip code
     */
    int getZipCode() throws IOException;

    /**
     * Method that will use the sub method to construct a valid LocalDateTime
     * @param b if true will only ask for year, month and day. If false will ask for the previously mentioned plus hour and minutes.
     * @return A LocalDateTime variable
     */
    LocalDateTime getFutureDate(boolean b) throws IOException;

    /**
     * @return A LocalDate variable with a year, month and day
     */
    LocalDate getLocalDate() throws IOException;

    /**
     * Get the year
     * @return The valid year
     */
    int getYear() throws IOException;

    /**
     * @return The number of the month (can't be greater than 12)
     */
    int getMonth() throws IOException;

    /**
     * @return A valid day
     */
    int getDay() throws IOException;

    /**
     * @return The hour
     */
    int getHour() throws IOException;

    /**
     * @return The minutes
     */
    int getMinute() throws IOException;

    /**
     * Method that will take a toString coming from an Array and it will take the braces and comas off for a bettter user display in the console
     * @param content the toString of an Array
     * @return The same String but cleaned
     */
    String removeCharacters(String content) throws IOException;

    /**
     * Simple validation method
     * @param msg Custome message to display
     * @return either true or false
     */
    boolean isCompleted(String msg) throws IOException;

    /**
     * Method that will ask user how many procedure they would like to add
     * @return the number desired
     */
    int getHowManyProcedures() throws IOException;

    /**
     * @return The selection from the menu of what they would like to edit from an appointment's provider
     */
    int editAppointmentProviderMenu() throws IOException;

    /**
     * @return The amount to be charge
     */
    double chargeAmount() throws IOException;

    /**
     * Simple menu with a choice of organizing either by day or month
     * @return the selection made
     */
    int selectMonthOrDay() throws IOException;

    /**
     * Method that will ask user how they would like to group by the Patient's balance
     * @return The selection made
     */
    int gruopBy() throws IOException;
}
