package schedulingapplication;

import java.io.File;

public class UserLoginTracker {

    private File file;

    public void userLoginTracker() {
        file = new File("C:\\Users\\clply\\Documents\\GitHub\\SchedulingApplication\\SchedulingApplication\\logintracker.txt");
        if (file.exists()) {
            System.out.println("file exists");
        } else {
            file.listFiles();
        }
    }

    public void setupTracker() {
        //BufferedInputStream inputStream = new BufferedInputStream(new InputStream(file));
    }
}
