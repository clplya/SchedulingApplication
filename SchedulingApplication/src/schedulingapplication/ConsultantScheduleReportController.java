package schedulingapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedulingapplication.Dao.DBReportsDao;


public class ConsultantScheduleReportController implements Initializable {

    @FXML
    public TableView apptTable;
    @FXML
    public TableColumn customerColumn;
    @FXML
    public TableColumn apptStartTimeColumn;
    @FXML
    public TableColumn apptStopTimeColumn;
    @FXML
    public TableColumn LocationColumn;
    @FXML
    public ComboBox consultantComboBox;
    @FXML
    ObservableList consultantList = FXCollections.observableArrayList();
    DBReportsDao dbReports;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customer"));
        apptStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("aptStartTime"));
        apptStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("aptStopTime"));
                LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));

        System.out.println("Setting & Adding all Customers");

        apptTable.setItems(null);
        apptTable.setItems(loadAllConsultants());

        consultantList = apptTable.getItems();
    }

    @FXML
    public ObservableList loadAllConsultants() {
        consultantList.addAll(dbReports.selectAllConsultantNames());

        return consultantList;
    }

    @FXML
    public void comboBoxEventHandler() {

    }

}
