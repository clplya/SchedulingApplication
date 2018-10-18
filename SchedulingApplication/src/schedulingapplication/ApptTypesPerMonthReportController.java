package schedulingapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import schedulingapplication.DAO.DBReportsDao;

public class ApptTypesPerMonthReportController implements Initializable {

    @FXML
    private CategoryAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private BarChart apptMonthChart;
    @FXML
    private DBReportsDao dbReportsDao;

    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series monthChart = new XYChart.Series<>();
        xAxis.setCategories(dbReportsDao.monthsOfAppointments());
        //   monthChart.getData().add(new XYChart.Data(1, dbReportsDao.monthsOfAppointments()));

        //apptMonthChart.getData().addAll(monthChart);
    }

    public void loadData() {

    }

}
