package schedulingapplication.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulingapplication.DomainObjects.Reports;

public class DBReportsDao implements IReportsDao {

    private ObservableList months = FXCollections.observableArrayList();

    public DBReportsDao() {

    }

    @Override
    public void generateReports() {

    }

    @Override
    public void showReports() {

    }

    @Override
    public ObservableList monthsOfAppointments() {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery("select start from appointment");

            while (result.next()) {
                java.sql.Date start = result.getDate(1);

                LocalDate localStartDate = start.toLocalDate();

                Reports reports = new Reports();
                reports.setApptDate(localStartDate);
                String apptDate = reports.getApptDate().toString();
                String apptMonth = apptDate.substring(6, 7);
                months.add(apptMonth);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        return months;
    }
}
