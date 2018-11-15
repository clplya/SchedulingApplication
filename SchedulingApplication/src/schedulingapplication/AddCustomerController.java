package schedulingapplication;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulingapplication.Dao.DBCustomerDao;
import schedulingapplication.DomainObjects.Customer;

public class AddCustomerController {

    @FXML
    private Button exitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addCustomerButton;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TableView<Customer> customerTableView;
    @FXML
    private TableColumn CustomerIDColumn;
    @FXML
    private TableColumn NameColumn;
//    @FXML
//    private TableColumn AddressColumn;
//    @FXML
//    private TableColumn PhoneNumberColumn;
    private final AtomicInteger GENERATEDCUSTOMERID = new AtomicInteger(2);
    private final AtomicInteger GENERATEDADDRESSID = new AtomicInteger(3);

    private DBCustomerDao dbCustomer = new DBCustomerDao();
    @FXML
    private ObservableList<Customer> customerList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        //   AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address1"));
        //  PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        customerList.addAll(dbCustomer.getAllCustomers());
        customerTableView.getItems().addAll(customerList);
    }

    @FXML
    void cancelButtonHandler(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("You will lose all progress on this screen");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> window = alert.showAndWait();
        if (window.get() == ButtonType.OK) {
            stage = (Stage) cancelButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (window.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    public void addCustomerButtonHandler(ActionEvent event) throws IOException {
        int customerID = GENERATEDCUSTOMERID.incrementAndGet();
        String customerName = customerNameTextField.getText();
        int addressID = GENERATEDADDRESSID.incrementAndGet();
        int active = 1;
//        String address = customerAddressTextField.getText();
//        String phone = customerPhoneNumberTextField.getText();
//
//        int newCustomerID = Integer.parseInt(customerID);
//        int addressID = Integer.parseInt(addressId);
        dbCustomer.addCustomer(customerID, customerName, addressID, active);
    }

    public void exitButtonHandler(ActionEvent event) throws IOException {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
