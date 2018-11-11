package schedulingapplication.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulingapplication.DomainObjects.Appointment;

public class DBAppointmentDao implements IAppointmentDao {

    private Appointment appointment;
    private ObservableList<Appointment> appointmentList;

    public DBAppointmentDao() {
        appointmentList = FXCollections.observableArrayList();
        appointment = null;
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
        String title = appointment.getTitle();
        String description = appointment.getDescription();
        String location = appointment.getLocation();
        String contact = appointment.getContact();
        String url = appointment.getURL();
        LocalDateTime startDate = appointment.getStartDate();
        LocalDateTime endDate = appointment.getEndDate();

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
    public ObservableList<Appointment> getAllAppointments() {
        Statement stmt = null;
        appointmentList.clear();

        try {
            Connection conn = DataSource.getConnection();

            stmt = conn.createStatement();
            String sql = "select appointmentId,customerId,title,description,location,contact,url,start,end from appointment";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int appointmentId = rs.getInt(1);
                int customerId = rs.getInt(2);
                String title = rs.getString(3);
                String description = rs.getString(4);
                String location = rs.getString(5);
                String contact = rs.getString(6);
                String url = rs.getString(7);
                Timestamp startDate = rs.getTimestamp(8);
                Timestamp endDate = rs.getTimestamp(9);

                LocalDateTime localStartDateTime = startDate.toLocalDateTime();
                LocalDateTime localEndDateTime = endDate.toLocalDateTime();

                appointment = new Appointment(appointmentId, customerId, title, description, location, contact, url, localStartDateTime, localEndDateTime);
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
    public Appointment getAppointment(int apptId) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            ResultSet result = stmt.executeQuery("select appointmentId,customerId,title,description,location,contact,url,start,end from appointment where appointmentId =" + apptId);

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
        if (appointmentList.size() > 0) {
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
                Timestamp startDate = result.getTimestamp(8);
                Timestamp endDate = result.getTimestamp(9);

                LocalDateTime localStartDate = startDate.toLocalDateTime();
                LocalDateTime localEndDate = endDate.toLocalDateTime();

                appointment = new Appointment(apptId, customerId, title, description, location, contact, url, localStartDate, localEndDate);
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

    public ObservableList<Appointment> getAppointmentsByContact(String inputContact) {
        Statement stmt = null;
        if (appointmentList.size() > 0) {
            appointmentList.clear();
        }
        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String sql = "select appointmentId,customerId,title,description,location,contact,url,start,end from appointment where contact ='";
            ResultSet result = stmt.executeQuery(sql + inputContact + "'");

            while (result.next()) {
                int apptId = result.getInt(1);
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

                appointment = new Appointment(apptId, customerId, title, description, location, contact, url, localStartDate, localEndDate);
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
            return appointmentList;
        }
    }

    @Override
    public void updateAppointmentTitle(int upAppointmentId, String upAppointmentTitle
    ) {
        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

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
                Timestamp startDate = result.getTimestamp(8);
                Timestamp endDate = result.getTimestamp(9);

                LocalDateTime localStartDate = startDate.toLocalDateTime();
                LocalDateTime localEndDate = endDate.toLocalDateTime();

                appointment = new Appointment(appointmentId, customerId, title, description, location, contact, url, localStartDate, localEndDate);
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

    @Override
    public void updateAppointment(int apptmentId, int customerId, String apptmentTitle,
            String apptmentDesc,
            String apptmentLocation,
            String contact, String apptmentUrl,
            LocalDate startDate, LocalDate endDate
    ) {

        Statement stmt = null;

        try {
            Connection conn = DataSource.getConnection();
            stmt = conn.createStatement();

            String updateSql = "update appointment set appointment.title =" + apptmentTitle
                    + ", description =" + apptmentDesc + ", location =" + apptmentLocation
                    + ", contact =" + contact + ", url =" + apptmentUrl + ", start =" + startDate
                    + ", end =" + endDate + "where apptmentId = " + apptmentId;

            stmt.executeUpdate(updateSql);
            String selectSql = "select appointmentId,customerId,title,description,location,contact,url,start,end                 from appointment where appointment.appointmentId=" + apptmentId;
            ResultSet result = stmt.executeQuery(selectSql);

            while (result.next()) {
                int rappointmentId = result.getInt(1);
                int rcustId = result.getInt(2);
                String rtitle = result.getString(3);
                String rdescription = result.getString(4);
                String rlocation = result.getString(5);
                String rcontact = result.getString(6);
                String rurl = result.getString(7);
                Timestamp rstartDate = result.getTimestamp(8);
                Timestamp rendDate = result.getTimestamp(9);

                LocalDateTime localStartDate = rstartDate.toLocalDateTime();
                LocalDateTime localEndDate = rendDate.toLocalDateTime();

                appointment = new Appointment(rappointmentId, rcustId, rtitle, rdescription, rlocation, rcontact, rurl, localStartDate, localEndDate);
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
