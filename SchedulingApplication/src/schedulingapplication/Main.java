package schedulingapplication;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import schedulingapplication.DomainObjects.Customer;
import schedulingapplication.DomainObjects.User;

public class Main extends Application {

    private Stage stage;
    private User loggedUser;
    private Customer selectedCustomer;

    public static void Main(String[] args) throws SQLException {
        Locale us = new Locale("en", "US");
        Locale sp = new Locale("sp", "SP");

        Application.launch(Main.class, (java.lang.String[]) null);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            stage = primaryStage;
            stage.setTitle("Scheduling Application");
            goToLogin();
            primaryStage.show();
        } catch (Exception ex) {
            Logger.getLogger(com.sun.javaws.Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void goToLogin() {
        try {
            LoginPageFXMLController login = (LoginPageFXMLController) replaceSceneContent("LoginPageFXML.fxml");
            login.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToCustomer() {
        try {
            CustomerPageController customer = (CustomerPageController) replaceSceneContent("CustomerPage.fxml");
            customer.setApp(this);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToAppointment() {
        try {
            AppointmentPageController appointment = (AppointmentPageController) replaceSceneContent("AppointmentPage.fxml");
            appointment.setApp(this);
            appointment.initialize(selectedCustomer);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
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
    
    public boolean userLogin() {
        goToCustomer();
        return true;
    }
}
