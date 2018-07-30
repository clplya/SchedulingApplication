package schedulingapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import schedulingapplication.DomainObjects.Customer;
import schedulingapplication.DomainObjects.CustomerManager;

/**
 * FXML Controller class
 *
 * @author mpilkin1
 */
public class AddCustomerController {

    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button addCustomerButton;
    @FXML
    private TextField customerNameTextField;
    @FXML
    private TextField customerAddressTextField;
    @FXML
    private TextField customerPhoneNumberTextField;
    @FXML
    private TableView customerTableView;
    @FXML
    private TableColumn CustomerIDColumn;
    @FXML
    private TableColumn NameColumn;
    @FXML
    private TableColumn AddressColumn;
    @FXML
    private TableColumn PhoneNumberColumn;
    private static final AtomicInteger GENERATEDCUSTOMERID = new AtomicInteger(2);
    private CustomerManager customerManager = new CustomerManager();

    public void initialize(URL url, ResourceBundle rb) {

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
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        String customerID = Integer.toString(GENERATEDCUSTOMERID.incrementAndGet());
        String customerName = customerNameTextField.getText();
        String address = customerAddressTextField.getText();
        String phone = customerPhoneNumberTextField.getText();

        int newCustomerID;
        newCustomerID = Integer.parseInt(customerID);

        customerManager.addCustomer(new Customer(newCustomerID, customerName, address, phone));
        
        customerTableView.setItems(customerManager.getAllCustomers());
    }

    public void saveButtonHandler(ActionEvent event) throws IOException {

    }
}
