package admin.elimination;

import java.io.IOError;
import java.io.IOException;

import Classes.Match;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;

public class controllerBracket {
    @FXML
    private Text team1name;

    @FXML
    private Text team1score;

    @FXML
    private Text team2name;

    @FXML
    private Text team2score;
    @FXML
    private AnchorPane myPane;

    // ...

    @FXML
    private void initialize() {
        // Add an event handler to the Pane that changes its style when the mouse enters
        // or exits the Pane
        myPane.setOnMouseEntered(event -> {
            myPane.setStyle("-fx-background-color: #657c87;");
        });
        myPane.setOnMouseExited(event -> {
            myPane.setStyle("-fx-background-color: #3c494f");
        });
    }

    public void setData(Match match) {
        team1name.setText(match.getTeam1().getNameString());
        team2name.setText(match.getTeam2().getNameString());

        int[] trashArray = null;
        if (match.getScore() == trashArray) {
            team1score.setText("");
            team2score.setText("");
        } else {
            team1score.setText("" + match.getScore()[0]);
            team2score.setText("" + match.getScore()[1]);
        }

    }

}
