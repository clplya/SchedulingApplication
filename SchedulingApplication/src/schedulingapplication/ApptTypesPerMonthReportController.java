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
    final CategoryAxis xAxis = new CategoryAxis();
    @FXML
    final NumberAxis yAxis = new NumberAxis();
    @FXML
    BarChart apptTypesBarChart;
    DBReportsDao db;
    ObservableList<String> monthsChart = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db.selectApptTypesPerMonth();
        apptTypesBarChart.setData(monthsChart);
    }

}
