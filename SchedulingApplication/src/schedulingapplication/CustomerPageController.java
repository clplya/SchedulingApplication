package schedulingapplication;

import java.net.URL;
import java.sql.Connection;
import java.lang.String;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CustomerPageController implements Initializable  {
    
    @FXML
    private TableView customerTableView;
//    @FXML
//    private TableColumn<String, String> nameColumn = new TableColumn<>("Name");
//    @FXML
//    private TableColumn<String, String> addressColumn = new TableColumn<>("Address");
    final String key = "name";
    @FXML
    private TableColumn<?, Integer> customerIDColumn = new TableColumn("Customer ID");
    @FXML
    private TableColumn<?, String> nameColumn = new TableColumn("Name");
    @FXML
    private TableColumn<?, String> addressColumn = new TableColumn("Address");
    @FXML
    private TableColumn<?, String> phoneNumberColumn = new TableColumn("Phone Number");
    @FXML
    private ArrayList customerFinalList;
    @FXML
    private ArrayList customerList;
    @FXML
    private ObservableList<String> nameList = FXCollections.observableArrayList();
    @FXML
    private javafx.scene.control.Button exitButton;

               
   @Override
    public void initialize(URL url, ResourceBundle rb) {
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameColumn"));
//        addressColumn.setCellValueFactory(new PropertyValueFactory<>("addressColumn"));
//        
//        Map customerMap = null;
//        try {
//            customerMap = tableViewSet();
//        } catch (SQLException ex) {
//            Logger.getLogger(CustomerPageController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        nameList.set(1, "");
//        customerTableView.setItems((ObservableList<String>) customerMap);
            try{
                customerList = tableViewSet();    
            }
            catch (SQLException ex){
                System.out.println(ex);
            }
            
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("nameColumn"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
            phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("Phone Number"));
            
            customerFinalList.addAll(customerList);
           // TableView table = new TableView(nameList);
         //   customerTableView.setItems(nameList);
            customerTableView.getColumns().setAll(customerFinalList);

            }    
        
    private ArrayList tableViewSet() throws SQLException{
        Connection conn = JDBCConnection.getConnection();
        Statement stmt = conn.createStatement();
        String query = "select * from customer";
        
        ArrayList customerInfo = new ArrayList<>();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                int customerId = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                customerInfo.add(1,customerId);
                customerInfo.add(2,customerName);
                customerInfo.add(3,address);
                customerInfo.add(4,phoneNumber);
            }
            System.out.println(customerInfo);
            return customerInfo;
        //conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03xBv");
        //System.out.println(conn);
        
        
        
    }
    
    @FXML
    private void exitButtonHandler(){
    Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
}
    
   
}
