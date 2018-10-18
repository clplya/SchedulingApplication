package schedulingapplication.DomainObjects;

import java.time.LocalDate;
import javafx.fxml.FXML;

public class Reports {

    @FXML
    private LocalDate apptDate;
    @FXML
    private String apptMonth;

    public LocalDate getApptDate() {
        return apptDate;
    }

    public void setApptDate(LocalDate apptDate) {
        this.apptDate = apptDate;
    }

    public String getApptMonth() {
        return apptMonth;
    }

    public void setApptMonth(String apptMonth) {
        this.apptMonth = apptMonth;
    }

}
