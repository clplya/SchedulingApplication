package schedulingapplication;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.Dao.DBCustomerDao;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;

public class AddAppointmentPageController {

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox selectedCustomerComboBox = new ComboBox();;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField apptDescriptionField;
    @FXML
    private TextField apptContactField;
    @FXML
    private TextField apptTitleField;
    @FXML
    private TextField apptLocationField;
    @FXML
    private TextField apptUrlField;
    @FXML
    private TextField apptStartTimeField;
    @FXML
    private TextField apptEndTimeField;
    @FXML
    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private final DBAppointmentDao dbAppointment = new DBAppointmentDao();
    private DBCustomerDao dbCustomer = new DBCustomerDao();
    @FXML
    private Appointment newAppointment = new Appointment();
    private static final AtomicInteger GENERATEDAPPOINTMENTID = new AtomicInteger(11);
    ObservableList customerList = FXCollections.observableArrayList();
    String customerString;

    @FXML
    void initialize() {
        customerList.addAll(dbCustomer.getAllCustomers());
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = (Customer) customerList.get(i);
            selectedCustomerComboBox.getItems().add(customer.getCustomerName());
        }
    }

    @FXML
    void saveButtonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save");
        alert.setHeaderText("Any changes to the Appointment will overwrite existing info");
        alert.setContentText("Are you sure you want to save?");

        Optional<ButtonType> window = alert.showAndWait();
        if (window.get() == ButtonType.OK) {
            setNewAppointment();
            dbAppointment.addAppointment(newAppointment.getAppointmentId(), newAppointment.getCustomerId(), newAppointment.getTitle(), newAppointment.getDescription(), newAppointment.getLocation(), newAppointment.getContact(), newAppointment.getURL(), newAppointment.getStartDate(), newAppointment.getEndDate());

            loadScene(event);
        } else if (window.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    void setNewAppointment() {
        customerString = selectedCustomerComboBox.getValue().toString();
        Customer loopCustomer = new Customer();
        for (int i = 0; i < customerList.size(); i++) {
            loopCustomer = (Customer) customerList.get(i);
        }
        //Set start and End Appointment Times
        String apptStartTime = apptStartTimeField.getText();
         LocalDateTime apptStartDateTime = LocalDateTime.parse(apptStartTime);
         String apptStopTime = apptEndTimeField.getText();
          LocalDateTime apptStopDateTime = LocalDateTime.parse(apptStopTime);

        newAppointment.setAppointmentId(GENERATEDAPPOINTMENTID.incrementAndGet());
        newAppointment.setCustomerId(loopCustomer.getCustomerId());
        newAppointment.setTitle(apptTitleField.getText());
        newAppointment.setDescription(apptDescriptionField.getText());
        newAppointment.setLocation(apptLocationField.getText());
        newAppointment.setContact(apptContactField.getText());
        newAppointment.setURL(apptUrlField.getText());
        newAppointment.setStartDate(apptStartDateTime);
        newAppointment.setEndDate(apptStopDateTime);
    }

    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("You will lose all progress on this screen");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> window = alert.showAndWait();
        if (window.get() == ButtonType.OK) {
            loadScene(event);

        } else if (window.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    @FXML
    void selectedCustomerHandler(ActionEvent event) throws IOException{
        
    }

    @FXML
    private void loadScene(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        if (event.getSource() == cancelButton) {
            stage = (Stage) cancelButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
        } else if (event.getSource() == saveButton) {
            stage = (Stage) saveButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
        } else {
            stage = null;
            root = null;
            System.out.println("Redirect Failed");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
