/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package schedulingapplication.DomainObjects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author mpilkin1
 */
public class CustomerManager {

    private final static ObservableList<Customer> customerList = FXCollections.observableArrayList();

    public ObservableList<Customer> getAllCustomers() {
        return customerList;
    }
    
    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
}
