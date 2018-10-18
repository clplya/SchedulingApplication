package schedulingapplication.DAO;

import javafx.collections.ObservableList;

public interface IReportsDao {

    public void generateReports();

    public void showReports();

    public ObservableList monthsOfAppointments();

}
