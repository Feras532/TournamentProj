package admin.roundrobin;

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
import Classes.RoundRobin;
import Classes.SystemData;
import Classes.Team;
import Classes.Tournament;
import admin.controller_DashBoard_admin;
import admin.elimination.playerController;
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
    private URL location;
    
    @FXML
    private ImageView recordScore;

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
    void returnTournament(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("roundrobin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void initialize() throws IOException {

        buttonsInteraction(resetTeams);
        buttonsInteraction(recordScore);
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
            }
            else {
                int[] newScore = { Integer.parseInt(team1Score), Integer.parseInt(team2Score) };
                match.setScore(newScore);
                setData(match);
                //if (match.getScore()[0] > match.getScore()[1])
                    //controllerElimination.tournament.promoteWinner(match.getTeam1());
                //else
                    //controllerElimination.tournament.promoteWinner(match.getTeam2());
            }
        }

        save();
    }

    public void setData(Match match) {
        team1name.setText(match.getTeam1().getNameString());
        team2name.setText(match.getTeam2().getNameString());


        int[] trashArray = null;
        if (match.getScore() == trashArray) {
            team1score.setText("_");
            team2score.setText("_");
            team1winORlost.setText("");
            team2winORlost.setText("");

        } else {
            team1score.setText("" + match.getScore()[0]);
            team2score.setText("" + match.getScore()[1]);
            if (match.getScore()[0] > match.getScore()[1]) { //team 1 wins
                team1winORlost.setText("Won!");
                team1winORlost.setTextFill(Color.GREEN);
                team2winORlost.setText("Lost!");
                team2winORlost.setTextFill(Color.RED);

                if(!match.hasBeenRecorded){
                    match.getTeam1().setWins( match.getTeam1().getWins()+1);
                    match.getTeam2().setLosses( match.getTeam2().getLosses()+1);
                }


            } else if(match.getScore()[0] == match.getScore()[1]){ //draw
                team1winORlost.setText("Tie!");
                team1winORlost.setTextFill(Color.ORANGE);
                team2winORlost.setText("Tie!");
                team2winORlost.setTextFill(Color.ORANGE);

                if(!match.hasBeenRecorded){
                    match.getTeam1().setDraws( match.getTeam1().getDraws()+1);
                    match.getTeam2().setDraws( match.getTeam2().getDraws()+1);
                }
            }
            else { //team 2 wins
                team2winORlost.setText("Won!");
                team2winORlost.setTextFill(Color.GREEN);
                team1winORlost.setText("Lost!");
                team1winORlost.setTextFill(Color.RED);
                if(!match.hasBeenRecorded){
                    match.getTeam1().setLosses( match.getTeam1().getLosses()+1);
                    match.getTeam2().setWins( match.getTeam2().getWins()+1);
                }
            }
            if(!match.hasBeenRecorded){
                match.getTeam1().setGoalsScored(match.getScore()[0]);
                match.getTeam1().setGoalsReceived(match.getScore()[1]);

                match.getTeam2().setGoalsScored(match.getScore()[1]);
                match.getTeam2().setGoalsReceived(match.getScore()[0]);
                match.hasBeenRecorded = true;
            }
        }
    }

    public void recordHelper(Tournament tournament,Match match) throws IOException {
        if (!tournament.getIsCompleted()) {
            resetTeams.setOnMouseClicked(event -> resetter(event, match));
            recordScore.setOnMouseClicked(event -> handleButtonClick(event, match));
        }
        setData(match);
        fillPlayers(match);
    }

    static RoundRobin tournament = (RoundRobin) controller_DashBoard_admin.selectedTournament;

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
        match.hasBeenRecorded = false;
        if (match.getScore()[0] > match.getScore()[1]) { //team 1 wins
            match.getTeam1().setWins( match.getTeam1().getWins()-1);
            match.getTeam2().setLosses( match.getTeam2().getLosses()-1);

        } else if(match.getScore()[0] == match.getScore()[1]){ //draw
            match.getTeam1().setDraws( match.getTeam1().getDraws()-1);
            match.getTeam2().setDraws( match.getTeam2().getDraws()-1);
        }
        else { 
            match.getTeam1().setLosses( match.getTeam1().getLosses()-1);
            match.getTeam2().setWins( match.getTeam2().getWins()-1);
        }
        match.getTeam1().setGoalsScored(match.getTeam1().getGoalsScored()-match.getScore()[0]);
        match.getTeam1().setGoalsReceived(match.getTeam1().getGoalsReceived()-match.getScore()[1]);

        match.getTeam2().setGoalsScored(match.getTeam2().getGoalsScored()-match.getScore()[1]);
        match.getTeam2().setGoalsReceived(match.getTeam2().getGoalsReceived()-match.getScore()[0]);

        match.setScore(emptyArray(match.getScore()));
        setData(match);


    }

    public static int[] emptyArray(int[] array) {
        return array = null;
    }

    static void save() {
        new SystemData().updateTournament("savedTournaments.dat", tournament.getName(), tournament);

    }
    private void fillPlayers(Match match) throws IOException {

        // filling team 1 players::::
        ArrayList<Paricipant> players = match.getTeam1().getPlayers();
        int i = 0;
        for (Paricipant player : players) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/elimination/player.fxml"));
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/elimination/player.fxml"));
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
