package schedulingapplication.DomainObjects;

import java.sql.Connection;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AppointmentManager {

    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    Connection conn;
    Statement stmt;
    
    private void connect(){
        try {
                stmt = conn.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("select * from appointment");

                while (rs.next()) {
                    addAppointment(new Appointment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4),rs.getString(5),
                            rs.getString(6),rs.getString(7),rs.getDate(8),rs.getDate(9)));
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
    }
    public ObservableList<Appointment> getAllAppointments() {
        clear();
        
        return appointmentList;
    }
    
    public void addAppointment(Appointment appointment){
        appointmentList.add(appointment);
    }
    
    public void clear (){
        appointmentList.clear();
    }
    
    public ObservableList<Appointment> getCustomerAppointment(int customerId) {
        ObservableList<Appointment> customerAppointment = FXCollections.observableArrayList();
        for(int i=0;i<appointmentList.size();i++){
            if(appointmentList.get(i).getCustomerId() == customerId){
                customerAppointment.add(appointmentList.get(i));
            } 
        }
        return customerAppointment;
    }
}
