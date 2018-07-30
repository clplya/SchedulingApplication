//package schedulingapplication.DomainObjects;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import javafx.collections.ObservableList;
//import schedulingapplication.DAO;
//import static schedulingapplication.DAO.DB_URL;
//import static schedulingapplication.DAO.DRIVER;
//import static schedulingapplication.DAO.PASS;
//import static schedulingapplication.DAO.USER;
//
///**
// *
// * @author Mark Pilkington
// */
//public class CustomerDAO implements DAO {
//
//    protected List<Customer> customers = new ArrayList<Customer>();
//    private String sql = "select c.customerId,c.customerName,a.address ,a.phone from address a "
//                      + "join customer c on c.addressId = a.addressId";
//
//    private Customer createCustomer(ResultSet rs) {
//        Customer c = new Customer();
//        try {
//            c.setCustomerId(rs.getInt("customerId"));
//            c.setCustomerName(rs.getString("customerName"));
//            c.setAddress(rs.getString("address"));
//            c.setPhone(rs.getString("phone"));
//        } catch (Exception ex) {
//        }
//        return c;
//    }
//
//    public List<Customer> getCustomers() {
//        try {
//            Class.forName(DRIVER);
//            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            while (rs.next()) {
//                Customer c = createCustomer(rs);
//                customers.add(c);
//            }
//            rs.close();
//            conn.close();
//        } catch (ClassNotFoundException | SQLException ex) {
//        }
//        System.out.println(customers);
//        return customers;
//    }
//}
