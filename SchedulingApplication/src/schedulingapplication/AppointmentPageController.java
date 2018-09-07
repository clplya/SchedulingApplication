package schedulingapplication;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;

public class AppointmentPageController {

    @FXML
    private Customer selectedCustomer;
    private DatePicker datePicker;
    private javafx.scene.control.ComboBox selectedAppointmentComboBox;
    DBAppointmentDao dbAppointment = new DBAppointmentDao();
    private Appointment selectedAppointment;
    private ObservableList<Appointment> appointmentList;

    public void initialize(Customer customer) {
        selectedCustomer = customer;
        appointmentList.addAll(dbAppointment.getAppointment(customer.getCustomerId()));

        selectedAppointmentComboBox.setItems(appointmentList);

//        if (appointmentList.size() > 0) {
//            selectedAppointment = (Appointment) selectedAppointmentComboBox.getItems().get(0);
//        }
    }

    public void datePickerButtonHandler() {
        LocalDate currentDate = datePicker.getValue();
        ChronoLocalDate chronDate = datePicker.getChronology().date(currentDate);
        System.out.println("Today's Date is: " + chronDate);
    }

    public void getAllAppointments() {
        //  appointmentManager.clear();
        //  appointmentManager.getAllAppointments();

        java.util.Date date = new Date();

    }
}
