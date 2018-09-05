package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulingapplication.DomainObjects.Address;
import schedulingapplication.DomainObjects.Customer;

public class DBCustomerDao implements ICustomerDao {

    private Customer customer;
    private ObservableList<Customer> customerList;
    private ObservableList<Map> customerData;
    private Customer[] array = new Customer[3];
    private ArrayList<String> customerArray = new ArrayList<>();
    private ArrayList<String> addressArray = new ArrayList<>();

    public DBCustomerDao() {
        customerList = FXCollections.observableArrayList();
        customer = null;
    }

    @Override
    public void addCustomer(int customerId, String customerName, int addressId, int active) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "insert into customer(customerId,customerName,addressId,active,createdBy,createDate,lastUpdateBy) values (" + customerId + ",'" + customerName + "','" + addressId + "'," + active + ",1,now(),1)";
            int result = stmt.executeUpdate(sql);
            System.out.println("Inserting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteCustomer(int deletedCustomerId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "delete from customer where customerId=" + deletedCustomerId;
            int result = stmt.executeUpdate(sql);
            System.out.println("Deleting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select customerId,customerName,addressId,active from customer";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int customerId = rs.getInt(1);
                String customerName = rs.getString(2);
                int addressId = rs.getInt(3);
                int active = rs.getInt(4);

                customer = new Customer(customerId, customerName, addressId, active);
                customerList.add(customer);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

        }
        return customerList;
    }

    @Override
    public Customer getCustomer(int customerId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select customerId,customerName,addressId,active from customer where customerId =" + customerId);

            while (rs.next()) {
                int customerID = rs.getInt(1);
                String customerName = rs.getString(2);
                int addressId = rs.getInt(3);
                int active = rs.getInt(4);

                customer = new Customer(customerID, customerName, addressId, active);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return customer;

//        for (Customer c : allCustomers) {
//            if (c.getCustomerId() == Id) {
//                return c;
//        }
//        }
//        return null;
    }

    @Override
    public void updateCustomer(int upCustomerId, String upCustomerName) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String updateSql = null;
            updateSql = "update customer set customer.customerName =" + upCustomerName + " where customer.customerId =" + upCustomerId;
            stmt.executeUpdate(updateSql);

            String selectSql = "select customerId,customerName,addressId,active from customer where customer.customerId=" + upCustomerId;
            ResultSet result = stmt.executeQuery(selectSql);

            while (result.next()) {
                int customerId = result.getInt(1);
                String customerName = result.getString(2);
                int addressId = result.getInt(3);
                int active = result.getInt(4);

                customer = new Customer(customerId, customerName, addressId, active);
                System.out.println("Updated Customer Name: " + customer.getCustomerName());
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ObservableList<Map> getCustomerData() throws SQLException {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String dataSql = null;
            dataSql = "select *\n"
                    + "from customer c\n"
                    + "join address ad on c.addressId = ad.addressId";

            ResultSet rs = stmt.executeQuery(dataSql);

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while (rs.next()) {
                int i = 1;
                while (i <= columnCount) {
                    //Data needed
                    int customerId = rs.getInt(1);
                    String customerName = rs.getString(2);
                    int addressId = rs.getInt(3);
                    int active = rs.getInt(4);

                    String address = rs.getString(10);
                    String address2 = rs.getString(11);
                    int cityId = rs.getInt(12);
                    String postalCode = rs.getString(13);
                    String phone = rs.getString(14);

                    Customer customerWithData = new Customer(customerId,
                            customerName, addressId, active);

                    Address customerAddress = new Address(addressId, address, address2,
                            cityId, postalCode, phone);

                    customerData = FXCollections.observableArrayList();
                    Map<Customer, Address> dataRow = new HashMap<>();
                    dataRow.put(customerWithData, customerAddress);

                    customerData.add(dataRow);
                }
            }
        } catch (SQLException ex) {

        }
        return customerData;
    }
}
