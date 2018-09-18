package schedulingapplication.Dao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import schedulingapplication.DomainObjects.Appointment;

public interface IAppointmentDao {

    public void addAppointment(int appointmentId, int customerId, String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end);

    public void addNewAppointment(Appointment appointment);

    public void deleteAppointment(int deletedAppointmentId);

    public ArrayList<Appointment> getAllAppointments();

    public Appointment getAppointment(int appointmentId);

    public ArrayList getAppointmentsByCustomer(int customerId);

    public void updateAppointmentTitle(int upAppointmentId, String upAppointmentTitle);
}
