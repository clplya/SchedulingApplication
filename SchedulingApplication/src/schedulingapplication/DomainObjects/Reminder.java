package schedulingapplication.DomainObjects;

import java.time.LocalDateTime;

public class Reminder {

    private int reminderId;
    private LocalDateTime reminderDateTime;
    private int snoozeIncrement;
    private int snoozeIncrementTypeId;
    private int appointmentId;
    private String reminderCol;

    public Reminder(int reminderId, LocalDateTime reminderDateTime,
            int snoozeIncrement, int snoozeIncrementTypeId,
            int appointmentId, String reminderCol) {
        this.reminderId = reminderId;
        this.reminderDateTime = reminderDateTime;
        this.snoozeIncrement = snoozeIncrement;
        this.snoozeIncrementTypeId = snoozeIncrementTypeId;
        this.appointmentId = appointmentId;
        this.reminderCol = reminderCol;
    }

    public int getReminderId() {
        return reminderId;
    }

    public void setReminderId(int reminderId) {
        this.reminderId = reminderId;
    }

    public LocalDateTime getReminderDateTime() {
        return reminderDateTime;
    }

    public void setReminderDateTime(LocalDateTime reminderDateTime) {
        this.reminderDateTime = reminderDateTime;
    }

    public int getSnoozeIncrement() {
        return snoozeIncrement;
    }

    public void setSnoozeIncrement(int snoozeIncrement) {
        this.snoozeIncrement = snoozeIncrement;
    }

    public int getSnoozeIncrementTypeId() {
        return snoozeIncrementTypeId;
    }

    public void setSnoozeIncrementTypeId(int snoozeIncrementTypeId) {
        this.snoozeIncrementTypeId = snoozeIncrementTypeId;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getReminderCol() {
        return reminderCol;
    }

    public void setReminderCol(String reminderCol) {
        this.reminderCol = reminderCol;
    }
}
