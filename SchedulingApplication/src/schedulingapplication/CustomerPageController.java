package schedulingapplication;

import java.net.URL;
import java.sql.Connection;
import java.lang.String;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class CustomerPageController implements Initializable  {
    
    @FXML
    private TableView customerTableView;
//    @FXML
//    private TableColumn<String, String> nameColumn = new TableColumn<>("Name");
//    @FXML
//    private TableColumn<String, String> addressColumn = new TableColumn<>("Address");
    final String key = "name";
    @FXML
    private TableColumn<Map, String> customerIDColumn = new TableColumn("Customer ID");
    @FXML
    private TableColumn nameColumn;
    @FXML
    private Map customerList;
    @FXML
    private ObservableList nameList = FXCollections.observableArrayList();
    
        

            
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


            //TableColumn<Map, String> customerIDColumn = new TableColumn("Customer ID");
            customerIDColumn.setCellValueFactory(new MapValueFactory<>("customerID"));
            
            nameColumn.setCellValueFactory(new MapValueFactory<>("nameColumn"));
            
            TableView table = new TableView(nameList);
            customerTableView.setItems(nameList);
            customerTableView.getColumns().setAll(customerIDColumn);

            }    
        
    private Map tableViewSet() throws SQLException{
        Connection conn = JDBCConnection.getConnection();
        Statement stmt = conn.createStatement();
        String query = "select * from customer";
        
        Map<Integer,String> customerInfo = new HashMap<>();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                int customerId = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                customerInfo.put(customerId, customerName);
            }
            System.out.println(customerInfo);
            return customerInfo;
        //conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03xBv");
        //System.out.println(conn);
        
        
        
    }
    
   
}
