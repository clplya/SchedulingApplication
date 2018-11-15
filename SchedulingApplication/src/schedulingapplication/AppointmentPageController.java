package schedulingapplication;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;
import schedulingapplication.DomainObjects.User;

public class AppointmentPageController implements Initializable {

    private Appointment selectedAppointment = new Appointment();
    private final DBAppointmentDao dbAppointment = new DBAppointmentDao();

    @FXML
    private Button cancelButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button calendarMonthButton;
    @FXML
    private Button calendarWeekButton;
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
    private TextField apptUrlField;
    @FXML
    private TextField apptStartDateField;
    @FXML
    private TextField apptEndDateField;
    @FXML
    private ComboBox selectedCustomerComboBox = new ComboBox();
    @FXML
    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private Customer selectedCustomer;
    private String titleText;// = FXCollections.observableArrayList();
    private Main application;
    
    public void setApp(Main application){
        this.application = application;
        User loggedUser = application.getLoggedUser();
         apptTitleField.setText(selectedAppointment.getTitle());
        apptDescriptionField.setText(selectedAppointment.getDescription());
        apptLocationField.setText(selectedAppointment.getLocation());
        apptContactField.setText(selectedAppointment.getContact());
    }

    public void initialize(Customer customer) {
        selectedCustomer = customer;
        appointmentList.addAll((dbAppointment.getAppointmentsByCustomer(
                selectedCustomer.getCustomerId())));
        selectedAppointment = appointmentList.get(1);
        selectedCustomerComboBox.getItems().add(selectedAppointment.getTitle());

        for (int i = 0; i < appointmentList.size(); i++) {
            titleText = appointmentList.get(i).getTitle();
            selectedCustomerComboBox.getItems().add(titleText);
        }
        selectedCustomerComboBox.setValue(selectedCustomerComboBox.getItems().get(0));
        updateAppointmentDate(selectedAppointment.getStartDate());
        // calendarDisablePastCells();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //empty
    }

    private void changeSelectedAppointment() {
        ObservableList newAppointment = FXCollections.observableArrayList();
        newAppointment.addAll(selectedCustomerComboBox.getItems());
        String title = (String) selectedCustomerComboBox.getValue();
        appointmentList.forEach((appointments) -> {
            if (title.equals(appointments.getTitle())) {
                selectedAppointment = appointments;
            }
        });
    }

    public void calendarWeekButtonHandler() { //currently bugged - cannot go forward on calendar
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datepicker) {
                return new DateCell() {
                    public void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item.toLocalDate(), empty);
                        if (item.isAfter(selectedAppointment.getEndDate())) {
                            this.setVisible(false);
                        }
//                        if (item.isBefore(
//                                datePicker.getValue().plusDays(1))) {
//                            setDisable(true);
//                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
    }

    public void calendarMonthButtonHandler() {

    }

    private void updateAppointmentDate(LocalDateTime apptDate) {

        datePicker.setValue(apptDate.toLocalDate());
    }

    @FXML
    void datePickerButtonHandler(ActionEvent event) throws IOException {
        //LocalDate date = datePicker.getValue();
    }

    //Take off @FXML tag if this breaks
    @FXML
    private void calendarDisablePastCells() {
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datepicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        DayOfWeek day = DayOfWeek.from(item);
                        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
                            this.setTextFill(Color.DARKCYAN);
                        }
//                        if (item.isAfter(LocalDate.now())) {
//                            this.setDisable(true);
//                        }
                        if (item.isBefore(
                                datePicker.getValue().plusDays(1))) {
                            setDisable(true);
                        }
                    }
                };
            }
        };
        datePicker.setDayCellFactory(dayCellFactory);
    }

    @FXML
    public void selectedCustomerHandler() {
        changeSelectedAppointment();
        updateAppointmentDate(selectedAppointment.getStartDate());
      //  updateAppointmentDetails();
    }

    @FXML
    public void saveButtonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Save");
        alert.setHeaderText("Any changes to the Appointment will overwrite existing info");
        alert.setContentText("Are you sure you want to save?");

        Optional<ButtonType> window = alert.showAndWait();
        if (window.get() == ButtonType.OK) {
            dbAppointment.updateAppointmentTitle(selectedAppointment.getAppointmentId(),
                    selectedAppointment.getTitle());
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