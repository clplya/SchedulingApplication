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

        //      dataSet1 = new XYChart.Series<>();
        getBarChartData();
    }

    private void getBarChartData() {
        //ObservableList<Series<String, Double>> - return type with other way
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
//        for (String s : months) {
//
//            dataSet1.getData().add(new XYChart.Data(s, 1));
//
//        }
        for (int i = 0; i <= months.size(); i++) {
            dataSet1.getData().add(new XYChart.Data(i, 1));
        }
        apptTypesBarChart.getData().add(dataSet1);

        //trying something different
//        ObservableList<XYChart.Series<String, Double>> data = FXCollections.observableArrayList();
//
//        Series<String, Double> january = new Series<>();
//        Series<String, Double> february = new Series<>();
//        Series<String, Double> march = new Series<>();
//        Series<String, Double> april = new Series<>();
//        Series<String, Double> may = new Series<>();
//        Series<String, Double> june = new Series<>();
//        Series<String, Double> july = new Series<>();
//        Series<String, Double> august = new Series<>();
//        Series<String, Double> september = new Series<>();
//        Series<String, Double> october = new Series<>();
//        Series<String, Double> november = new Series<>();
//        Series<String, Double> december = new Series<>();
//
//        january.setName("January");
//        february.setName("February");
//        march.setName("March");
//        april.setName("April");
//        may.setName("May");
//        june.setName("June");
//        july.setName("July");
//        august.setName("August");
//        september.setName("September");
//        october.setName("October");
//        november.setName("November");
//        december.setName("December");
//
//        int januaryAppts = 0;
//        int augustAppts = 0;
//
//        for (String s : months) {
//            if (months.equals("1")) {
//                januaryAppts++;
//            }
//
//            if (months.equals("8")) {
//                augustAppts++;
//            }
//        }
//        for (int i = 0; i <= months.size(); i++) {
//            january.getData().add(new XYChart.Data(januaryAppts, 1));
//            august.getData().add(new XYChart.Data(augustAppts, 1));
//        }
//        return data;
//    }
    }
}
