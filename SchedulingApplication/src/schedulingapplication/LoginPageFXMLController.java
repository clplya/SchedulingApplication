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

    private Application application;
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

    private boolean loginSuccessful = false;
    private boolean loginFailed = false;
    private Locale defaultLocale;
    private final DBUserDao dbUser = new DBUserDao();

    private User loginUser;

    public void setApp(Main application) {
        this.application = application;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //remove
        username.setText("Edgar");
        password.setText("123456");

        defaultLocale = Locale.getDefault();
    }

    @FXML
    public void germanButtonHandler() {
        String language = "ge";
        String country = "GE";
        Locale locale = new Locale(language, country);
        Locale.setDefault(locale);
        System.out.println("Locale is now " + locale);
    }

    @FXML
    public void spanishButtonHandler() {
        String language = "sp";
        String country = "SP";
        Locale locale = new Locale(language, country);
        Locale.setDefault(locale);
        System.out.println("Locale is now " + locale);
    }

    @FXML
    public void englishButtonHandler() {
        String language = "en";
        String country = "US";
        Locale locale = new Locale(language, country);
        Locale.setDefault(locale);
        System.out.println("Locale is now " + locale);
    }

    @FXML
    public void exitButtonHandler() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void loginButtonHandlerDao(ActionEvent event) throws SQLException, IOException {
        boolean passwordMatch = false;

        String inputUserName = username.getText();
        String inputPassword = password.getText();

        User mappedUser = dbUser.getUserByUserName(inputUserName);
        loginUser = mappedUser;

        Locale locale = localeTracker();
        Locale.setDefault(locale);

        if (!mappedUser.getPassword().equals(inputPassword)) {
            loginResult();
        } else {
            if (mappedUser.getPassword().equals(inputPassword)) {
                passwordMatch = true;
            }
        }
        if (passwordMatch) {
            loginSuccessful = true;
            loginResult();
        } else {
            loginFailed = true;
        }
    }

    @FXML
    public void loginResult() throws SQLException, IOException {
        Locale locale = localeTracker();
        Locale.setDefault(locale);

        ResourceBundle rb = ResourceBundle.getBundle("schedulingapplication/Scheduler", locale);

        if (!loginSuccessful) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("LoginUnsuccessful"));
            alert.setHeaderText(rb.getString("PasswordIsIncorrect"));
            alert.setContentText(rb.getString("PleaseTryAgain"));
            alert.showAndWait();
            rb.getString(rb.getString("LoginUnsuccessful"));
        } else {

            if (!loginSuccessful) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("LoginUnsuccessful"));
                alert.setHeaderText(rb.getString("PasswordIsIncorrect"));
                alert.setContentText(rb.getString("PleaseTryAgain"));
                alert.showAndWait();
            } else if (loginSuccessful & loginFailed) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("LoginUnsuccessful"));
                alert.setHeaderText(rb.getString("PleaseTryAgain"));
                alert.setContentText(rb.getString("PleaseTryAgain"));
                alert.showAndWait();
            } else if (loginSuccessful & !loginFailed) {
                System.out.println(rb.getString("LoginUnsuccessful"));
                Stage stage;
                Parent root;

                stage = (Stage) loginButton.getScene().getWindow();
                Stage currentStage = (Stage) loginButton.getScene().getWindow();
                root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);

                FXMLLoader loader = new FXMLLoader();
                CustomerPageController controller = loader.getController();
                stage.show();
            }
        }
    }

    public Locale localeTracker() {
        String lang = defaultLocale.getLanguage();
        String country = defaultLocale.getCountry();

        Locale locale = new Locale(lang, country);

        return locale;
    }
//
//    public String loadLocalization() {
//        String lang = "sp";
//        String country = "SP";
//
//        Locale l = new Locale(lang, country);
//        ResourceBundle r = ResourceBundle.getBundle("schedulingapplication/Scheduler", l);
//
//        String ok = r.getString("LoginNotFound");
//        return ok;
//    }
}
