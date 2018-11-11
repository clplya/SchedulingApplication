package schedulingapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAppointmentPageController {

    @FXML
    private TextField apptContactField;
    @FXML
    private Button cancelButton;
    @FXML
    private TextField apptDescriptionField;

    @FXML
    private ComboBox<?> selectedAppointmentComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label appointmentTitleLabel1;

    @FXML
    private TextField apptTitleField;

    @FXML
    private Button saveButton;

    @FXML
    private TextField apptLocationField;

    @FXML
    private Label appointmentTitleLabel;

//    @FXML
//    void datePickerButtonHandler(ActionEvent event) {
//
//    }
    @FXML
    void selectedAppointmentController(ActionEvent event) {

    }

    @FXML
    void saveButtonHandler(ActionEvent event) {

    }

    @FXML
    void cancelButtonHandler(ActionEvent event) {

    }

}
