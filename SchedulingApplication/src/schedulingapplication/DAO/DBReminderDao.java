package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Reminder;

public class DBReminderDao implements IReminderDao {

    @Override
    public Reminder getReminderPerAppointment(Appointment appointment) {
        Statement stmt = null;
        int reminderId = 0;
        LocalDateTime localReminderDateTime = LocalDateTime.now();
        int snoozeIncrement = 0;
        int snoozeIncrementTypeId = 0;
        int appointmentId = 0;
        String reminderCol = "";

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select * from reminder r join appointment a on r.appointmentId = a.appointmentId\n"
                    + "where a.appointmentId = '" + appointment.getAppointmentId() + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                reminderId = rs.getInt(1);
                Timestamp reminderDateTime = rs.getTimestamp(2);
                snoozeIncrement = rs.getInt(3);
                snoozeIncrementTypeId = rs.getInt(4);
                appointmentId = rs.getInt(6);
                reminderCol = rs.getString(7);

                localReminderDateTime = reminderDateTime.toLocalDateTime();

            }
        } catch (SQLException ex) {

        }
        Reminder reminder = new Reminder(reminderId, localReminderDateTime, snoozeIncrement, snoozeIncrementTypeId, appointmentId, reminderCol);
        return reminder;
    }
}
