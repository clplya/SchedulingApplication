package schedulingapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

public class ApptTypesPerMonthReportController implements Initializable {

    @FXML
    final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    final NumberAxis yAxis = new NumberAxis();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
