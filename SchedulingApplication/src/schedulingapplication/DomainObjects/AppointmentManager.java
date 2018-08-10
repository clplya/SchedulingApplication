package schedulingapplication.DomainObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import schedulingapplication.DAO;

public class AppointmentManager {

    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
    private Statement stmt; 
    private Connection conn;
    private String jdbcDriver  = "com.mysql.cj.jdbc.Driver";

    public void initialize() {
       try {
            Class.forName(jdbcDriver);
            try {
                conn = DriverManager.getConnection(DAO.DB_URL, DAO.USER, DAO.PASS);
                stmt = conn.createStatement();
            } catch (SQLException ex) {
                System.out.println("Failed to create the DB connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        }
    }
    public ObservableList<Appointment> getAllAppointments() throws Exception {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        try {
            Class.forName(jdbcDriver);
            try {
                stmt = conn.createStatement();
                stmt.setQueryTimeout(30);
                ResultSet rs = stmt.executeQuery("select * from appointment");
                while (rs.next()) {
                    appointmentList.add(new Appointment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
                                                        rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDate(9)));
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return appointmentList;
    }
    

    public ObservableList<Appointment> getCustomerAppointment(int customerId) throws SQLException, Exception{
        ObservableList<Appointment> customerAppointment = FXCollections.observableArrayList();
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();

        appointmentList.setAll(getAllAppointments());
        for (int i = 0; i < appointmentList.size(); i++) {
            if (appointmentList.get(i).getCustomerId() == customerId) {
                customerAppointment.add(appointmentList.get(i));
            }
        }
        return customerAppointment;
    }
}

//class AppointmentManagerOld {
//
//    private final ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
//    private Connection conn;
//    private Statement stmt;
//
//    private void connect() throws Exception {
//        try {
//            stmt = conn.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
//            ResultSet rs = stmt.executeQuery("select * from appointment");
//
//            while (rs.next()) {
//                addAppointment(new Appointment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5),
//                                               rs.getString(6), rs.getString(7), rs.getDate(8), rs.getDate(9)));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex);
//        }
//    }
//
//    public ObservableList<Appointment> getAllAppointments() throws Exception {
//        clear();
//
//        connect();
//
//        return appointmentList;
//    }
//
//    public void addAppointment(Appointment appointment) {
//        appointmentList.add(appointment);
//    }
//
//    public void clear() {
//        appointmentList.clear();
//    }
//
//    public ObservableList<Appointment> getCustomerAppointment(int customerId) {
//        ObservableList<Appointment> customerAppointment = FXCollections.observableArrayList();
//        for (int i = 0; i < appointmentList.size(); i++) {
//            if (appointmentList.get(i).getCustomerId() == customerId) {
//                customerAppointment.add(appointmentList.get(i));
//            }
//        }
//        return customerAppointment;
//    }
//}
