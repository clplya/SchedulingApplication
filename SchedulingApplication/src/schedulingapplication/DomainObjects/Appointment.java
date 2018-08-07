
package schedulingapplication.DomainObjects;

import java.text.SimpleDateFormat;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import java.util.Date;
import java.util.Calendar;
import java.time.*;

public class Appointment {

    private final SimpleIntegerProperty appointmentId;
    private final SimpleIntegerProperty customerId;
    private final SimpleStringProperty title;
    private final SimpleStringProperty description;
    private final SimpleStringProperty location;
    private final SimpleStringProperty contact;
    private final SimpleStringProperty URL;
    private final Date startDate;
    private final Date endDate;
    Calendar cal = Calendar.getInstance();
   SimpleDateFormat date;
    
    public Appointment(int appointmentId, int customerId, String title, String description, String location,
        String contact, String URL, Date startDate, Date endDate){
        this.appointmentId = new SimpleIntegerProperty(appointmentId);
        this.customerId = new SimpleIntegerProperty(customerId);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.location = new SimpleStringProperty(location);
        this.contact = new SimpleStringProperty(contact);
        this.URL = new SimpleStringProperty(URL);
        cal.getTime();
        this.startDate = cal.getTime();
        this.endDate = new Date();
    }

    public int getAppointmentId() {
        return appointmentId.get();
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId.set(appointmentId);
    }

    public IntegerProperty appointmentId() {
        return this.appointmentId;
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public IntegerProperty customerIdProperty() {
        return this.customerId;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return this.title;
    }

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return this.description;
    }
    
    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public StringProperty locationProperty() {
        return this.location;
    }
    
    public String getContact() {
        return contact.get();
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public StringProperty contactProperty() {
        return this.contact;
    }
    
    public String getURL() {
        return URL.get();
    }

    public void setURL(String URL) {
        this.URL.set(URL);
    }

    public StringProperty URLProperty() {
        return this.URL;
    }
 
    public Date getStartDate() {
        return startDate.getTime();
    }
    
  
}
