package schedulingapplication.Dao;

import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Reminder;

public interface IReminderDao {

    public Reminder getReminderPerAppointment(Appointment appointment);

}
