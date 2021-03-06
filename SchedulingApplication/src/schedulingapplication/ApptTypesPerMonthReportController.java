package schedulingapplication;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import schedulingapplication.Dao.DBReportsDao;

public class ApptTypesPerMonthReportController implements Initializable {

    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private BarChart apptTypesBarChart;
    @FXML
    private Button reportButton;

    private DBReportsDao db;
    private ObservableList<String> months = FXCollections.observableArrayList();
    private XYChart.Series dataSet1 = new XYChart.Series<>();

    ;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //db.selectApptTypesPerMonth();

    }

    @FXML
    public void reportButtonHandler(ActionEvent event) {
        getBarChartData();
    }

    private void getBarChartData() {
        Statement stmt;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select start from appointment";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Date apptDate = rs.getDate(1);

                String dateString = apptDate.toString();
                String month = dateString.substring(6, 7);

                months.add(month);
            }
        } catch (SQLException ex) {

            System.out.println(ex);
        }
        for (String s : months) {

            dataSet1.getData().add(new XYChart.Data(s, 1));

        }
        apptTypesBarChart.getData().add(dataSet1);

    }
}
