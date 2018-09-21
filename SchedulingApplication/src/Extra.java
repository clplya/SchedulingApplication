//package schedulingapplication;
//
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.*;
//import schedulingapplication.Dao.DBAppointmentDao;
//import schedulingapplication.Dao.DBCustomerDao;
//import schedulingapplication.DomainObjects.Appointment;
//import schedulingapplication.DomainObjects.Customer;
//
//public class AppointmentPageController {
//
//    private Appointment selectedAppointment;
//    private final DBAppointmentDao dbAppointment = new DBAppointmentDao();
//    private final DBCustomerDao dbCustomer = new DBCustomerDao();
//
//    @FXML
//    private Button cancelButton;
//    @FXML
//    private Button saveButton;
//    @FXML
//    private DatePicker datePicker;
//    @FXML
//    private TextField apptTitleField;
//    @FXML
//    private TextField apptDescriptionField;
//    @FXML
//    private TextField apptLocationField;
//    @FXML
//    private TextField apptContactField;
//    @FXML
//    private ComboBox selectedAppointmentComboBox = new ComboBox();
//    @FXML
//    private TableView<Appointment> appointmentTableView;
//    @FXML
//    private TableColumn<Appointment, String> AppointmentColumn;
//    @FXML
//    private final ObservableList<Appointment> appointmentList
//            = FXCollections.observableArrayList();
//
//    private Customer selectedCustomer;
//    private String titleText;
//
//    public void initialize(Customer customer) {
//
//        selectedCustomer = customer;
//
//        appointmentList.addAll((dbAppointment.getAppointmentsByCustomer(
//                selectedCustomer.getCustomerId())));
//
//        for (int i = 0; i < appointmentList.size(); i++) {
//            titleText = appointmentList.get(i).getTitle();
//            selectedAppointmentComboBox.getItems().clear();
//            selectedAppointmentComboBox.getItems().add(titleText);
//        }
//
//    }
////
////    @Override
//
//    public void loadComboBox() {
//        /* ******* Here is the Trick *********
//    listens to the value property of the comboBox.
//    calls the updateLabels function when the value changes. */
//        selectedAppointmentComboBox.valueProperty().addListener(new ChangeListener<String>() {
//
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                updateBoxes(newValue);
//            }
//        });
//        selectedAppointmentComboBox.setItems(appointmentList);
//    }
//
//    private void updateBoxes(String newValue) {
//
//        switch (newValue) {
//            case ("Treatment Room 1"):
//                setPatientInfo(0);
//                break;
//            case ("Treatment Room 2"):
//                setPatientInfo(1);
//                break;
//            case ("Treatment Room 3"):
//                setPatientInfo(2);
//                break;
//            default:
//                System.out.println("treatment room not recognised");
//        }
//
//    }
//
//    public void setPatientInfo(int i) {
//        apptTitleField.setText((dbAppointment.getAppointmentsByCustomer(
//                selectedCustomer.getCustomerId())));
//        apptDescriptionField.setText((dbAppointment.getAppointmentsByCustomer(
//                selectedCustomer.getCustomerId()))).getDescription();
//        apptLocationField.setText((dbAppointment.getAppointmentsByCustomer(
//                selectedCustomer.getCustomerId()))).getLocation();
//        apptContactField.setText((dbAppointment.getAppointmentsByCustomer(
//                selectedCustomer.getCustomerId()))).getContact();
//    }
//
//    @FXML
//    public void datePickerButtonHandler() {
//        LocalDate currentDate = datePicker.getValue();
//        ChronoLocalDate chronDate = datePicker.getChronology().date(currentDate);
//        System.out.println("Today's Date is: " + chronDate);
//    }
//
//    @FXML
//    public void selectedAppointmentController() {
//        selectedAppointment = (Appointment) selectedAppointmentComboBox.getValue();
//        //FIGURE OUT how to tell what Appointment is selected in the combobox to pull it's values
//
//        //appointmentList.contains(title);
////        apptTitleField.setText(appointmentList.
////                getTitle()));
////        apptDescriptionField.setText((dbAppointment.getAppointmentsByCustomer(
////                selectedCustomer.getCustomerId()).
////                getDescription()));
////        apptLocationField.setText((dbAppointment.getAppointmentsByCustomer(
////                selectedCustomer.getCustomerId()).
////                getLocation()));
////        apptContactField.setText((dbAppointment.getAppointmentsByCustomer(
////                selectedCustomer.getCustomerId()).
////                getContact()));
//    }
//
//    @FXML
//    public void saveButtonHandler(ActionEvent event) {
//        Appointment newAppointment = new Appointment(
//                10, 2, "Xray Procedure", "This is for an Xray", "Doctors Office", "Nurse", "none",
//                Date.valueOf("2019-03-12"), Date.valueOf("2019-03-12"));
//        dbAppointment.addNewAppointment(newAppointment);
//    }
//
//    @FXML
//    void cancelButtonHandler(ActionEvent event) throws IOException {
//        Stage stage;
//        Parent root;
//
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Cancel");
//        alert.setHeaderText("You will lose all progress on this screen");
//        alert.setContentText("Are you sure you want to cancel?");
//
//        Optional<ButtonType> window = alert.showAndWait();
//        if (window.get() == ButtonType.OK) {
//            stage = (Stage) cancelButton.getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
//
//            Scene scene = new Scene(root);
//            stage.setScene(scene);
//            stage.show();
//        } else if (window.get() == ButtonType.CANCEL) {
//            alert.close();
//        }
//    }
//}