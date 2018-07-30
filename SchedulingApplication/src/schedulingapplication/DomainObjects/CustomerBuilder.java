//package schedulingapplication.DomainObjects;
//
///**
// *
// * @author clply_000
// */
//import java.util.*;
//
//public class CustomerBuilder {
//    private int customerId;
//    private String customerName;
//    private String address;
//    private String phone;
//    
//    public CustomerBuilder setCustomerId(int customerId){
//        this.customerId = customerId;
//        return this;
//    }
//    
//    public CustomerBuilder setCustomerName(String customerName){
//        this.customerName = customerName;
//        return this;
//    }
//    
//    public CustomerBuilder setAddress(String address){
//        this.address = address;
//        return this;
//    }
//    
//    public CustomerBuilder setPhone(String phone){
//        this.phone = phone;
//        return this;
//    }
//    
//    public Customer build(int customerId, String customerName, String address, String phone) {
//        return new Customer(customerId,customerName,address,phone);
//    }
//    public Customer build() {
//        return new Customer();
//    }
//}
