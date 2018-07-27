package schedulingapplication;

import java.net.URL;
import java.sql.Connection;
import java.lang.String;
import java.sql.ResultSet;
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

    private ArrayList customerInfo = new ArrayList<>();
    private ObservableList customerFinalList = FXCollections.observableArrayList();
    private ArrayList customerList = new ArrayList<>();
    @FXML
    private ObservableList<String> nameList = FXCollections.observableArrayList();
    @FXML
    private javafx.scene.control.Button exitButton;
    private Connection con;


               
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
            customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<>("addressID"));
          //  phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            
            try{
                //customerList = tableViewSet();    
         //       customerList.addAll(tableViewSet());
            viewCustomerTable(con);
            }
            catch (SQLException ex){
                System.out.println(ex);
            }
            
            
            customerFinalList.addAll(customerList);
           // TableView table = new TableView(nameList);
         //   customerTableView.setItems(nameList);
            customerTableView.getColumns().setAll(customerFinalList);

            }    
        
////    private ArrayList tableViewSet() throws SQLException{
//        Connection conn = JDBCConnection.getConnection();
//        Statement stmt = conn.createStatement();
//        //String query = "select * from customer";
//        
//        
//            ResultSet rs = stmt.executeQuery("SELECT * FROM customer");
//            
//            ResultSetMetaData resultMData = rs.getMetaData();
//            int columnCount = resultMData.getColumnCount();
//            
//            while(rs.next()) {
//                int i = 1;
//                while(i <= columnCount){
//                    customerInfo.add(rs.getArray(i++));
//                }
////                while(rs.next()){
////                int customerId = rs.getInt("customerId");
////                String customerName = rs.getString("customerName");
////                String address = rs.getString("addressId");
////                //String phoneNumber = rs.getString("phoneNumber");
////                customerInfo.set(1,customerId);
////                customerInfo.set(2,customerName);
////                customerInfo.set(3,address);
//               // customerInfo.add(4,phoneNumber);
//            }
//            System.out.println(customerInfo);
//            return customerInfo;
//        //conn = DriverManager.getConnection("jdbc:mysql://52.206.157.109:3306/U03xBv");
//        //System.out.println(conn);
//        
//        
//        
//    }
    
    public static void viewCustomerTable(Connection con) throws SQLException{
        Statement stmt = null;
        
        try{
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT customerID, customerName, addressId FROM customer");
            while (rs.next()){
                Integer customerId = rs.getInt(1);
                String customerName = rs.getString(2);
                Integer addressId = rs.getInt(3);
            System.out.println(customerId + " " + customerName +" "+ addressId);
            
            }
        }
    
        catch(SQLException ex){
             if (stmt != null) { stmt.close(); }
        }
    }
    
    @FXML
    private void exitButtonHandler(){
    Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
}
    
   
}
