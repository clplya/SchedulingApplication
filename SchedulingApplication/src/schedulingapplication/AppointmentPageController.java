package schedulingapplication;

import java.io.IOException;
import java.time.*;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.Dao.DBCustomerDao;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;

public class AppointmentPageController {

    private Appointment selectedAppointment;
    private final DBAppointmentDao dbAppointment = new DBAppointmentDao();
    private final DBCustomerDao dbCustomer = new DBCustomerDao();

    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField apptTitleField;
    @FXML
    private TextField apptDescriptionField;
    @FXML
    private TextField apptLocationField;
    @FXML
    private TextField apptContactField;
    @FXML
    private ComboBox selectedAppointmentComboBox = new ComboBox();
    @FXML
    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private Customer selectedCustomer;
    private String titleText;// = FXCollections.observableArrayList();

    public void initialize(Customer customer) {
        selectedCustomer = customer;

        appointmentList.addAll((dbAppointment.getAppointmentsByCustomer(
                selectedCustomer.getCustomerId())));
        selectedAppointment = appointmentList.get(1);

        selectedAppointmentComboBox.getItems().add(selectedAppointment.getTitle());
        // selectedAppointmentComboBox.getItems().clear();

        for (int i = 0; i < appointmentList.size(); i++) {
            titleText = appointmentList.get(i).getTitle();

            updateAppointmentDetails();

            selectedAppointmentComboBox.getItems().add(titleText);
        }

        selectedAppointmentComboBox.setValue(selectedAppointmentComboBox.getItems().get(0));

        updateAppointmentDate();
    }

    private void changeSelectedAppointment() {
        ObservableList newAppointment = FXCollections.observableArrayList();
        newAppointment.addAll(selectedAppointmentComboBox.getItems());
        String title = (String) selectedAppointmentComboBox.getValue();
        appointmentList.forEach((appointments) -> {
            if (title.equals(appointments.getTitle())) {
                selectedAppointment = appointments;
            }
        });

    }

    private void updateAppointmentDate() {
        String selectedAppointmentTime = selectedAppointment.getStartDate().toString();

        int year = Integer.parseInt(selectedAppointmentTime.substring(0, 4));
        int month = Integer.parseInt(selectedAppointmentTime.substring(5, 7));
        int day = Integer.parseInt(selectedAppointmentTime.substring(8, 10));

        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.of(year, month, day));
    }

    private void updateAppointmentDetails() {
        apptTitleField.setText(selectedAppointment.getTitle());
        apptDescriptionField.setText(selectedAppointment.getDescription());
        apptLocationField.setText(selectedAppointment.getLocation());
        apptContactField.setText(selectedAppointment.getContact());
    }

    @FXML
    void datePickerButtonHandler(ActionEvent event) throws IOException {
        LocalDate date = datePicker.getValue();
        System.err.println("Selected date: " + date);

        //datePicker.setValue(selectedAppointment.getStartDate();
    }

    @FXML
    public void selectedAppointmentController() {
        changeSelectedAppointment();
        updateAppointmentDate();
        updateAppointmentDetails();
    }

    @FXML
    public void saveButtonHandler(ActionEvent event) throws IOException {
        //Appointment newAppointment = new Appointment(
        //       10, 2, "Xray Procedure", "This is for an Xray", "Doctors Office", "Nurse", "none",
        //       LocalDate.of(2019, 03, 12), LocalDate.of(2019, 03, 12));
        // dbAppointment.addNewAppointment(newAppointment);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save");
        alert.setHeaderText("Any changes to the Appointment will overwrite existing info");
        alert.setContentText("Are you sure you want to save?");

        Optional<ButtonType> window = alert.showAndWait();
        if (window.get() == ButtonType.OK) {
            loadScene(event);

        } else if (window.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    @FXML
    public void cancelButtonHandler(ActionEvent event) throws IOException {
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
