package schedulingapplication;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;

public class ConsultantScheduleReportController implements Initializable {

    @FXML
    public TableView apptTable;
    @FXML
    public ComboBox consultantComboBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void comboBoxEventHandler(){
        
    }

}
