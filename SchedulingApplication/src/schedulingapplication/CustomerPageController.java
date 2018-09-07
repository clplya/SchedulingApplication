package schedulingapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulingapplication.Dao.DBAddressDao;
import schedulingapplication.Dao.DBCustomerDao;
import schedulingapplication.DomainObjects.Address;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;

public class CustomerPageController implements Initializable {

    @FXML
    private javafx.scene.control.Button exitButton;
    @FXML
    private javafx.scene.control.Button addCustomerButton;
    @FXML
    private javafx.scene.control.Button customerAppointmentButton;
    @FXML
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Address> addressList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Customer> customerFiltered = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Address> addressFiltered = FXCollections.observableArrayList();
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableView<Address> addressTableView;
    @FXML
    private TableColumn<Customer, String> NameColumn;
    @FXML
    private TableColumn<Address, String> AddressColumn;
    @FXML
    private TableColumn<Address, String> PhoneNumberColumn;

    private static Customer selectedCustomer;
    private DBCustomerDao dbCustomer = new DBCustomerDao();
    private DBAddressDao dbAddress = new DBAddressDao();
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address1"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        System.out.println("Setting & Adding all Customers");

        customerTableView.setItems(null);
        customerTableView.setItems(dbCustomer.getAllCustomers());
        addressTableView.setItems(dbAddress.getAllAddresses());

        customerList = customerTableView.getItems();
        addressList = addressTableView.getItems();
    }

    @FXML
    public void addCustomerButtonHandler() throws IOException {

        stage = (Stage) addCustomerButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exitButtonHandler() {
        stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void customerAppointmentButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AppointmentPage.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
        Appointment selectedAppointment = new Appointment(selectedCustomer.getCustomerId(), 0, null, null, null, null, null, null, null);

        AppointmentPageController controller = loader.getController();
        controller.initialize(selectedCustomer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

        stage = (Stage) addCustomerButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AppointmentPage.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void customerSelectionFilter(ActionEvent event) throws IOException {
        selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();

        customerTableView.setItems(null);

        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address1"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        //final int customerId = selectedCustomer.getCustomerId();
        final int addressId = selectedCustomer.getAddressId();

        customerFiltered.add(selectedCustomer);
        addressFiltered.add(dbAddress.getAddress(addressId));

        customerTableView.setItems(customerFiltered);
        addressTableView.setItems(addressFiltered);
    }
}
