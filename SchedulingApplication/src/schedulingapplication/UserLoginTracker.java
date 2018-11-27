package schedulingapplication;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;

public class UserLoginTracker {

    private File file;
    private File fileDestination;

    public void userLoginTracker(String input) throws IOException {
        File path = new File("logintracker.txt");
        
        try (OutputStream outputStream = new FileOutputStream(path)) {
            
            outputStream.write((input + LocalDateTime.now().toString()).getBytes());
        }
    }
    
    public void userLoginFailed(String input) throws IOException {
        File path = new File("logintracker.txt");
        
        try (OutputStream outputStream = new FileOutputStream(path)) {
            
            outputStream.write((input + LocalDateTime.now().toString()).getBytes());
        }
    }

    private void setupTracker(File source, File Destination) throws IOException {
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
