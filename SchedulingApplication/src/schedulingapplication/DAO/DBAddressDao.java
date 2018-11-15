package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulingapplication.DomainObjects.Address;

public class DBAddressDao implements IAddressDao {

    //private Address address = new Address();
    private ObservableList<Address> addressList = FXCollections.observableArrayList();

    public DBAddressDao() {
    }

    @Override
    public void addAddress(int addressId, String address, String address2, int cityId, String postalCode, String phone) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "insert into address(addressId,address,address2,cityId,postalCode,phone,createDate,createdBy,lastUpdateBy) values (" + addressId + ",'" + address + "','" + address2 + "'," + cityId + ",'" + postalCode + "','" + phone + "',now(),1,1)";
            int result = stmt.executeUpdate(sql);
            System.out.println("Inserting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAddress(int deletedAddressId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "delete from address where addressId=" + deletedAddressId;
            int result = stmt.executeUpdate(sql);
            System.out.println("Deleting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList<Address> getAllAddresses() {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select addressId,address,address2,cityId,postalCode,phone from address";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int addressId = rs.getInt(1);
                String address1 = rs.getString(2);
                String address2 = rs.getString(3);
                int cityId = rs.getInt(4);
                String postalCode = rs.getString(5);
                String phone = rs.getString(6);

                Address address = new Address(addressId, address1, address2, cityId, postalCode, phone);
                addressList.add(address);
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
        return addressList;
    }

    @Override
    public Address getAddressByID(int addressId) {
        Statement stmt = null;
        int addressID = 0;
        String address1 = "";
        String address2 = "";
        int cityId = 0;
        String postalCode = "";
        String phone = "";

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select addressId,address,address2,cityId,postalCode,phone from address where addressId =" + addressId);

            while (rs.next()) {
                addressID = rs.getInt(1);
                address1 = rs.getString(2);
                address2 = rs.getString(3);
                cityId = rs.getInt(4);
                postalCode = rs.getString(5);
                phone = rs.getString(6);

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
        Address address = new Address(addressID, address1, address2, cityId, postalCode, phone);
        return address;
    }

    @Override
    public void updateAddress(int upAddressId, String upAddress) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String updateSql = null;
            updateSql = "update address set address.address =" + upAddress + " where address.addressId =" + upAddressId;
            stmt.executeUpdate(updateSql);

            String selectSql = ("select addressId,address,address2,cityId,postalCode,phone from address where addressId =" + upAddressId);
            ResultSet rs = stmt.executeQuery(selectSql);

            while (rs.next()) {
                int addressID = rs.getInt(1);
                String address1 = rs.getString(2);
                String address2 = rs.getString(3);
                int cityId = rs.getInt(4);
                String postalCode = rs.getString(5);
                String phone = rs.getString(6);

                Address address = new Address(addressID, address1, address2, cityId, postalCode, phone);
                System.out.println("Updated Address Name: " + address.getAddress1());
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
    }
}
