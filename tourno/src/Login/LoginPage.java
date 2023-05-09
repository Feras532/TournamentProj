package Login;
import java.net.URL;
import java.util.ResourceBundle;
import Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginPage {
    private Stage stage;
    private Scene scene;

    @FXML
    private ResourceBundle resources;
    // public static Admin adminUser;
    // 7032,8809,student,Abdulmjeed.alothman222@gmail.com,"TURAIKI, DHAFER"
    public static Paricipant participantUser = new Paricipant("7032", "DHAFER", "TURAIKI","Abdulmjeed.alothman222@gmail.com");
    @FXML
    private URL location;
    @FXML
    public void LoginAdmin(ActionEvent event) throws Exception {
  
        // Parent root = FXMLLoader.load(getClass().getResource("/admin/DashBoard_admin.fxml"));
        // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // scene = new Scene(root);
        // stage.setScene(scene);
        // stage.setMaximized(false);
        // stage.setMaximized(true);
        // stage.show();


        Parent newPage = FXMLLoader.load(getClass().getResource("/admin/DashBoard_admin.fxml"));
        Scene newScene = new Scene(newPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(newScene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        appStage.setX(bounds.getMinX());
        appStage.setY(bounds.getMinY());
        appStage.setWidth(bounds.getWidth());
        appStage.setHeight(bounds.getHeight());

        appStage.show();
    }

    @FXML
    public void LoginParticipant(ActionEvent event) throws Exception {
  
        Parent newPage = FXMLLoader.load(getClass().getResource("/participant/DashBoard_participant.fxml"));
        Scene newScene = new Scene(newPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(newScene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        appStage.setX(bounds.getMinX());
        appStage.setY(bounds.getMinY());
        appStage.setWidth(bounds.getWidth());
        appStage.setHeight(bounds.getHeight());

        appStage.show();
    }

    @FXML
    void initialize() {

    }

}
