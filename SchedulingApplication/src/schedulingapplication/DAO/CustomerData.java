package schedulingapplication.DAO;

public class CustomerData {

    private int customerId;
    private String customerName;
    private String address;
    private String phone;

    public CustomerData(int customerId, String customerName, String address, String phone) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
