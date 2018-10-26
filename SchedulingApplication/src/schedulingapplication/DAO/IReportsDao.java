package schedulingapplication.Dao;

import javafx.collections.ObservableList;

public interface IReportsDao {

    public ObservableList<String> countApptTypesByMonths();

    public ObservableList<String> selectApptTypesPerMonth();

}
