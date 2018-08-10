/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author clply_000
 */
public class Main extends Application {
    private Stage stage;
   
        
    public static void main(String[] args) throws SQLException {
        Application.launch(Main.class, (java.lang.String[])null);     
    }
    
   @Override
    public void start(Stage primaryStage) {
        try{
            stage = primaryStage;
            stage.setTitle("Scheduling Application");
            goToLogin();
            primaryStage.show();
        }
         catch (Exception ex) {
            Logger.getLogger(com.sun.javaws.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void goToLogin()
    {
        try {
            LoginPageFXMLController login = (LoginPageFXMLController) replaceSceneContent("LoginPageFXML.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private Initializable replaceSceneContent(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        InputStream in = Main.class.getResourceAsStream(fxml);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource(fxml));
        AnchorPane page;
        try {
            page = (AnchorPane) loader.load(in);
        } finally {
            in.close();
        } 
        Scene scene = new Scene(page, 700, 500);
        stage.setScene(scene);
        stage.sizeToScene();
        return (Initializable) loader.getController();
    }

}
