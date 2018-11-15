package schedulingapplication;

import java.io.IOException;
import java.lang.*;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schedulingapplication.Dao.DBUserDao;
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
    
    private Main application;
    
    public void setApp(Main application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //remove
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
            userLoginTracker.userLoginTracker();
            application.userLogin();

            //Navigation Code that works
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(getClass().getResource("CustomerPage.fxml"));
//            Parent tableViewParent = loader.load();
//            Scene tableViewScene = new Scene(tableViewParent);
//            Stage window = (Stage) (loginButton.getScene().getWindow());
//            window.setScene(tableViewScene);
//            window.show();
        }
    }

    private void throwLoginError() {
        Locale locale = localeTracker();
        ResourceBundle rb = ResourceBundle.getBundle("schedulingapplication/Scheduler", locale);

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
    //Create country in DB: INSERT INTO U03xBv.country (countryId, country, createDate, createdBy, lastUpdate, lastUpdateBy)
    //VALUES (2, 'Mexico', CURRENT_TIMESTAMP, 'Admin', DEFAULT, 'Admin')
}
