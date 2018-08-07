package schedulingapplication.DomainObjects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AppointmentManager {

    private final static ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

    public ObservableList<Appointment> getAllAppointment() {
        return appointmentList;
    }
    
    public void addAppointment(Appointment appointment){
        appointmentList.add(appointment);
    }
}
