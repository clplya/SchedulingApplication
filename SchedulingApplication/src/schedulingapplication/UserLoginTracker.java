package schedulingapplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class UserLoginTracker {

    private File file;
    private File fileDestination;

    public void userLoginTracker(String input) throws IOException {
        file = new File("C:\\Users\\clply\\Documents\\GitHub\\SchedulingApplication\\SchedulingApplication\\logintracker.txt");
        //fileDestination = new File("");
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            if (file.exists()) {
                System.out.println("file exists");
                out.print(input);
                //setupTracker(file, fileDestination);
            } else {
                file.listFiles();
            }
        }
    }

    public void setupTracker(File source, File Destination) throws IOException {
        try (
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                OutputStream out = new BufferedOutputStream(new FileOutputStream(fileDestination))) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        }
    }
}
