package schedulingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;

public class AppointmentPageController {

   @FXML
   private Customer selectedCustomer; 
   private DatePicker datePicker;
   private javafx.scene.control.ComboBox selectedAppointmentComboBox;
   private Appointment selectedAppointment;
   private Connection conn;
   private Statement stmt;
   private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
   private ObservableList<Appointment> appointmentList;
   
    public void initialize(Customer customer) {
        try {
            Class.forName(jdbcDriver);
            try {
                conn = DriverManager.getConnection(DAO.DB_URL, DAO.USER, DAO.PASS);
                stmt = conn.createStatement();
                
            } catch (SQLException ex) {
                System.out.println("Failed to create the DB connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        }

        selectedCustomer = customer;
        getAllAppointments();
      //  appointmentList = appointmentManager.getCustomerAppointment(customer.getCustomerId());
        selectedAppointmentComboBox.setItems(appointmentList);
        
        if(appointmentList.size() > 0){
         selectedAppointment = (Appointment)selectedAppointmentComboBox.getItems().get(0);
        }       
        
    }   
    
    public void datePickerButtonHandler(){
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
