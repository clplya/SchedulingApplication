package schedulingapplication;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableView<Appointment> appointmentTableView;
    @FXML
    private TableColumn<Appointment, String> AppointmentColumn;
    @FXML
    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private Customer selectedCustomer;
    private String titleText;// = FXCollections.observableArrayList();

    public void initialize(Customer customer) {
        AppointmentColumn.setCellValueFactory(new PropertyValueFactory<>("appointment"));
        selectedCustomer = customer;

        appointmentList.addAll((dbAppointment.getAppointmentsByCustomer(
                selectedCustomer.getCustomerId())));

        // appointmentTableView.setItems(null);
        for (int i = 0; i < appointmentList.size(); i++) {
            titleText = appointmentList.get(i).getTitle();

            //  appointmentTableView.setItems(dbAppointment.getAppointmentsByCustomer(i));
            apptTitleField.setText(dbAppointment.getAppointmentsByCustomer(selectedCustomer.getCustomerId()))
            );
            apptDescriptionField.setText((dbAppointment.getAppointmentsByCustomer(
                    selectedCustomer.getCustomerId()))).getDescription();
            apptLocationField.setText((dbAppointment.getAppointmentsByCustomer(
                    selectedCustomer.getCustomerId()))).getLocation();
            apptContactField.setText((dbAppointment.getAppointmentsByCustomer(
                    selectedCustomer.getCustomerId()))).getContact();
        }

//            selectedAppointmentComboBox.getItems().clear();
//            selectedAppointmentComboBox.getItems().add(titleText);
    }

    @FXML
    public void datePickerButtonHandler() {
//        LocalDate currentDate = datePicker.getValue();
//        ChronoLocalDate chronDate = datePicker.getChronology().date(currentDate);
//        System.out.println("Today's Date is: " + chronDate);
    }

    @FXML
    public void selectedAppointmentController() {
        selectedAppointment = (Appointment) selectedAppointmentComboBox.getValue();

    }

    @FXML
    public void saveButtonHandler(ActionEvent event) {
        Appointment newAppointment = new Appointment(
                10, 2, "Xray Procedure", "This is for an Xray", "Doctors Office", "Nurse", "none",
                Date.valueOf("2019-03-12"), Date.valueOf("2019-03-12"));
        dbAppointment.addNewAppointment(newAppointment);
    }

    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("You will lose all progress on this screen");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> window = alert.showAndWait();
        if (window.get() == ButtonType.OK) {
            stage = (Stage) cancelButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (window.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }
}
