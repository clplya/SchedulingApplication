package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulingapplication.DomainObjects.Appointment;

public class DBReportsDao implements IReportsDao {

    private ArrayList<String> monthsOfAppts;
    private ObservableList<String> contacts;
    private ObservableList<String> users;
    private Appointment appointment;
    private ObservableList<Appointment> appointmentList;

    public DBReportsDao() {
        appointmentList = FXCollections.observableArrayList();
        contacts = FXCollections.observableArrayList();
        users = FXCollections.observableArrayList();
        appointment = null;
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

    public ObservableList<String> selectAllUsersNames() {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select userName from user";
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                String user = result.getString(1);

                users.add(user);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return users;
    }

    @Override
    public ObservableList<String> selectAllContactsNames() {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select contact from appointment";
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                String contact = result.getString(1);

                contacts.add(contact);
            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return contacts;
    }

    @Override
    public ObservableList selectContactsAppointments(String contactInput) {
        Statement stmt = null;

        try {
            Connection conn = schedulingapplication.Dao.DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select * from appointment where contact = '" + contactInput + "'";
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                int appointmentId = result.getInt(1);
                int customerId = result.getInt(2);
                String title = result.getString(3);
                String description = result.getString(4);
                String location = result.getString(5);
                String contact = result.getString(6);
                String url = result.getString(7);
                Timestamp startDate = result.getTimestamp(8);
                Timestamp endDate = result.getTimestamp(9);

                LocalDateTime localStartDate = startDate.toLocalDateTime();
                LocalDateTime localEndDate = endDate.toLocalDateTime();

                appointment = new Appointment(appointmentId, customerId, title, description, location, contact, url, localStartDate, localEndDate);
                appointmentList.add(appointment);

            }
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return appointmentList;
    }
}
