package admin;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class controller_DashBoard_admin {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ResourceBundle resources;

    @FXML
    private BorderPane borderpane;

    @FXML
    private URL location;

    @FXML
    private Button btn_logout;

    @FXML /// implement logout button with conformation message.
    public void logout(ActionEvent event) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            // handle logout
            Parent root = FXMLLoader.load(getClass().getResource("/Login/LoginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }


    //*********************************************************************//
    @FXML
    void viewTournament(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    // in view tournament we have 3 buttons: ongoing, past, future.----------------
    @FXML
    void ongoing(ActionEvent event) {

    }

    @FXML
    void past(ActionEvent event) {

    }

    @FXML
    void future(ActionEvent event) {

    }

    //*********************************************************************//



    @FXML
    void scene_addGame(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("addGame.fxml"));
        borderpane.setCenter(view);

    }

    @FXML
    void createTournament(ActionEvent event) {

    }

    @FXML
    void manageTournament(ActionEvent event) {

    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("profile.fxml"));
        borderpane.setCenter(view);
    }

    @FXML
    void initialize() {
        assert btn_logout != null
                : "fx:id=\"btn_logout\" was not injected: check your FXML file 'DashBoard_admin.fxml'.";

    }

}
