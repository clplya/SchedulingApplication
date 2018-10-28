package schedulingapplication.Dao;

import java.util.ArrayList;

public interface IReportsDao {

    public ArrayList<String> countApptTypesByMonths();

    public ArrayList<String> selectApptTypesPerMonth();

}
