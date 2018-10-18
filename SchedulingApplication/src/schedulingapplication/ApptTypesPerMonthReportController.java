package schedulingapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import schedulingapplication.DAO.DBReportsDao;

public class ApptTypesPerMonthReportController implements Initializable {

    @FXML
    CategoryAxis xAxis = new CategoryAxis();
    @FXML
    NumberAxis yAxis = new NumberAxis();
    @FXML
    private BarChart apptTypesBarChart;
    @FXML
    private ObservableList<String> monthsChart = FXCollections.observableArrayList();

    private DBReportsDao db;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db.selectApptTypesPerMonth();
        apptTypesBarChart.setData(monthsChart);
    }

}
