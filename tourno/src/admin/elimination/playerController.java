package admin.elimination;

import java.net.URL;
import java.util.ResourceBundle;

import Classes.Paricipant;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class playerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label playerName;

    @FXML
    private AnchorPane playerPane;

    @FXML
    void initialize() {
        
    }

    void setName(Integer number,  Paricipant player){
        playerName.setText("#"+number +"  "+ player.getFirstName()+" " + player.getLastName());
    }
}
