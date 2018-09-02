package schedulingapplication.Dao;

import java.util.ArrayList;
import schedulingapplication.DomainObjects.Address;

public interface IAddressDao {

    public void addAddress(int addressId, String address, String address2, int cityId, String postalCode, String phone);

    public void deleteAddress(int addressId);

    public ArrayList<Address> getAllAddresses();

    public Address getAddress(int addressId);

    public void updateAddress(int upAddressId, String upAddress);
}
