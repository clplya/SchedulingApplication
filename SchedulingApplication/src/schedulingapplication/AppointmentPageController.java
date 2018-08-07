package schedulingapplication;

import java.sql.Connection;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;
import schedulingapplication.DomainObjects.AppointmentManager;



public class AppointmentPageController {

   @FXML
   private Customer selectedCustomer; 
   private DatePicker datePicker;
   private javafx.scene.control.ComboBox selectedAppointmentComboBox;
   private Appointment selectedAppointment;
   private AppointmentManager appointmentManager = new AppointmentManager();
   
    public void initialize(Customer customer, Appointment appointment) {
        selectedCustomer = customer;
        selectedAppointment = appointment;
        
        
    }   
    
    public void datePickerButtonHandler(){
        LocalDate currentDate = datePicker.getValue();
        ChronoLocalDate chronDate = datePicker.getChronology().date(currentDate);
        System.out.println("Today's Date is: " + chronDate);
    }
    
    public void getAppointmentDates() throws SQLException{
        Connection con = null;
        Statement stmt = null;
        try {
                stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("select * from appointment");

                while (rs.next()) {
                    appointmentManager.addAppointment(new Appointment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),
                            rs.getString(6),rs.getString(7),rs.getDate(8),rs.getDate(9)));
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
                
        java.util.Date date = new Date();
        stmt.setTimestamp(columnIndex, new java.sql.Timestamp(date.getTime()).getTime());
    }
}
