package schedulingapplication;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;

public class AppointmentPageController {

    private Customer selectedCustomer;
    private Appointment selectedAppointment;
    private DBAppointmentDao dbAppointment = new DBAppointmentDao();

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
    private ComboBox<String> selectedAppointmentComboBox = new ComboBox<String>();
    @FXML
    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    public void initialize(Customer customer) throws InvocationTargetException {

        selectedCustomer = customer;
        appointmentList.addAll(dbAppointment.getAppointment(customer.getCustomerId()));

        for (int i = 0; i < appointmentList.size(); i++) {
            selectedAppointmentComboBox.setItems(appointmentList);
            //selectedAppointmentComboBox.itemsProperty().setValue(i);
        }
    }

    @FXML
    public void datePickerButtonHandler() {
        LocalDate currentDate = datePicker.getValue();
        ChronoLocalDate chronDate = datePicker.getChronology().date(currentDate);
        System.out.println("Today's Date is: " + chronDate);
    }

    public void selectedAppointmentController() {

    }
}
