package schedulingapplication.DomainObjects;

/**
 *
 * @author Mark Pilkington
 */
public class Customer {
    public void Customer(){
    }
    
    private int customerId;
    private String customerName;
    private int addressId;
    private int active;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }    
}