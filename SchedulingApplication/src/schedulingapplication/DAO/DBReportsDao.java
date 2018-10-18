package schedulingapplication.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.ObservableList;

public class DBReportsDao implements IReportsDao {

    ObservableList<String> monthsOfAppts;

    public DBReportsDao() {
    }

    @Override
    public ObservableList<String> countApptTypesByMonths() {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select first from appointment";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Date apptDate = rs.getDate(1);

                String dateString = apptDate.toString();
                String month = dateString.substring(6, 7);

                monthsOfAppts.add(month);

                for (int i = 0; i < monthsOfAppts.size(); i++) {
                  // get number of months converted to month name 
                }
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
        return monthsOfAppts;

    }

    public String countNumberOfMonths(int numberOfMonths) {        
        String monthString;

        switch (numberOfMonths) {
            case 1:
                monthString = "January";
                break;
            case 2:
                monthString = "February";
                break;
            case 3:
                monthString = "March";
                break;
            case 4:
                monthString = "April";
                break;
            case 5:
                monthString = "May";
                break;
            case 6:
                monthString = "June";
                break;
            case 7:
                monthString = "July";
                break;
            case 8:
                monthString = "August";
                break;
            case 9:
                monthString = "September";
                break;
            case 10:
                monthString = "October";
                break;
            case 11:
                monthString = "November";
                break;
            case 12:
                monthString = "December";
                break;
            default:
                monthString = "Invalid Month";
                break;
        }

        return monthString;
    }

    @Override
    public ObservableList<String> selectApptTypesPerMonth() {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select first from appointment";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Date apptDate = rs.getDate(1);

                String dateString = apptDate.toString();
                String month = dateString.substring(6, 7);

                monthsOfAppts.add(month);
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
        return monthsOfAppts;
    }
}
