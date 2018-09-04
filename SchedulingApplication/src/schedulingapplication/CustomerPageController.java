package schedulingapplication;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import schedulingapplication.Dao.DBCustomerDao;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;
import schedulingapplication.DomainObjects.CustomerData;
import schedulingapplication.DomainObjects.User;

public class CustomerPageController implements Initializable {

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
    @FXML
    private javafx.scene.control.Button exitButton;
    @FXML
    private javafx.scene.control.Button addCustomerButton;
    @FXML
    private javafx.scene.control.Button customerAppointmentButton;
    @FXML
    private ObservableList<Customer> allCustomers = FXCollections.observableArrayList();

    private Customer customer;
    private Connection conn;
    private Statement stmt;
    private static Customer selectedCustomer;
    private DBCustomerDao dbCustomer = new DBCustomerDao();
    private CustomerData customerData;
    private User loggedUser;
    Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        System.out.println("Setting & Adding all Customers");

        allCustomers.add(dbCustomer.getAllCustomers());
//        allCustomers.addAll(dbCustomer.getAllCustomers());
//        customerTableView.setItems(allCustomers);
        //viewCustomerTable();
    }

    @FXML
    private void viewCustomerTable() throws SQLException {
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        customerTableView.getItems().clear();

        //customerTableView.setItems(null);
//        customerTableView.getItems().addAll(dbCustomer.getAllCustomers());
//        System.out.println("Getting & Adding all Customers:" + customerTableView);
//
//        customerTableView.getItems().clear();
        customerTableView.setItems(allCustomers);
        System.out.println("Setting & Adding all Customers" + customerTableView);

//        try {
//            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
//            ResultSet rs = stmt.executeQuery();
//
//            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                customerTableView.getColumns().addAll(col);
//            }
//            while (rs.next()) {
//                customerManager.addCustomer(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
//            }
//            customerTableView.setItems(customerManager.getAllCustomers());
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
    }

    @FXML
    public void addCustomerButtonHandler() throws IOException {
        Stage stage;
        Parent root;

        stage = (Stage) addCustomerButton.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void exitButtonHandler() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void customerAppointmentButtonHandler(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AppointmentPage.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        selectedCustomer = (Customer) customerTableView.getSelectionModel().getSelectedItem();
        Appointment selectedAppointment = new Appointment(selectedCustomer.getCustomerId(), 0, null, null, null, null, null, null, null);

        AppointmentPageController controller = loader.getController();
        controller.initialize(selectedCustomer);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

//        Stage stage;
//        Parent root;
//
//        stage = (Stage) addCustomerButton.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("AppointmentPage.fxml"));
//
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();
//    }
    }
}
