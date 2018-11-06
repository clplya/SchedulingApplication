package schedulingapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.Dao.DBReportsDao;
import schedulingapplication.DomainObjects.Appointment;

public class ConsultantScheduleReportController {

    @FXML
    private TableView<Appointment> apptTable;
    @FXML
    private TableColumn<Appointment, String> descriptionColumn;
    @FXML
    private TableColumn<Appointment, String> apptStartTimeColumn;
    @FXML
    private TableColumn<Appointment, String> apptStopTimeColumn;
    @FXML
    private TableColumn<Appointment, String> locationColumn;
    @FXML
    private TableColumn<Appointment, String> consultantColumn;
    @FXML
    private TableView<String> consultantTableView;
    @FXML
    private ObservableList<String> consultantList = FXCollections.observableArrayList();

    private DBReportsDao dbReports;
    private DBAppointmentDao dbAppointments;

    public void initialize() {

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("apptStartTime"));
        apptStopTimeColumn.setCellValueFactory(new PropertyValueFactory<>("apptStopTime"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        consultantColumn.setCellValueFactory(new PropertyValueFactory<>("consultant"));

        // consultantTableView.getItems().add(null);
        //consultantList.addAll(dbReports.selectAllConsultantNames());
        //   System.out.println(dbReports.selectAllConsultantNames());
        consultantTableView.setItems(dbReports.selectAllConsultantNames());
    }

//    @FXML
//    public void comboBoxEventHandler(ActionEvent event) {
//        consultantComboBox.setItems(null);
//
//        consultantList.addAll(dbReports.selectAllConsultantNames());
//        consultantComboBox.getItems().addAll(consultantList);
//
//        apptTable.setItems(dbReports.selectConsultantsAppointments((String) consultantComboBox.getValue()));
//    }
}
