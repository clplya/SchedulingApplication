package schedulingapplication.DAO;

import javafx.collections.ObservableList;

public interface IReportsDao {

     public ObservableList<String> countApptTypesByMonths();
    
     public ObservableList selectApptTypesPerMonth();

}
