package schedulingapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_FORWARD_ONLY;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import schedulingapplication.DomainObjects.Appointment;
import schedulingapplication.DomainObjects.Customer;
import schedulingapplication.DomainObjects.AppointmentManager;

public class AppointmentPageController {

   
    private Customer selectedCustomer;
    @FXML
    private DatePicker datePicker;
    @FXML
    public javafx.scene.control.ComboBox selectedAppointmentComboBox;
    private Appointment selectedAppointment;
    private final AppointmentManager appointmentManager = new AppointmentManager();
    private Connection conn;
    private Statement stmt;
    private final static String jdbcDriver = "com.mysql.cj.jdbc.Driver";
     @FXML
    private ObservableList<Appointment> appointmentList;
     @FXML
     private final ObservableList<Appointment> allAppointmentList  = null;

    @FXML
    public void initialize(Customer customer) throws SQLException, Exception {
        try {
            Class.forName(jdbcDriver);
            try {
                conn = DriverManager.getConnection(DAO.DB_URL, DAO.USER, DAO.PASS);
                stmt = conn.createStatement();
                viewAppointmentsDropDown(conn);
            } catch (SQLException ex) {
                System.out.println("Failed to create the DB connection.");
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver not found");
        }
    }

    
    public void datePickerButtonHandler() {
        LocalDate currentDate = datePicker.getValue();
        ChronoLocalDate chronDate = datePicker.getChronology().date(currentDate);
        System.out.println("Today's Date is: " + chronDate);
    }

    @FXML
    public void viewAppointmentsDropDown(Connection conn) throws SQLException, Exception {
        if (selectedAppointmentComboBox != null) {
            try {
                stmt = conn.createStatement(TYPE_FORWARD_ONLY, CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("select * from appointment");

//                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
//                    TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
//                    customerTableView.getColumns().addAll(col);
//                }
                while (rs.next()) {
                    appointmentManager.addAppointment(new Appointment(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                            rs.getString(7), rs.getDate(8), rs.getDate(9)));
                }
                
                EventHandler<ActionEvent> handler = selectedAppointmentComboBox.getOnAction();
                selectedAppointmentComboBox.setOnAction(null);

                selectedAppointmentComboBox.setItems(appointmentManager.getAllAppointments());
                selectedAppointmentComboBox.setOnAction(handler);
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
    }

//    public ObservableList<Appointment> getAllAppointments() throws Exception {
//        return appointmentManager.getAllAppointments();
//
//    }
}
