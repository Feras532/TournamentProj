package participant;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Classes.Elimination;
import Classes.Paricipant;
import Classes.RoundRobin;
import Classes.Team;
import Classes.Tournament;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HistoryController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView imageType;

    @FXML
    private Label rank;

    @FXML
    private Label teamName;

    @FXML
    private Label tournamentName;

    @FXML
    void initialize() {

    }

    void setData(int i, Tournament t, Paricipant p) {
        if (t instanceof Elimination) {
            Elimination tournament = ((Elimination) t);
            ArrayList<Team> arrayTeams = tournament.getRegisteredTeams();
            for (Team team : arrayTeams) {
                for (Paricipant player : team.getPlayers()) {
                    if (player == p) {
                        String Rank = ((Elimination) tournament).getRank(team);
                        rank.setText(Rank);
                        System.out.println(Rank);
                        teamName.setText(team.getNameString());
                        tournamentName.setText(t.getName());
                        break;
                    }
                }
            }

        }
        else{
            RoundRobin tournament = ((RoundRobin) t);
            ArrayList<Team> arrayTeams = tournament.getRegisteredTeams();
            for (Team team : arrayTeams) {
                for (Paricipant player : team.getPlayers()) {
                    if (player == p) {
                        String Rank = team.getRankString();
                        System.out.println(player.getLastName() +" "+team.getRankString());
                        rank.setText(Rank);
                        System.out.println(Rank);
                        teamName.setText(team.getNameString());
                        tournamentName.setText(t.getName());
                        break;
                    }
                }
            }
        }

    }

}
