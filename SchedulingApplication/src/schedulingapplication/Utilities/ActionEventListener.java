package schedulingapplication.Utilities;

import java.util.EventListener;
import javafx.event.ActionEvent;

public interface ActionEventListener extends EventListener {
    
    public void actionEventClicked(ActionEvent ae);
}
