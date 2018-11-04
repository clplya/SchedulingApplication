package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DBReportsDao implements IReportsDao {

    private ArrayList<String> monthsOfAppts;
    private ArrayList<String> consultants;

    public DBReportsDao() {
    }

    @Override
    public ArrayList<String> countApptTypesByMonths() {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select start from appointment";
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

    public ArrayList selectAllConsultantNames() {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select userName from user";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String Name = rs.getString(1);

                consultants.add(Name);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return consultants;
    }

    @Override
    public ArrayList<String> selectApptTypesPerMonth() {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select start from appointment";
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
