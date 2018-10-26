package schedulingapplication;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import schedulingapplication.Dao.DBReportsDao;

public class ApptTypesPerMonthReportController implements Initializable {

    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private BarChart<?, ?> apptTypesBarChart;

    private DBReportsDao db;
    private ArrayList<String> months = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //db.selectApptTypesPerMonth();

        XYChart.Series dataSet1 = new XYChart.Series<>();

        dataSet1.getData().add(new XYChart.Data(loadMonths(), 12));

        apptTypesBarChart.getData().addAll(dataSet1);
    }

    protected ArrayList<String> loadMonths() {
        months.add("january");
        months.add(DateTime)
        DateTime time = null;
    }

}
