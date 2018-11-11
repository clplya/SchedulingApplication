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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulingapplication.Dao.DBAddressDao;
import schedulingapplication.Dao.DBCustomerDao;
import schedulingapplication.DomainObjects.Address;
import schedulingapplication.DomainObjects.Customer;

public class CustomerPageController implements Initializable {

    @FXML
    private Button exitButton;
    @FXML
    private Button addCustomerButton;
    @FXML
    private Button customerAppointmentButton;
    @FXML
    private Button viewReportsButton;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<Address> addressList = FXCollections.observableArrayList();
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
    public void addAppointmentButtonHandler(ActionEvent event) throws IOException {
        stage = (Stage) addCustomerButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddAppointmentPage.fxml"));

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
        if (customerTableView.getSelectionModel().getSelectedItem() != null) {
            selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("AppointmentPage.fxml"));
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);

            AppointmentPageController controller = loader.getController();
            controller.initialize(selectedCustomer);

            Stage window = (Stage) (customerAppointmentButton.getScene().getWindow());
            window.setScene(tableViewScene);
            window.show();
        } else { //Does not work - fix
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Customer Selected");
            alert.setHeaderText("Please Select a customer");
            alert.setContentText("Select a Customer prior to checking their appointments");
        }
    }

    @FXML
    public void viewReportButtonHandler(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ReportsPage.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);

//        AppointmentPageController controller = loader.getController();
//        controller.initialize(selectedCustomer);
        stage = (Stage) viewReportsButton.getScene().getWindow();
        stage.setScene(tableViewScene);
        stage.show();
    }
}
