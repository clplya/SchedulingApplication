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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class CustomerPageController extends AnchorPane implements Initializable {
    
    @FXML
    private TableView<String> customerTableView;
    @FXML
    private TableColumn<String, String> nameColumn;
    @FXML
    private TableColumn<String, String> addressColumn;
    
            

            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        Map customerMap = null;
        try {
            customerMap = tableViewSet();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        customerTableView.setItems((ObservableList<String>) customerMap);
        //tableView.setItems(Inventory.getAllParts());

    }    
    
    public Map tableViewSet() throws SQLException{
        Connection conn = null;
        Statement stmt = null;
        String query = "select * from customer";
        stmt = conn.createStatement();
        
        Map<Integer,String> customerInfo = new HashMap<>();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) {
                int customerId = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                customerInfo.put(customerId, query);
            }
            System.out.println(customerInfo);
            return customerInfo;
        //conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03xBv");
        //System.out.println(conn);
        
        
        
    }
    
   
}
