package schedulingapplication.Utilities;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Navigator implements Initializable {

    //private final FXMLLoader loader = new FXMLLoader();
    //private final CustomerPageController controller = loader.getController();
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void goToFXMLPage(String pageName, Stage primaryStage) throws IOException {
        Parent root;
        root = FXMLLoader.load(getClass().getResource("'" + pageName + "'"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
