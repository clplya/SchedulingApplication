package schedulingapplication.Dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javafx.collections.ObservableList;
import schedulingapplication.DomainObjects.Appointment;

public interface IAppointmentDao {

    public void addAppointment(int appointmentId, int customerId, String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end);

    public void addNewAppointment(Appointment appointment);

    public void deleteAppointment(int deletedAppointmentId);

    public ObservableList getAllAppointments();

    public Appointment getAppointment(int appointmentId);

    public ObservableList getAppointmentsByCustomer(int customerId);

    public void updateAppointmentTitle(int upAppointmentId, String upAppointmentTitle);

    public void updateAppointment(int apptId, int customerId, String apptmentTitle,
            String apptmentDesc, String apptLocation, String contact, String url,
            LocalDate startDate, LocalDate endDate);
}
