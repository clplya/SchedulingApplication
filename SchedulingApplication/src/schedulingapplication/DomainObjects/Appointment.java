package schedulingapplication.DomainObjects;

import java.time.LocalDateTime;

public class Appointment {

    private int appointmentId;
    private int customerId;
    private String title;
    private String description;
    private String location;
    private String contact;
    private String URL;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Appointment(int appointmentId, int customerId, String title, String description, String location, String contact, String URL, LocalDateTime startDate, LocalDateTime endDate) {
        this.appointmentId = appointmentId;
        this.customerId = customerId;
        this.title = title;
        this.description = description;
        this.location = location;
        this.contact = contact;
        this.URL = URL;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Appointment() {

    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

}
