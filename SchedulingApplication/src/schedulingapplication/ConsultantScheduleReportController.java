package schedulingapplication;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.Dao.DBReportsDao;
import schedulingapplication.DomainObjects.Appointment;

public class ConsultantScheduleReportController implements Initializable {

    @FXML
    private TableView<Appointment> appointmentTableView;
    @FXML
    private TableColumn<Appointment, String> DescriptionColumn;
    @FXML
    private TableColumn<Appointment, LocalDate> ApptStartTimeColumn;
    @FXML
    private TableColumn<Appointment, LocalDate> ApptStopTimeColumn;
    @FXML
    private TableColumn<Appointment, String> LocationColumn;
    @FXML
    private TableColumn<Appointment, String> ContactColumn;
    @FXML
    private TableView<Appointment> contactTableView;
    @FXML
    private ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    @FXML
    private ObservableList<String> contactList = FXCollections.observableArrayList();
    @FXML
    private Button showContactsBtn;
    @FXML
    private ComboBox contactBox = new ComboBox();

    private DBReportsDao dbReports;
    private DBAppointmentDao dbAppointments;
    private String contactName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        ApptStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("apptStartTime"));
        ApptStopTimeColumn.setCellValueFactory(new PropertyValueFactory<>("apptStopTime"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));

        //appointmentTableView.setItems(null);
        appointmentList.addAll(dbAppointments.getAllAppointments());
        appointmentTableView.setItems(appointmentList);
    }

//    public void showConsultantsBtnHandler(){
//        //ConsultantTableView.getItems().add(null);
//      //  ConsultantTableView.getItems().add(dbReports.selectAllConsultantNames());
//
////        appointmentList.addAll(dbReports.selectAllConsultantNames());
//        for (int i = 0; i < appointmentList.size(); i++) {
//            consultantName = appointmentList.get(i).getContact();
//            ConsultantTableView.getItems().add(consultantName);
//        }
//
//    }
    @FXML
    public void showContactsBtnHandler(ActionEvent event) throws IOException {
        contactTableView.setItems(appointmentList);
        appointmentList.addAll(getContacts());

        //contactList = contactTableView.getItems();
        // apptTable.setItems(dbReports.selectConsultantsAppointments((String) consultantComboBox.getValue()));
    }

    @FXML
    public ObservableList<Appointment> getContacts() throws IOException {
        ObservableList<Appointment> contact = FXCollections.observableArrayList();
        for (int i = 1; i <= dbAppointments.getAllAppointments().size(); i++) {

            contact.addAll(dbAppointments.getAppointment(i));
        }
        return contact;
    }

}
