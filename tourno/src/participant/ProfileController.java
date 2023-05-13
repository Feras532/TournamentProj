package participant;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import Classes.Paricipant;
import Classes.Team;
import Classes.Tournament;
import Login.LoginPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import admin.elimination.playerController;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label emailStringField;

    @FXML
    private Label firstNameField;

    @FXML
    private Label lastNameField;

    @FXML
    private Label lastNameField1;

    @FXML
    private Label userIdField;

    @FXML
    private Text usernameText;

    @FXML
    private VBox vboxHistory;

    @FXML
    void initialize() throws IOException {
        Paricipant student = LoginPage.getParticipantUser();

        userIdField.setText(student.getUserID());
        lastNameField.setText(student.getLastName());
        lastNameField1.setText(student.getLastName());
        firstNameField.setText(student.getFirstName());
        emailStringField.setText(student.getEmailString());

     
        ArrayList<Tournament> tournaments = student.getHistoryTournaments();

        for (Tournament t : tournaments) {
                ArrayList<Team> allTeams = t.getRegisteredTeams();
                for (int i = 0; i < allTeams.size(); i++) {
                    ArrayList<Paricipant> players = allTeams.get(i).getPlayers();
                    for (Paricipant p : players) {
                        if (p == student) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("history.fxml"));
                            AnchorPane playerPane = loader.load();
                            // Get the controller
                            HistoryController controller3 = loader.getController();
                            // Call the setData method
                            
                            controller3.setData(i + 1, t, p);
                            vboxHistory.getChildren().add(playerPane);
                            i++;

                        }
                    }
                }
            }
        }

    

    void fillTable() throws IOException, ClassNotFoundException {

    }
}
