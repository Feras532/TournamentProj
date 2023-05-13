package admin.elimination;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import Classes.Elimination;
import Classes.Match;
import Classes.Paricipant;
import Classes.Round;
import Classes.SystemData;
import Classes.Team;
import Classes.Tournament;
import admin.controller_DashBoard_admin;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Node;

public class controllerMatch {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ResourceBundle resources;

    @FXML
    private ImageView demoteTeam;
    @FXML
    private URL location;

    @FXML
    ImageView recordScore;

    @FXML
    private ImageView resetTeams;

    @FXML
    private Label team1name;

    @FXML
    private Label team1score;

    @FXML
    private Label team1winORlost;

    @FXML
    private Label team2name;

    @FXML
    private Label team2score;

    @FXML
    private Label team2winORlost;
    @FXML
    private VBox vboxTeam1;

    @FXML
    private VBox vboxTeam2;
    @FXML
    private Label Date;


    @FXML
    void returnTournament(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("elimination.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();

    }

    @FXML
    void initialize() throws IOException {
        // ...
        //Elimination tournament = (Elimination) controller_DashBoard_admin.selectedTournament;
        // Use the tournament variable here
        // ...
        // =============================================
        buttonsInteraction(demoteTeam);
        buttonsInteraction(resetTeams);
        buttonsInteraction(recordScore);

        // ArrayList<Round> rounds = tournament.getRounds();
        // for (int i = 0; i < rounds.size(); i++) {
        // ArrayList<Match> matches = rounds.get(i).getMatches();
        // for (int j = 0; j < matches.size(); j++) {
        // if (matches.get(j).getTeam1().equals(team1name) &&
        // matches.get(j).getTeam2().equals(team2name)) {
        // System.out.println("Herreeee");
        // fillPlayers(matches.get(j));
        // break;
        // }

        // }
        // }

    }

    void buttonsInteraction(ImageView img) {
        DropShadow shadow = new DropShadow();
        img.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setCursor(Cursor.HAND);
                img.setEffect(shadow);
            }
        });
        img.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                img.setCursor(Cursor.DEFAULT);
                img.setEffect(null);
            }
        });
    }

    // this method records the match score
    public void handleButtonClick(MouseEvent event, Match match) {
        Elimination tournament = (Elimination) controller_DashBoard_admin.selectedTournament;

        String team1Name = match.getTeam1().getNameString();
        String team2Name = match.getTeam2().getNameString();
        if (team1Name.equals("TBA") || team2Name.equals("TBA")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Invalid Teams");
            errorAlert.setHeaderText("To be announced!.");
            errorAlert.setContentText("You cannot record scores, BRACKET is not ready yet!");
            errorAlert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Enter Match Scores");
        alert.setHeaderText("Enter the match scores for " + team1Name + " and " + team2Name);
        TextField team1Field = new TextField();
        team1Field.setPromptText(team1Name + " Score");
        TextField team2Field = new TextField();
        team2Field.setPromptText(team2Name + " Score");
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.add(new Label(team1Name + " Score:"), 0, 0);
        grid.add(team1Field, 1, 0);
        grid.add(new Label(team2Name + " Score:"), 0, 1);
        grid.add(team2Field, 1, 1);
        alert.getDialogPane().setContent(grid);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String team1Score = team1Field.getText();
            String team2Score = team2Field.getText();
            if (!team1Score.matches("\\d+") || !team2Score.matches("\\d+")) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Invalid Scores");
                errorAlert.setHeaderText("The scores must be integers.");
                errorAlert.setContentText("Please enter valid integer scores for each team.");
                errorAlert.showAndWait();
                return;
            } else if (team1Score.equals(team2Score)) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setTitle("Invalid Scores");
                errorAlert.setHeaderText("The scores cannot be equal.");
                errorAlert.setContentText("Please enter different scores for each team.");
                errorAlert.showAndWait();
                return;
            } else {
                int[] newScore = { Integer.parseInt(team1Score), Integer.parseInt(team2Score) };
                match.setScore(newScore);
                setData(match);
                if (match.getScore()[0] > match.getScore()[1])
                    tournament.promoteWinner(match.getTeam1());
                else
                    tournament.promoteWinner(match.getTeam2());
            }
        } else {
        }

        save();
    }

    public void setData(Match match) {
        System.out.println();

        team1name.setText(match.getTeam1().getNameString());
        team2name.setText(match.getTeam2().getNameString());
        Date.setText(match.getDate());
        int[] trashArray = null;
        if (match.getScore() == trashArray) {
            team1score.setText("_");
            team2score.setText("_");
            team1winORlost.setText("");
            team2winORlost.setText("");

        } else {
            team1score.setText("" + match.getScore()[0]);
            team2score.setText("" + match.getScore()[1]);
            if (match.getScore()[0] > match.getScore()[1]) {
                team1winORlost.setText("Won!");
                team1winORlost.setTextFill(Color.GREEN);
                team2winORlost.setText("Lost!");
                team2winORlost.setTextFill(Color.RED);

            } else {
                team2winORlost.setText("Won!");
                team2winORlost.setTextFill(Color.GREEN);
                team1winORlost.setText("Lost!");
                team1winORlost.setTextFill(Color.RED);

            }
        }
    }

    public void recordHelper(Tournament tournament, Match match) throws IOException {

        if (!tournament.getIsCompleted()) {
            resetTeams.setOnMouseClicked(event -> resetter(event, match));
            recordScore.setOnMouseClicked(event -> handleButtonClick(event, match));
            demoteTeam.setOnMouseClicked(event -> onMouseClick_demoteTeam(event, match));
        }

        setData(match);
        fillPlayers(match);

    }

    // static Elimination tournament = (Elimination)
    // controller_DashBoard_admin.selectedTournament;

    private void resetter(MouseEvent event, Match match) {

        String team1Name = match.getTeam1().getNameString();
        String team2Name = match.getTeam2().getNameString();
        if (team1Name.equals("TBA") || team2Name.equals("TBA")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Invalid Teams");
            errorAlert.setHeaderText("To be announced!.");
            errorAlert.setContentText("You cannot reset invalid teams!");
            errorAlert.showAndWait();
            return;
        }
        // if (Removable(match)) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Reset Teams");
        alert.setHeaderText("Are you sure you want to reset the teams?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            match.setScore(emptyArray(match.getScore()));
            checkAndRemovePromotedTeam(match); // Call the checkAndRemovePromotedTeam method
            setData(match);
        }
        // } else {
        // Alert alert = new Alert(Alert.AlertType.ERROR);
        // alert.setTitle("Reset results");
        // alert.setHeaderText("It is not recorded.");
        // Optional<ButtonType> result = alert.showAndWait();

    }

    private void onMouseClick_demoteTeam(MouseEvent event, Match match) {
        if (Removable(match)) {
            String team1Name = match.getTeam1().getNameString();
            String team2Name = match.getTeam2().getNameString();

            List<String> choices = new ArrayList<>();
            choices.add(team1Name);
            choices.add(team2Name);
            ChoiceDialog<String> dialog = new ChoiceDialog<>(team1Name, choices);
            dialog.setTitle("Select Team");
            dialog.setHeaderText("Select a team to demote");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                String selectedTeam = result.get();
                if (selectedTeam.equals("TBA")) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setTitle("Invalid Teams");
                    errorAlert.setHeaderText("To be announced!.");
                    errorAlert.setContentText("No team to be demoted, please select a valid team");
                    errorAlert.showAndWait();
                    return;
                }
                // Your code here
                if (selectedTeam.equals(team1Name))
                    resetPrevious(match.getTeam1(), match);
                else
                    resetPrevious(match.getTeam2(), match);
                setData(match);
                save();
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Demotion error");
            errorAlert.setHeaderText("Cannot be demoted!");
            errorAlert.setContentText("you cant demote teams from first round.");
            errorAlert.showAndWait();
        }
    }

    public static int[] emptyArray(int[] array) {
        return array = null;
    }

    private static boolean Removable(Match match) {

        Elimination tournament = (Elimination) controller_DashBoard_admin.selectedTournament;

        Team team1 = match.getTeam1();
        Team team2 = match.getTeam2();
        ArrayList<Round> rounds = tournament.getRounds();
        int team1Existence = 0;
        int team2Existence = 0;
        if (match.getScore() == null) {
            for (Round r : rounds) {
                ArrayList<Match> matches = r.getMatches();
                for (Match m : matches) {
                    if (m.getTeam1() == team1 || m.getTeam2() == team1)
                        team1Existence++;
                    if (m.getTeam1() == team2 || m.getTeam2() == team2)
                        team2Existence++;
                }

            }
            System.out.println("Team 1 existence = " + team1Existence);
            System.out.println("Team 2 existence = " + team2Existence);

            if (team1Existence == 1 && team2Existence == 1)
                return false;
            else
                return true;
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Demote Error");
            errorAlert.setHeaderText("Cannot be demoted.");
            errorAlert.setContentText("Please reset the match score before trying to demote.");
            errorAlert.showAndWait();
            return false;
        }

    }

    private static void resetPrevious(Team team, Match currentMatch) {
        Elimination tournament = (Elimination) controller_DashBoard_admin.selectedTournament;

        if (Removable(currentMatch)) {
            if (team == currentMatch.getTeam1())
                currentMatch.setTeam1(new Team());
            if (team == currentMatch.getTeam2())
                currentMatch.setTeam2(new Team());
            ArrayList<Round> rounds = tournament.getRounds();
            for (int i = rounds.size() - 1; i >= 0; i--) {
                Round r = rounds.get(i);
                ArrayList<Match> matches = r.getMatches();
                for (Match m : matches) {
                    if (team == m.getTeam1() || team == m.getTeam2())
                        m.setScore(emptyArray(m.getScore()));
                    break;
                }
            }
        }
        save();

    }

    private void checkAndRemovePromotedTeam(Match currentMatch) {
        Elimination tournament = (Elimination) controller_DashBoard_admin.selectedTournament;

        ArrayList<Round> rounds = tournament.getRounds();
        int currentRoundIndex = -1;
        for (int i = 0; i < rounds.size(); i++) {
            Round r = rounds.get(i);
            if (r.getMatches().contains(currentMatch)) {
                currentRoundIndex = i;
                break;
            }
        }
        // Get the teams that played in the current match
        Team team1 = currentMatch.getTeam1();
        Team team2 = currentMatch.getTeam2();
        // Create a set of teams to demote
        HashSet<Team> teamsToDemote = new HashSet<>();
        teamsToDemote.add(team1);
        teamsToDemote.add(team2);
        // Loop through the next rounds and demote any team in the set
        for (int i = currentRoundIndex + 1; i < rounds.size(); i++) {
            Round r = rounds.get(i);
            ArrayList<Match> matches = r.getMatches();
            for (Match m : matches) {
                // Check if team1 or team2 is in this match
                if (teamsToDemote.contains(m.getTeam1())) {
                    // Demote the team and reset the score
                    m.setTeam1(new Team());
                    m.setScore(emptyArray(m.getScore()));
                }
                if (teamsToDemote.contains(m.getTeam2())) {
                    m.setTeam2(new Team());
                    m.setScore(emptyArray(m.getScore()));
                }

            }
        }

    }

    static void save() {
        Elimination tournament = (Elimination) controller_DashBoard_admin.selectedTournament;

        new SystemData().updateTournament("savedTournaments.dat", tournament.getName(), tournament);

    }

    private void fillPlayers(Match match) throws IOException {

        // filling team 1 players::::
        ArrayList<Paricipant> players = match.getTeam1().getPlayers();
        int i = 0;
        for (Paricipant player : players) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("player.fxml"));
            AnchorPane playerPane = loader.load();
            // Get the controller
            playerController controller3 = loader.getController();
            // Call the setData method
            controller3.setName(i + 1, player);
            vboxTeam1.getChildren().add(playerPane);
            i++;

        }

        // filling team 2 players::::
        players = match.getTeam2().getPlayers();
        i = 0;
        for (Paricipant player : players) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("player.fxml"));
            AnchorPane playerPane = loader.load();
            // Get the controller
            playerController controller3 = loader.getController();
            // Call the setData method
            controller3.setName(i + 1, player);
            vboxTeam2.getChildren().add(playerPane);
            i++;

        }
    }

}
