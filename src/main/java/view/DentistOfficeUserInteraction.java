package view;

import model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface DentistOfficeUserInteraction {

    void welcome();

    int mainMenu() throws IOException;

    int viewMenu() throws IOException;

    int appointmentsMenu() throws IOException;

    int createAdminMenu() throws IOException;

    int createStandardMenu() throws IOException;

    int editStandardMenu() throws IOException;

    int editAdminMenu() throws IOException;

    int deleteAdminMenu() throws IOException;

    int deleteStandardMenu() throws IOException;

    int searchMenu() throws IOException;

    int changeProviderInformationMenu() throws IOException;

    int changePatientInformationMenu() throws IOException;

    int changeFutureAppointmentMenu() throws IOException;

    int changeTimeMenu() throws IOException;

    int changeProcedureInformation() throws IOException;

    int changeUserInformationMenu() throws IOException;

    int changeUserOwnInformation() throws IOException;

    User selectUser(List<User> users, String message) throws IOException;

    Provider selectProvider(List<Provider> providers, String message, boolean b) throws IOException;

    Patient selectPatient(List<Patient> patients, String s, boolean b) throws IOException;

    FutureAppointment selectFutureAppointment(List<FutureAppointment> appointments, String message) throws IOException;

    Procedure selectProcedure(List<Procedure> procedures, String message) throws IOException;

    Insurance selectInsurance(List<Insurance> insurances, String message) throws IOException;

    UserRole selectRole() throws IOException;

    String changePassword() throws IOException;

    String changePasswordSameLine() throws IOException;

    String verifyPassword() throws IOException;

    String getLoginUsername() throws IOException;

    String getLoginPassword() throws IOException;

    void println(String message) throws IOException;

    void print(String message) throws IOException;

    String getName(boolean allowEmpty) throws IOException;

    String getLastName(boolean allowEmpty) throws IOException;

    String getUsername() throws IOException;

    UserRole getUserType() throws IOException;

    ProviderType getProviderType() throws IOException;

    ProviderType getProviderTypeWithEmptyEntry() throws IOException;

    Source getSource() throws IOException;

    int getUniqueID() throws IOException;

    String getEmail() throws IOException;

    String getPhoneNumber() throws IOException;

    String getCode() throws IOException;

    String getDescription() throws IOException;

    double getCost() throws IOException;

    String getInsuranceName() throws IOException;

    String getGroupId() throws IOException;

    String getMemberId() throws IOException;

    String getCardNumber() throws IOException;

    int getExpMonth() throws IOException;

    int getExpYear() throws IOException;

    String getCardName() throws IOException;

    int getCvv() throws IOException;

    int getZipCode() throws IOException;

    LocalDateTime getFutureDate(boolean b) throws IOException;

    LocalDate getLocalDate() throws IOException;

    int getYear() throws IOException;

    int getMonth() throws IOException;

    int getDay() throws IOException;

    int getHour() throws IOException;

    int getMinute() throws IOException;

    String getInput(String msg, boolean allowEmpty) throws IOException;

    String removeCharacters(String content) throws IOException;

    boolean isCompleted(String msg) throws IOException;

    int getHowManyProcedures() throws IOException;

    int editAppointmentProviderMenu() throws IOException;

    double chargeAmount() throws IOException;

}
