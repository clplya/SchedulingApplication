package schedulingapplication;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import schedulingapplication.Dao.DBReportsDao;

public class ApptTypesPerMonthReportController {

    @FXML
    CategoryAxis xAxis = new CategoryAxis();
    @FXML
    NumberAxis yAxis = new NumberAxis();
    @FXML
    private BarChart apptTypesBarChart;
    @FXML
    private ObservableList<String> monthsChart = FXCollections.observableArrayList();

    private DBReportsDao db;

    public void initialize() {
        db.selectApptTypesPerMonth();
        apptTypesBarChart.setData(monthsChart);
    }

}
