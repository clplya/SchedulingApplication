package schedulingapplication;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedulingapplication.Dao.DBReportsDao;
import schedulingapplication.DomainObjects.Appointment;

public class ConsultantScheduleReportController implements Initializable {

    @FXML
    private TableView<Appointment> apptTable;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, Date> apptStartTimeColumn;
    @FXML
    private TableColumn<Appointment, Date> apptStopTimeColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private ComboBox consultantComboBox;
    @FXML
    private ObservableList<Appointment> consultantList = FXCollections.observableArrayList();
    private DBReportsDao dbReports;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("descriptionColumn"));
        apptStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("apptStartTimeColumn"));
        apptStopTimeColumn.setCellValueFactory(new PropertyValueFactory<>("apptStopTimeColumn"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("locationColumn"));

        System.out.println("Setting & Adding all Customers");

        consultantComboBox.setItems(null);
        consultantList.addAll(loadAllConsultants());
        consultantComboBox.setItems(consultantList);
        //  consultantList = consultantComboBox.getItems();

        apptTable.setItems(null);
    }

    @FXML
    public ObservableList loadAllConsultants() {
        consultantList.addAll(dbReports.selectAllConsultantNames());

        return consultantList;
    }

    @FXML
    public void comboBoxEventHandler(ActionEvent event) {

        apptTable.setItems(dbReports.selectConsultantsAppointments((String) consultantComboBox.getValue()));
    }

}
