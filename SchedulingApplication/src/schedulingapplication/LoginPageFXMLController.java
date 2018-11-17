package schedulingapplication;

import java.io.IOException;
import java.lang.*;
import java.net.URL;
import java.sql.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.Dao.DBReminderDao;
import schedulingapplication.Dao.DBUserDao;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Reminder;
import schedulingapplication.DomainObjects.User;

public class LoginPageFXMLController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private javafx.scene.control.Button loginButton;
    @FXML
    private javafx.scene.control.Button exitButton;
    @FXML
    private javafx.scene.control.Button germanLocaleBtn;
    @FXML
    private javafx.scene.control.Button spanishLocaleBtn;
    @FXML
    private javafx.scene.control.Button englishLocaleBtn;
    private boolean passwordMatch;
    private boolean loginSuccessful = false;
    private boolean loginFailed = false;
    private Locale defaultLocale;
    private final DBUserDao dbUser = new DBUserDao();
    private DBAppointmentDao dbAppointment = new DBAppointmentDao();
    private final DBReminderDao dbReminder = new DBReminderDao();
    @FXML
    private ObservableList<Appointment> userAppointments = FXCollections.observableArrayList();
    private ArrayList<Reminder> appointmentReminders = new ArrayList();

    private Main application;

    public void setApp(Main application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText("Edgar");
        password.setText("password");

        defaultLocale = Locale.getDefault();
    }

    @FXML
    public void germanButtonHandler() {
        String language = "ge";
        String country = "GE";
        defaultLocale = new Locale(language, country);
        Locale.setDefault(defaultLocale);
        System.out.println("Locale is now " + defaultLocale);
    }

    @FXML
    public void spanishButtonHandler() {
        String language = "sp";
        String country = "SP";
        defaultLocale = new Locale(language, country);
        Locale.setDefault(defaultLocale);
        System.out.println("Locale is now " + defaultLocale);
    }

    @FXML
    public void englishButtonHandler() {
        String language = "en";
        String country = "US";
        defaultLocale = new Locale(language, country);
        Locale.setDefault(defaultLocale);
        System.out.println("Locale is now " + defaultLocale);
    }

    @FXML
    public void exitButtonHandler() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void loginButtonHandlerDao(ActionEvent event) throws SQLException, IOException {
        checkForPasswordMatch();
        loginSuccessful = false;
        loginFailed = false;

        if (!passwordMatch) {
            loginFailed = true;
            loginResult();
        } else if (passwordMatch) {
            loginSuccessful = true;
            loginResult();
        } else {
            loginFailed = true;
        }
    }

    private boolean checkForPasswordMatch() {
        passwordMatch = false;

        String inputUserName = username.getText();
        String inputPassword = password.getText();

        User mappedUser = dbUser.getUserByUserName(inputUserName);
        if (mappedUser.getPassword().equals(inputPassword)) {
            passwordMatch = true;
        }
        return passwordMatch;
    }

    @FXML
    public void loginResult() throws SQLException, IOException {
        Locale locale = localeTracker();

        ResourceBundle rb = ResourceBundle.getBundle("schedulingapplication/Scheduler", locale);

        if (!loginSuccessful) {
            throwLoginError();
        } else if (loginSuccessful & loginFailed) {
            throwLoginError();
        } else if (loginSuccessful & !loginFailed) {
            System.out.println(rb.getString("LoginSuccessful"));
            UserLoginTracker userLoginTracker = new UserLoginTracker();
            userLoginTracker.userLoginTracker(rb.getString("LoginSuccessful"));
            alertForAppointment();
            application.userLogin();
        }
    }

    @FXML
    public void throwLoginError() throws IOException {
        Locale locale = localeTracker();
        ResourceBundle rb = ResourceBundle.getBundle("schedulingapplication/Scheduler", locale);

//        UserLoginTracker userLoginTracker = new UserLoginTracker();
//        userLoginTracker.userLoginTracker(rb.getString("Login Unsuccessful"));
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(rb.getString("LoginUnsuccessful"));
        alert.setHeaderText(rb.getString("PasswordIsIncorrect"));
        alert.setContentText(rb.getString("PleaseTryAgain"));
        alert.showAndWait();
    }

    public Locale localeTracker() {
        String lang = defaultLocale.getLanguage();
        String country = defaultLocale.getCountry();

        Locale locale = new Locale(lang, country);

        return locale;
    }

    private ArrayList<Reminder> getAppointmentReminder() {
        Reminder reminder;
        try {
            if (loginSuccessful) {
                User loggedUser = application.getLoggedUser();
                String userName = loggedUser.getUserName();

                userAppointments.addAll(dbAppointment.getAppointmentsByContact(userName));
                for (int i = 0; i < userAppointments.size(); i++) {
                    Appointment appointment = userAppointments.get(i);
                    reminder = dbReminder.getReminderPerAppointment(appointment);
                    appointmentReminders.add(reminder);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception");
        }
        return appointmentReminders;
    }

    private void alertForAppointment() {
        getAppointmentReminder();
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.ofMinutes(15);
        for (int i = 0; i < appointmentReminders.size(); i++) {
            Reminder reminder = appointmentReminders.get(i);
            if (reminder.getReminderDateTime().plus(duration).equals(now)) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Appointment starting soon");
                alert.setHeaderText("Appointment begins in the next 15 minutes");
                alert.setContentText("Appointment Begins Soon");

                Optional<ButtonType> window = alert.showAndWait();
                if (window.get() == ButtonType.OK) {
                    alert.close();
                } else if (window.get() == ButtonType.CANCEL) {
                    alert.close();
                }
            }
        }
    }
}
