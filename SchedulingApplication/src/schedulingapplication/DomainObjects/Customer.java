package schedulingapplication.DomainObjects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Mark Pilkington
 */
public class Customer {

    private final SimpleIntegerProperty customerId;
    private final SimpleStringProperty customerName;
    private final SimpleStringProperty address;
    private final SimpleStringProperty phone;

    public Customer(int customerId, String customerName, String address, String phone) {
        this.customerId = new SimpleIntegerProperty(customerId);
        this.customerName = new SimpleStringProperty(customerName);
        this.address = new SimpleStringProperty(address);
        this.phone = new SimpleStringProperty(phone);
    }

    public int getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(int customerId) {
        this.customerId.set(customerId);
    }

    public IntegerProperty customerId() {
        return this.customerId;
    }

    public String getCustomerName() {
        return customerName.get();
    }

    public void setCustomerName(String customerName) {
        this.customerName.set(customerName);
    }

    public StringProperty customerNameProperty() {
        return this.customerName;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return this.address;
    }

//    public int getActive() {
//        return active;
//    }
//
//    public void setActive(int active) {
//        this.active = active;
//    }    
    public String getPhone() {
        return phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty phoneProperty() {
        return this.phone;
    }
}
