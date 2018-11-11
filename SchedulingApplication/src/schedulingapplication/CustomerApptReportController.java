package schedulingapplication;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import schedulingapplication.Dao.DBAppointmentDao;
import schedulingapplication.Dao.DBReportsDao;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;

public class CustomerApptReportController {

    @FXML
    private Button cancelButton;
    @FXML
    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    @FXML // fx:id="appointmentTableView"
    private TableView<Appointment> appointmentTableView;
    @FXML // fx:id="descriptionColumn"
    private TableColumn<Appointment, String> AppointmentDescriptionColumn;
    @FXML // fx:id="apptStartTimeColumn"
    private TableColumn<Appointment, LocalDateTime> AppointmentStartTimeColumn;
    @FXML // fx:id="apptStopTimeColumn"
    private TableColumn<Appointment, LocalDateTime> AppointmentStopTimeColumn;
    @FXML // fx:id="locationColumn"
    private TableColumn<Appointment, String> LocationColumn;
    @FXML
    private ComboBox customerBox = new ComboBox();
    @FXML
    private final DBReportsDao dbReports = new DBReportsDao();
    @FXML
    private final DBAppointmentDao dbAppointments = new DBAppointmentDao();

    public void CustomerApptReportController() {
        AppointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        AppointmentStartTimeColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        AppointmentStopTimeColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        LocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
    }

    public void initialize() throws IOException {
        ObservableList<String> users = FXCollections.observableArrayList();
        appointmentTableView.setItems(null);
        appointmentList.clear();

        if (customerBox.getItems().isEmpty()) {
            users.addAll(dbReports.selectAllUsersNames());
            customerBox.getItems().addAll(users);
            appointmentTableView.setItems(dbAppointments.getAllAppointments());
        }
    }

    public void customerBoxEventHandler() {
        Customer selectedCustomer = (Customer) customerBox.getSelectionModel().getSelectedItem();
        appointmentList.clear();
        appointmentList.addAll(dbAppointments.getAppointmentsByCustomer(selectedCustomer.getCustomerId()));
    }

    public void cancelButtonHandler(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel");
        alert.setHeaderText("You will lose all progress on this screen");
        alert.setContentText("Are you sure you want to cancel?");

        Optional<ButtonType> window = alert.showAndWait();
        if (window.get() == ButtonType.OK) {
            loadScene(event);

        } else if (window.get() == ButtonType.CANCEL) {
            alert.close();
        }
    }

    @FXML
    private void loadScene(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        if (event.getSource() == cancelButton) {
            stage = (Stage) cancelButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
        } else {
            stage = null;
            root = null;
            System.out.println("Redirect Failed");
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
