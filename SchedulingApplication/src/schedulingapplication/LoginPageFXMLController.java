package schedulingapplication;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.lang.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

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
    
    private boolean passwordMatch = false;
    
    private String sql = "select * from address";
    
    public void setApp(Main application) throws SQLException{
        this.application = application;
        //JDBCConnection.getConnection();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        username.setText("admin");
        password.setText("123456");   
        
    }    
       
    @FXML
    private void exitButtonHandler() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void loginButtonHandler(ActionEvent event) throws SQLException, IOException{
        System.out.println("Logging in");
        
        connection = JDBCConnection.getConnection();
        statement = connection.createStatement();
        
        Map<String,String> usernamePasswordMap = new HashMap<>();
        rs = statement.executeQuery("select * from user");
        while(rs.next()) {
            String userNameValue = rs.getString("userName");
            String passwordValue = rs.getString("password");
            usernamePasswordMap.put(userNameValue,passwordValue);
           //line below needs to grab the password from the page 
            String passwordReturned = usernamePasswordMap.get(passwordValue);
            
            
            String passwordText = password.getText();
            passwordMatch = passwordValue.equals(passwordText);
        }
            if(passwordMatch){
                   loginSuccessful = true;
            }
            else{
                    loginFailed = true;
            }
            loginResult();
    }
    
    private void loginResult() throws IOException{
        if(!loginSuccessful){
            System.out.println("Login Failed");
        } else if (loginSuccessful & loginFailed){
            System.out.println("Login not Determined");
            
        } else if(loginSuccessful & !loginFailed){
            System.out.println("Login Successful");
            Stage stage = new Stage();
            
            Parent root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            Stage currentStage = (Stage) loginButton.getScene().getWindow();
            
            currentStage.hide();
            stage.show();
        }
    }
}
