package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulingapplication.DomainObjects.Appointment;

public class DBReportsDao implements IReportsDao {

    private ArrayList<String> monthsOfAppts;
    private ObservableList<String> consultants;
    private Appointment appointment;
    private ObservableList<Appointment> appointmentList;

    public DBReportsDao() {
        this.consultants = FXCollections.observableArrayList();
        this.appointmentList = FXCollections.observableArrayList();
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

    @Override
    public ObservableList<String> selectAllConsultantNames() {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();
            
            stmt = conn.createStatement();
            String sql = "select contact from appointment";
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                String contact = result.getString(6);
    
                consultants.add(contact);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return consultants;
    }

    @Override
    public ObservableList selectConsultantsAppointments(String consultant) {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select * from appointment where contact = '" + consultant + "'";
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                int appointmentId = result.getInt(1);
                int customerId = result.getInt(2);
                String title = result.getString(3);
                String description = result.getString(4);
                String location = result.getString(5);
                String contact = result.getString(6);
                String url = result.getString(7);
                java.sql.Date startDate = result.getDate(8);
                java.sql.Date endDate = result.getDate(9);

                LocalDate localStartDate = startDate.toLocalDate();
                LocalDate localEndDate = endDate.toLocalDate();

                appointment = new Appointment(appointmentId, customerId, title, description, location, contact, url, localStartDate, localEndDate);
                appointmentList.add(appointment);

            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return appointmentList;
    }
}
