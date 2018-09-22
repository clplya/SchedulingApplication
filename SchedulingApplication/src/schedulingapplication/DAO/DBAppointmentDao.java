package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulingapplication.DomainObjects.Appointment;

public class DBAppointmentDao implements IAppointmentDao {

    private Appointment appointment;
    private ObservableList appointmentList = FXCollections.observableArrayList();

    public DBAppointmentDao() {

    }

    @Override
    public void addAppointment(int appointmentId, int customerId, String title, String description, String location, String contact, String url, LocalDateTime start, LocalDateTime end) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "insert into appointment(appointmentId,customerId,title,description,location,contact,url,start,end,createDate,createdBy,lastUpdateBy) values ("
                    + appointmentId + "," + customerId + ",'" + title + "','" + description + "','" + location + "','" + contact + "','" + url + "','" + start + "','" + end + "','" + now() + "',1,1)";
            int result = stmt.executeUpdate(sql);
            System.out.println("Inserting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void addNewAppointment(Appointment appointment) {
        Statement stmt;
        int appointmentId = appointment.getAppointmentId();
        int customerId = appointment.getCustomerId();
        String title = appointment.getTitle();;
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        String contact = appointment.getContact();
        String url = appointment.getURL();
        Date startDate = appointment.getStartDate();
        Date endDate = appointment.getEndDate();

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "insert into appointment(appointmentId,customerId,title,description,location,contact,url,start,end,createDate,createdBy,lastUpdateBy) values (" + appointmentId + "," + customerId + ",'" + title + "','" + description + "','" + location + "','" + contact + "','" + url + "','" + startDate + "','" + endDate + "','" + now() + "',1,1)";
            int result = stmt.executeUpdate(sql);
            System.out.println("Inserting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteAppointment(int deletedAppointmentId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "delete from appointment where appointmentId=" + deletedAppointmentId;
            int result = stmt.executeUpdate(sql);
            System.out.println("Deleting number of records: " + result);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public ObservableList getAllAppointments() {
        Statement stmt = null;
        Timestamp ts;

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select appointmentId,customerId,title,description,location,contact,url,start,end from appointment";
            ResultSet result = stmt.executeQuery(sql);

            while (result.next()) {
                int appointmentId = result.getInt(1);
                int customerId = result.getInt(2);
                String title = result.getString(3);
                String description = result.getString(4);
                String location = result.getString(5);
                String contact = result.getString(6);
                String url = result.getString(7);
                Date startDate = result.getDate(8);
                Date endDate = result.getDate(9);

                appointment = new Appointment(appointmentId, customerId, title, description, location, contact, url, startDate, endDate);
                appointmentList.add(appointment);
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
        return appointmentList;
    }

    @Override
    public Appointment getAppointment(int appointmentId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery("select appointmentId,customerId,title,description,location,contact,url,start,end from appointment where appointmentId =" + appointmentId);

            while (result.next()) {
                int apptId = result.getInt(1);
                int customerId = result.getInt(2);
                String title = result.getString(3);
                String description = result.getString(4);
                String location = result.getString(5);
                String contact = result.getString(6);
                String url = result.getString(7);
                Date startDate = result.getDate(8);
                Date endDate = result.getDate(9);

                appointment = new Appointment(apptId, customerId, title, description, location, contact, url, startDate, endDate);
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
        return appointment;
    }

    @Override
    public ObservableList getAppointmentsByCustomer(int selectedCustomerId) {
        Statement stmt = null;
        if(appointmentList.size() >0){
            appointmentList.clear();
        }

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "select appointmentId,customerId,title,description,location,contact,url,start,end from appointment where customerId =";
            ResultSet result = stmt.executeQuery(sql + selectedCustomerId);

            while (result.next()) {
                int apptId = result.getInt(1);
                int customerId = result.getInt(2);
                String title = result.getString(3);
                String description = result.getString(4);
                String location = result.getString(5);
                String contact = result.getString(6);
                String url = result.getString(7);
                Date startDate = result.getDate(8);
                Date endDate = result.getDate(9);

                appointment = new Appointment(apptId, customerId, title, description, location, contact, url, startDate, endDate);
                appointmentList.add(appointment);
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
        return appointmentList;
    }

    @Override
    public void updateAppointmentTitle(int upAppointmentId, String upAppointmentTitle) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String updateSql = null;
            updateSql = "update appointment set appointment.title =" + upAppointmentTitle + " where appointment.appointmentId =" + upAppointmentId;
            stmt.executeUpdate(updateSql);

            String selectSql = "select appointmentId,customerId,title,description,location,contact,url,start,end from appointment where appointment.appointmentId=" + upAppointmentId;
            ResultSet result = stmt.executeQuery(selectSql);

            while (result.next()) {
                int appointmentId = result.getInt(1);
                int customerId = result.getInt(2);
                String title = result.getString(3);
                String description = result.getString(4);
                String location = result.getString(5);
                String contact = result.getString(6);
                String url = result.getString(7);
                Date startDate = result.getDate(8);
                Date endDate = result.getDate(9);

                appointment = new Appointment(appointmentId, customerId, title, description, location, contact, url, startDate, endDate);
                System.out.println("Updated Appointment Title: " + appointment.getTitle());
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
    }
}
