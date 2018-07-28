package schedulingapplication.DomainObjects;

import schedulingapplication.DAO;
import java.sql.*;

/**
 *
 * @author Mark Pilkington
 */
public class AddressDAO implements DAO {
    private Address createAddress(ResultSet rs) {
        Address a = new Address();
        try {
            a.setAddressId(rs.getInt("addressId"));
            a.setAddress(rs.getString("address"));
            a.setAddress2(rs.getString("address2"));
            a.setCityId(rs.getInt("cityId"));
            a.setPostalCode(rs.getString("zip"));
            a.setPhone(rs.getString("phone"));
        } catch(SQLException ex) {            
        }
        return a;
    }
}
