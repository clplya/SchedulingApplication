package schedulingapplication;

import java.io.IOException;
import java.lang.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

    private Main application;
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet rs = null;
    private boolean loginSuccessful = false;
    private boolean loginFailed = false;

    // private boolean passwordMatch = false;
    private DBUserDao dbUser = new DBUserDao();

    public void setApp(Main application) {
        this.application = application;
        //JDBCConnection.getConnection();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText("admin");
        password.setText("123456");

    }

    @FXML
    public void exitButtonHandler() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void loginButtonHandlerDao(ActionEvent event) throws SQLException, IOException {
        boolean passwordMatch = false;
        String userNameValue = username.getText();
        String passwordValue = password.getText();

        User mappedUser = dbUser.getUserByUserName(userNameValue);

        if (!mappedUser.getPassword().equals(passwordValue)) {
            throw new RuntimeException("Password is incorrect");
        } else {
            if (mappedUser.getPassword().equals(passwordValue)) {
                passwordMatch = true;
            }
        }
        if (passwordMatch) {
            loginSuccessful = true;
            System.out.println("Login Successful");
            Stage stage;
            FXMLLoader loader = new FXMLLoader();

            stage = (Stage) loginButton.getScene().getWindow();
            loader.setLocation(getClass().getResource("CustomerPage.fxml"));

            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(tableViewScene);
            window.show();

        } else {
            loginFailed = true;
        }
    }

//    @FXML
//    public void loginButtonHandler(ActionEvent event) throws SQLException, IOException {
//        System.out.println("Logging in");
//
//        connection = JDBCConnection.getConnection();
//        statement = connection.createStatement();
//
//        Map<String, String> usernamePasswordMap = new HashMap<>();
//        rs = statement.executeQuery("select * from user");
//        while (rs.next()) {
//            String userNameValue = rs.getString("userName");
//            String passwordValue = rs.getString("password");
//            usernamePasswordMap.put(userNameValue, passwordValue);
//
//            String passwordReturned = usernamePasswordMap.get(passwordValue);
//
//            String passwordText = password.getText();
//            passwordMatch = passwordValue.equals(passwordText);
//        }
//        if (passwordMatch) {
//            loginSuccessful = true;
//            System.out.println("Login Successful");
//            Stage stage;
//            FXMLLoader loader = new FXMLLoader();
//
//            stage = (Stage) loginButton.getScene().getWindow();
//            loader.setLocation(getClass().getResource("CustomerPage.fxml"));
//
//            Parent tableViewParent = loader.load();
//            Scene tableViewScene = new Scene(tableViewParent);
//            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            window.setScene(tableViewScene);
//            window.show();
//
//        } else {
//            loginFailed = true;
//        }
//    }
    @FXML
    public void loginResult() throws SQLException, IOException {
        if (!loginSuccessful) {
            System.out.println("Login Failed");
        } else if (loginSuccessful & loginFailed) {
            System.out.println("Login not Determined");

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
            // controller.initialize();
            //  currentStage.hide();
            stage.show();

        }
    }
}
