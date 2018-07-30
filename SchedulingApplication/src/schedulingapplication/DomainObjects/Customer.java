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
    private IntegerProperty customerIdProperty;
    private StringProperty customerNameProperty;
    private StringProperty addressProperty;
    private StringProperty phoneProperty;
   
    public void Customer(){
    this.customerIdProperty = new SimpleIntegerProperty();
    this.customerNameProperty = new SimpleStringProperty();
    this.addressProperty = new SimpleStringProperty();
    this.phoneProperty = new SimpleStringProperty();
    }   

    public int getCustomerId() {
        return customerIdProperty.get();
    }

    public void setCustomerId(int customerId) {
        this.customerIdProperty.set(customerId);
    }

    public IntegerProperty customerIdProperty() {
        return this.customerIdProperty;
    }
    
    public String getCustomerName() {
        return customerNameProperty.get();
    }

    public void setCustomerName(String customerName) {
        this.customerNameProperty.set(customerName);
    }
    
    public StringProperty customerNameProperty() {
        return this.customerNameProperty;
    }

    public String getAddress() {
        return addressProperty.get();
    }

    public void setAddress(String address) {
        this.addressProperty.set(address);
    }
    
    public StringProperty addressProperty() {
        return this.addressProperty;
    }

//    public int getActive() {
//        return active;
//    }
//
//    public void setActive(int active) {
//        this.active = active;
//    }    

    public String getPhone() {
        return phoneProperty.get();
    }

    public void setPhone(String phone) {
        this.phoneProperty.set(phone);
    }
    
    public StringProperty phoneProperty() {
        return this.phoneProperty;
    }
}