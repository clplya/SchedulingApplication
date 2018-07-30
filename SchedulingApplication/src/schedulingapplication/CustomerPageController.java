package schedulingapplication;

import java.sql.Connection;
import java.lang.String;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulingapplication.DomainObjects.CustomerDAO;

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
    private ObservableList<ObservableList> customerFinalList = FXCollections.observableArrayList();
    @FXML
    private javafx.scene.control.Button exitButton;

    private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";

    private CustomerDAO customer;
    private Connection conn;
    private Statement stmt;


    public void initialize() {
        // customerFinalList = FXCollections.observableArrayList();
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
        try {
            stmt = con.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(DAO.sql);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {

                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));

                customerTableView.getColumns().addAll(col);
              //  System.out.println("Column [" + i + "] ");
            }

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println(row);
                customerFinalList.add(row);
            }
        } catch (SQLException ex) {

        }
        System.out.println(customerFinalList);
        CustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        
        
        customerTableView.setItems(customerFinalList); 
    }

    @FXML
    private void exitButtonHandler() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
