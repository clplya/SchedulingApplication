package schedulingapplication;

import java.net.URL;
import java.sql.Connection;
import java.lang.String;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

public class CustomerPageController extends Application {

    @FXML
    private TableView customerTableView;
    @FXML
    private TableColumn customerIDColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private TableColumn phoneNumberColumn;
    @FXML
    private ObservableList<ObservableList> customerFinalList = FXCollections.observableArrayList();
    @FXML
    private javafx.scene.control.Button exitButton;

    private final static String url = "jdbc:mysql://52.206.157.109:3306/U03xBv";
    private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
    private final static String user = "U03xBv";
    private final static String password = "53688111925";
    private String query = "select c.customerId,c.customerName,a.address ,a.phone from address a "
                           + "join customer c on c.addressId = a.addressId";

    private Connection conn;
    private Statement stmt;
    private Integer customerId;
    private String customerName;
    private Integer addressId;

    @Override
    public void start(Stage stage) throws Exception {
        // customerFinalList = FXCollections.observableArrayList();
        try {
            Class.forName(jdbcDriver);
            try {
                conn = DriverManager.getConnection(this.url, user, password);
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
            ResultSet rs = stmt.executeQuery(query);

            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                customerTableView.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
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
//        customerIDColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
//        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        customerTableView.setItems(customerFinalList); 
    }

    @FXML
    private void exitButtonHandler() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}
