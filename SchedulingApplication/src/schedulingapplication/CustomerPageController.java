package schedulingapplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.*;
import java.lang.String;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerPageController implements Initializable {

    @FXML
    private TableView customerTableView;
    @FXML
    private TableColumn<?, Integer> customerIDColumn = new TableColumn("Customer ID");
    @FXML
    private TableColumn<?, String> nameColumn = new TableColumn("Name");
    @FXML
    private TableColumn<?, String> addressColumn = new TableColumn("Address");
    @FXML
    private TableColumn<?, String> phoneNumberColumn = new TableColumn("Phone Number");
    @FXML
    private ArrayList customerInfo = new ArrayList<>();
    @FXML
    private ObservableList customerFinalList = FXCollections.observableArrayList();
    private ArrayList customerList = new ArrayList<>();
    @FXML
    private ObservableList<String> nameList = FXCollections.observableArrayList();
    @FXML
    private javafx.scene.control.Button exitButton;

    private final static String url = "jdbc:mysql://52.206.157.109:3306/U03xBv";
    private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private final static String user = "U03xBv";
    private final static String password = "53688111925";
    Connection conn;
    
    Integer customerId;
    String customerName;
    Integer addressId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Statement stmt = null;

        try {
            Class.forName(jdbcDriver);
        try{
        conn = DriverManager.getConnection(this.url, user, password);
        stmt = conn.createStatement();
        
        viewCustomerTable(conn);
    }  catch(SQLException ex){
       System.out.println("Failed to create the DB connection.");
    }
   } catch (ClassNotFoundException ex){
        System.out.println("Driver not found");
    }
        
   }
    
    @FXML
    private void viewCustomerTable(Connection con) throws SQLException{
        Statement stmt;
        
        try{
            stmt = con.createStatement(TYPE_FORWARD_ONLY,CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT customerID, customerName, addressId FROM customer");
            while (rs.next()){
                customerId = rs.getInt(1);
                customerName = rs.getString(2);
                addressId = rs.getInt(3);
            System.out.println(customerId + " " + customerName +" "+ addressId);
            }
        } catch(SQLException ex){
            
        }
            customerFinalList.add(0, customerId);
            customerFinalList.add(1, customerName);
            customerFinalList.add(2, addressId);
            System.out.println();
            System.out.println(customerFinalList);
 
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("addressID"));
            
            TableView table = new TableView(customerFinalList);
           customerTableView.setItems(customerFinalList);
    }
 
    @FXML
    private void exitButtonHandler(){
    Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
}
}