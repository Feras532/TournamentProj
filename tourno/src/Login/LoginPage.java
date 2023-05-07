package Login;
import java.net.URL;
import java.util.ResourceBundle;
import Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginPage {
    private Stage stage;
    private Scene scene;

    @FXML
    private ResourceBundle resources;
    // public static Admin adminUser;
    public static Paricipant participantUser = new Paricipant("6104", "MOHAMMED", "ALAJWAD","Abdulmjeed.alothman222@gmail.com");
    @FXML
    private URL location;
    @FXML
    public void LoginAdmin(ActionEvent event) throws Exception {
  
        Parent root = FXMLLoader.load(getClass().getResource("/admin/DashBoard_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void LoginParticipant(ActionEvent event) throws Exception {
  
        Parent root = FXMLLoader.load(getClass().getResource("/participant/DashBoard_participant.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void initialize() {

    }

}
