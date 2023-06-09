package participant.elimination;

import javafx.event.EventHandler;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import Classes.Elimination;
import Classes.Match;
import Classes.Round;
import Classes.SystemData;
import Classes.Team;
import Classes.Tournament;
import participant.controller_DashBoard_par;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.*;

public class controllerElimination {
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Text Date;
    @FXML
    private Text GameName;
    @FXML
    private Text Limit;
    @FXML
    private Text TournamentName;
    @FXML
    private Text numRegistered;
    @FXML
    private Text status;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label winnerLabel;
    @FXML
    private AnchorPane winnerPane;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void backHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/participant/DashBoard_par.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // static Elimination tournament = (Elimination)
    // controller_DashBoard_admin.selectedTournament;

    @FXML
    void initialize() throws IOException {
        // ...
        Elimination tournament = (Elimination) controller_DashBoard_par.selectedTournament;
        // Use the tournament variable here
        // ...
        // =============================================
        if (!tournament.getIsCompleted()) {
            winnerPane.setVisible(false);
        } else {
            winnerPane.setVisible(true);
            winnerLabel.setText(tournament.getWinner().getNameString());
            save();
        }

        Group root = new Group();
        fillWithMatches(root, tournament);
        scrollPane.setContent(root); // display the content in the scroll pane
        //////////////////
        mainSceneStyler();

    }

    public void mainSceneStyler() {
        Elimination tournament = (Elimination) controller_DashBoard_par.selectedTournament;

        //// styling the getters
        Date.setText("from: " + tournament.getStartdate() + " To: " + tournament.getEndDate());
        GameName.setText("Game: " + tournament.getGame());
        TournamentName.setText("Name: " + tournament.getName());
        numRegistered.setText("" + tournament.getNumRegisteredTeams());
        numRegistered.setFont(Font.font("Verdana", 22));
        if (tournament.getNumOfTeams() != 0)
            Limit.setText("" + tournament.getNumOfTeams());
        else
            Limit.setText("Unlimited");

        Limit.setFont(Font.font("Verdana", 22));
        status.setText(tournament.getStatus());
    }

    public void fillWithMatches(Group root, Tournament tournament) throws IOException {
        final int WIDTH = 100;
        final int HEIGHT = 50;
        final int X_SPACING_OF_ROUNDS = 200;
        final int Y_SPACING_OF_FIRST_ROUND = 90;
        int ySpacingOfmatches = Y_SPACING_OF_FIRST_ROUND;
        ArrayList<Round> rounds = tournament.getRounds();
        int yPos = Y_SPACING_OF_FIRST_ROUND;
        int xPos = 0;
        int firstMatchYpos = Y_SPACING_OF_FIRST_ROUND;
        int roundNum = 0;
        for (Round round : rounds) {
            roundNum++;
            int NumOfMatches = round.getMatches().size();
            for (int i = 0; i < NumOfMatches; i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("oneBracket.fxml"));
                Match match = round.getMatches().get(i);
                Pane oneBracketRoot = loader.load();
                // Call methods on the controller to set data for the match
                controllerBracket controller = loader.getController();
                controller.setData(match);

                oneBracketRoot.setLayoutX(xPos);
                oneBracketRoot.setLayoutY(yPos);
                // Add an event handler to the pane

                // ==================================================================== this to
                // click on bracket and move scene.
                oneBracketRoot.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            // Load the new FXML file
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("matchDetails.fxml"));
                            Parent root = loader.load();
                            // Get the controller
                            controllerMatch controller2 = loader.getController();
                            // Call the setData method
                            controller2.setData(match);
                            // Get the current stage
                            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            // Create a new scene with the loaded content
                            Scene scene = new Scene(root);
                            // Set the scene of the current stage to the new scene
                            stage.setScene(scene);
                            // if (!tournament.getIsCompleted()) {

                            controller2.recordHelper(tournament, match);

                            save();
                            // }

                        } catch (IOException e) {
                            // Handle the exception
                            e.printStackTrace();
                        }
                    }
                });
                // =====================================================================
                Line l = new Line(xPos + WIDTH, yPos + HEIGHT / 2,
                        xPos + X_SPACING_OF_ROUNDS + WIDTH / 2, yPos + HEIGHT / 2);
                if (i % 2 == 0 && roundNum != rounds.size()) {
                    Line l1 = new Line(xPos + X_SPACING_OF_ROUNDS + WIDTH / 2, yPos + (ySpacingOfmatches / 2),
                            xPos + X_SPACING_OF_ROUNDS + WIDTH / 2, yPos + HEIGHT / 2);
                    root.getChildren().addAll(l1);
                } else if (roundNum != rounds.size()) {
                    Line l1 = new Line(xPos + X_SPACING_OF_ROUNDS + WIDTH / 2, yPos - (ySpacingOfmatches / 2),
                            xPos + X_SPACING_OF_ROUNDS + WIDTH / 2, yPos + HEIGHT / 2);
                    root.getChildren().addAll(l1);
                }
                root.getChildren().addAll(oneBracketRoot, l);
                if (NumOfMatches - i > 1)
                    yPos = yPos + ySpacingOfmatches;
            }
            // Updating coordinates for next round
            xPos = xPos + X_SPACING_OF_ROUNDS;
            ySpacingOfmatches = ySpacingOfmatches * 2;
            // Adding winner Rectangle
            if (roundNum == rounds.size()) {
                Rectangle r = new Rectangle(xPos + WIDTH / 2, firstMatchYpos, WIDTH, HEIGHT);
                r.setFill(Color.GOLD);
                ArrayList<Round> allRounds = tournament.getRounds();
                Round lastRound = allRounds.get(allRounds.size() - 1);
                Match lastMatch = lastRound.getMatches().get(0);
                if (lastMatch.getScore() != null) {
                    Team winner;
                    Team loser;
                    if (lastMatch.getScore()[0] > lastMatch.getScore()[1]) {
                        winner = lastRound.getMatches().get(0).getTeam1();
                        loser = lastRound.getMatches().get(0).getTeam2();

                        winner.setRank("Champion");
                        loser.setRank("Finals");
                    } else {
                        winner = lastRound.getMatches().get(0).getTeam2();
                        loser = lastRound.getMatches().get(0).getTeam1();
                        winner.setRank("Champion");
                        loser.setRank("Finals");

                    }
                    Text text = new Text(winner.getNameString());
                    text.setX(r.getX() + r.getWidth() / 2 - text.getLayoutBounds().getWidth() / 2);
                    text.setY(r.getY() + r.getHeight() / 2 + text.getLayoutBounds().getHeight() / 4);
                    root.getChildren().addAll(r, text);

                    if (!tournament.getIsCompleted()) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Tournament is completed");
                        alert.setHeaderText("Tournament is completed, \n winner is: " + winner);

                        Optional<ButtonType> result = alert.showAndWait();
                        ((Elimination) tournament).updateTeamsRank();
                        tournament.setIsCompleted(true);
                        tournament.setIsActive(false);
                        tournament.setWinner(winner);

                        save();
                    }
                }

            }
            // Updating coordinates for next round
            yPos = firstMatchYpos * 2 - Y_SPACING_OF_FIRST_ROUND / 2;
            firstMatchYpos = yPos;
        }

    }

    void save() {
        Elimination tournament = (Elimination) controller_DashBoard_par.selectedTournament;

        new SystemData().updateTournament("savedTournaments.dat", tournament.getName(), tournament);

    }
}