package schedulingapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ReportsPageController implements Initializable {

    @FXML
    private Button generateReportsButton;
    @FXML
    private ComboBox reportComboBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void chooseReport(ActionEvent event) {
        ObservableList<String> reports = FXCollections.observableArrayList();
        if (reportComboBox.getItems().isEmpty()) {
            reports.addAll("Appointment Types Per Month",
                    "Consultants Schedule",
                    "Customer Appointments");
            reportComboBox.getItems().addAll(reports);

        }
        if (event.getTarget().toString().equals(reports.get(0))) {
            System.out.println("Works");
        }
    }

    public void generateReports(ActionEvent event) {

    }

}
