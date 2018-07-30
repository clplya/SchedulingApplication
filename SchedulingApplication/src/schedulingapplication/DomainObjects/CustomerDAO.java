package schedulingapplication.DomainObjects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
 
import java.sql.*;
import schedulingapplication.DAO;
import schedulingapplication.JDBCConnection;

/**
 *
 * @author Mark Pilkington
 */
public class CustomerDAO implements DAO {

public static ObservableList<Customer> searchCustomers() throws SQLException, ClassNotFoundException {
    String selectQuery = DAO.sql;
    
    try{
        ResultSet rs = JDBCConnection.dbQuery(selectQuery);
        
        ObservableList<Customer> customerList = getCustomerList(rs);
        return customerList;
    } catch (SQLException e){        
        System.out.println("Query has failed:" + e);
        throw e;
    }    
}

public static ObservableList<Customer> getCustomerList(ResultSet rs) throws SQLException, ClassNotFoundException {
    ObservableList<Customer> customerList = FXCollections.observableArrayList();
    
    while(rs.next()){
        Customer c = new Customer();
            c.setCustomerId(rs.getInt("customerId"));
            c.setCustomerName(rs.getString("customerName"));
            c.setAddress(rs.getString("address"));
            c.setPhone(rs.getString("phone"));
            
            customerList.add(c);
        } return customerList;
    }   
}