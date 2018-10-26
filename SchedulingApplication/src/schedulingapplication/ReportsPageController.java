package schedulingapplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ReportsPageController implements Initializable {

    @FXML
    private Button viewReportButton;
    @FXML
    private ComboBox reportComboBox;
    private Stage stage;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> reports = FXCollections.observableArrayList();
        if (reportComboBox.getItems().isEmpty()) {
            reports.addAll("Appointment Types Per Month",
                    "Consultants Schedule",
                    "Customer Appointments");
            reportComboBox.getItems().addAll(reports);
        }
    }

    @FXML
    public void chooseReport() {

    }

    @FXML
    public void viewReport(ActionEvent event) throws IOException {
        if (reportComboBox.getValue().equals("Appointment Types Per Month")) {

            stage = (Stage) viewReportButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("ApptTypesPerMonthReport.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
