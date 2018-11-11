package schedulingapplication.Dao;

import java.util.ArrayList;
import javafx.collections.ObservableList;

public interface IReportsDao {

    public ArrayList<String> countApptTypesByMonths();

    public ArrayList<String> selectApptTypesPerMonth();

    public ObservableList selectAllContactsNames();

    public ObservableList selectContactsAppointments(String contact);

}
