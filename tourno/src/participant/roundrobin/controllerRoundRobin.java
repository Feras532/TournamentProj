package participant.roundrobin;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Optional;

import Classes.Match;
import Classes.Paricipant;
import Classes.Round;
import Classes.RoundRobin;
import Classes.SystemData;
import Classes.Team;
import Classes.Tournament;
import participant.controller_DashBoard_par;
import participant.elimination.controllerBracket;
import participant.roundrobin.controllerMatch;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import participant.controller_DashBoard_par;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;


public class controllerRoundRobin {

    @FXML
    private Text Date;

    @FXML
    private Text GameName;

    @FXML
    private Text Limit;

    @FXML
    private Text TournamentName;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Text numRegistered;

    @FXML
    private TableView<Button> roundsTable;

    @FXML
    private Text status;

    @FXML
    private GridPane roundsGridPane;
    
    @FXML
    private TableColumn<Team, Integer> teamLoss;

    @FXML
    private TableColumn<Team, Integer> teamGD;

    @FXML
    private TableColumn<Team, Integer> teamDraws;

    @FXML
    private TableColumn<Team, String> teamName;

    @FXML
    private TableColumn<Team, Integer> teamWins;

    @FXML
    private TableColumn<Team, Integer> teamPoints;

    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private Label winnerLabel;

    @FXML
    private AnchorPane winnerPane;

    private Stage stage;
    private Scene scene;
    
    
    @FXML
    void backHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/participant/DashBoard_par.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void initialize(){
        RoundRobin tournament = (RoundRobin) controller_DashBoard_par.selectedTournament;

        mainSceneStyler();
        //tournament.printMe();
        try{
            roundsGridPane.setAlignment(Pos.CENTER);
            roundsGridPane.setHgap(5);
            roundsGridPane.setVgap(50);
            roundsGridPane.setPadding(new Insets(25, 25, 25, 25));

            
            ArrayList<ArrayList<Match>> allRoundMatches = new ArrayList<>();
            
            ArrayList<ArrayList<Button>> allButtons = new ArrayList<>();

            for (Round round : tournament.getRounds()) {
                allRoundMatches.add(round.getMatches());
            }
            for (int i = 0; i < tournament.getRounds().size(); i++) {
                Label label = new Label("ROUND " + (i + 1));
                roundsGridPane.add(label, i, 0); // Add the label to the grid pane at column 0 and row i
                for (int j = 0; j < tournament.getRounds().get(i).getMatches().size(); j++) {
                    allButtons.add(new ArrayList<Button>());
                    Button button = new Button(tournament.getRounds().get(i).getMatches().get(j).getMatchString());
                    Match match = tournament.getRounds().get(i).getMatches().get(j);
                    roundsGridPane.add(button, i, j+1);
                    if(match.hasBeenRecorded){
                        button.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                    }
                    allButtons.get(i).add(button);
                    // ==================================================================== this to
                    // click on bracket and move scene.
                    button.setOnAction(event -> {
                            try {
                                // Load the new FXML file
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("matchDetailsRR.fxml"));
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
                                controller2.recordHelper(tournament,match);

                               save();


                            } catch (IOException e) {
                                // Handle the exception
                                e.printStackTrace();
                            }
                        });
                    
                //});
            }
        } 
        boolean lockNextRounds = false;
        boolean allLastRoundMatchesRecorded = false;
        for (int i = 0; i < allRoundMatches.size(); i++) {
            ArrayList<Match> matches = allRoundMatches.get(i);
            if(lockNextRounds){
                for (int j = 0; j < matches.size(); j++) {
                    allButtons.get(i).get(j).setDisable(true);
                }
            }
            else{
                int counter = 0;
                for (int j = 0; j < matches.size(); j++) {
                    if(i == allRoundMatches.size()-1 && matches.get(j).hasBeenRecorded){ //last round each match recorded
                        counter+=1;
                        if(counter == matches.size()){   
                            allLastRoundMatchesRecorded = true;
                        }                               
                    }
                    else if(!matches.get(j).hasBeenRecorded){
                        lockNextRounds = true;
                    }
                }
            }
        }       

        RoundRobin.sortTeamsByPointsAndGoalDifference(tournament.getRegisteredTeams());

        Match lastMatch = allRoundMatches.get(tournament.getRounds().size()-1).get(
            allRoundMatches.get(tournament.getRounds().size()-1).size()-1);

        if (allLastRoundMatchesRecorded && !tournament.getIsCompleted()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Tournament is completed");
            alert.setHeaderText("Tournament is completed, \n winner is: " + tournament.getRegisteredTeams().get(0));

            Optional<ButtonType> result = alert.showAndWait();
            ((RoundRobin) tournament).updateTeamsRank();
            tournament.setIsCompleted(true);
            tournament.setIsActive(false);
            tournament.setWinner( tournament.getRegisteredTeams().get(0));

            save();
        }
        if (!tournament.getIsCompleted()) {
            winnerPane.setVisible(false);
        } else {
            winnerPane.setVisible(true);
            winnerLabel.setText(tournament.getWinner().getNameString());
            save();
        }
            //---------------------------------------------------------
            
        ObservableList<Team> teamsobservableList = FXCollections.observableArrayList();

            for (Team team : tournament.getRegisteredTeams()) { // teams table
                
                teamsobservableList.add(team);
            }
            System.out.println(teamsobservableList);
            teamName.setCellValueFactory(new PropertyValueFactory<Team, String>("nameString"));
            teamWins.setCellValueFactory(new PropertyValueFactory<Team, Integer>("wins"));
            teamLoss.setCellValueFactory(new PropertyValueFactory<Team, Integer>("losses"));
            teamDraws.setCellValueFactory(new PropertyValueFactory<Team, Integer>("draws"));
            teamGD.setCellValueFactory(new PropertyValueFactory<Team, Integer>("goalDifference"));
            teamPoints.setCellValueFactory(new PropertyValueFactory<Team, Integer>("gamePoints"));


            teamsTable.setItems(teamsobservableList);

            save();
            for (Team team : tournament.getRegisteredTeams()) {
                for (Paricipant p : team.getPlayers()) {
                    SystemData.updateParticipant(p);
                }
            }
        
    }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    void save() {
        RoundRobin tournament = (RoundRobin) controller_DashBoard_par.selectedTournament;

        new SystemData().updateTournament("savedTournaments.dat", tournament.getName(), tournament);

    }
    
    public void mainSceneStyler() {
        RoundRobin tournament = (RoundRobin) controller_DashBoard_par.selectedTournament;

        //// styling the getters
        Date.setText("from: " + tournament.getStartdate() + " To: " + tournament.getEndDate());
        GameName.setText("Game: " + tournament.getGame());
        TournamentName.setText("Name: " + tournament.getName());
        numRegistered.setText("" + tournament.getNumRegisteredTeams());
        numRegistered.setFont(Font.font("Verdana", 22));
        Limit.setText("" + tournament.getNumOfTeams());
        Limit.setFont(Font.font("Verdana", 22));
        status.setText(tournament.getStatus());
    }
}
