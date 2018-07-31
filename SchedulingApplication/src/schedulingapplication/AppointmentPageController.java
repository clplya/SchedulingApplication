package schedulingapplication;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import schedulingapplication.DomainObjects.Customer;


public class AppointmentPageController {

   @FXML
   private Customer selectedCustomer; 
   private DatePicker datePicker;
   
    public void initialize(Customer customer) {
        selectedCustomer = customer;
        
    }   
    
    public void datePickerButtonHandler(){
        LocalDate currentDate = datePicker.getValue();
        ChronoLocalDate chronDate = datePicker.getChronology().date(currentDate);
        System.out.println("Today's Date is: " + chronDate);
    }
}
