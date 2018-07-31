package schedulingapplication;

import java.io.IOException;
import java.sql.Connection;
import java.lang.String;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulingapplication.DomainObjects.Customer;
import schedulingapplication.DomainObjects.CustomerManager;

public class CustomerPageController {

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

    private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private CustomerManager customerManager = new CustomerManager();
    private Customer customer;
    private Connection conn;
    private Statement stmt;
    private static Customer selectedCustomer;
    

    public void initialize() {
        try {
            Class.forName(jdbcDriver);
            try {
                conn = DriverManager.getConnection(DAO.DB_URL, DAO.USER, DAO.PASS);
                stmt = conn.createStatement();
                viewCustomerTable(conn);
            } catch (SQLException ex) {
                System.out.println("Failed to create the DB connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        }

    }

    @FXML
    private void viewCustomerTable(Connection con) throws SQLException {
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        if (customerManager.getAllCustomers().isEmpty()) {

            try {
                stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(DAO.sql);

                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                    customerTableView.getColumns().addAll(col);
                }
                while (rs.next()) {
                    customerManager.addCustomer(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
                customerTableView.setItems(customerManager.getAllCustomers());
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } else {
            customerTableView.setItems(customerManager.getAllCustomers());
        }
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
