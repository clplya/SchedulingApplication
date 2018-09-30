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

    private boolean loginSuccessful = false;
    private boolean loginFailed = false;

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

        Locale.getDefault();

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
//original
//    @FXML
//    public void loginResult() throws SQLException, IOException {
//        if (!loginSuccessful) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Login Unsuccessful");
//            alert.setHeaderText("Password is Incorrect");
//            alert.setContentText("Please Try Again");
//            alert.showAndWait();
//        } else if (loginSuccessful & loginFailed) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Login Unsuccessful");
//            alert.setHeaderText("Please Try Again");
//            alert.setContentText("Please Try Again");
//            alert.showAndWait();
//        } else if (loginSuccessful & !loginFailed) {
//            System.out.println("Login Successful");
//            Stage stage;
//            Parent root;
//
//            stage = (Stage) loginButton.getScene().getWindow();
//            Stage currentStage = (Stage) loginButton.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
//
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//
//            FXMLLoader loader = new FXMLLoader();
//            CustomerPageController controller = loader.getController();
//            stage.show();
//        }
//    }

    @FXML
    public void loginResult() throws SQLException, IOException {
        String usernameValue = loginUser.getUserName();
        if (usernameValue.equals("Edgar")) {
            //ResourceBundle default = ResourceBundle.getBundle("fdsa");
            ResourceBundle rb = ResourceBundle.
                    getBundle("Scheduler");

            if (!loginSuccessful) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("Login Unsuccessful"));
                alert.setHeaderText("Password is Incorrect");
                alert.setContentText("Please Try Again");
                alert.showAndWait();
                rb.getString("Login Unsuccessful");
            } else {

                if (!loginSuccessful) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Unsuccessful");
                    alert.setHeaderText("Password is Incorrect");
                    alert.setContentText("Please Try Again");
                    alert.showAndWait();
                } else if (loginSuccessful & loginFailed) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Login Unsuccessful");
                    alert.setHeaderText("Please Try Again");
                    alert.setContentText("Please Try Again");
                    alert.showAndWait();
                } else if (loginSuccessful & !loginFailed) {
                    System.out.println("Login Successful");
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
    }
}
