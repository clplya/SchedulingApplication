package schedulingapplication.DomainObjects;

import java.sql.*;
import schedulingapplication.DAO;

/**
 *
 * @author Mark Pilkington
 */
public class CustomerDAO implements DAO{
    private Customer createCustomer(ResultSet rs) {
        Customer c = new Customer();
        try {
            c.setCustomerId(rs.getInt("customerId"));
            c.setCustomerName(rs.getString("customerName"));
            c.setAddressId(rs.getInt("addressId"));
            c.setActive(rs.getInt("active"));            
        } catch(Exception ex) {        
        }
        return c;
    }
}
