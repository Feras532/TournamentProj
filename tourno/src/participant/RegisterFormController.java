package participant;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import Classes.Paricipant;
import Classes.SystemData;
import Classes.Team;
import Classes.Tournament;
import Login.LoginPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.TouchEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterFormController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private GridPane grid;
    @FXML
    private Button submitBtn;
    @FXML
    private Label tournamentInfo;
    @FXML
    private Button backButton;

    private ArrayList<TextField> idFields = new ArrayList<>();
    private ArrayList<String> ids = new ArrayList<>();
    private String teamName ;
    private TextField teamNameField = new TextField();

    @FXML
    void initialize() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(40);
        grid.setVgap(40);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        // Tournament Info Label
        tournamentInfo.setText("Tournament :"+controller_DashBoard_participant.selectedTournament.getName());
        /// Team name fields
        Label teamNameLabel = new Label("Team Name:");
        teamNameField = new TextField();
        grid.add(teamNameLabel,0,0);
        grid.add(teamNameField,1,0);
        /// Team leader fields
        Label teamLeaderLabel = new Label("Team member 1 id:");
        Label teamLeaderField = new Label(LoginPage.getParticipantUser().getUserID());
        grid.add(teamLeaderLabel,0,1);
        grid.add(teamLeaderField,1,1);

         // Create an array list to store the text fields for team member ids
        
         for (int i = 0; i < controller_DashBoard_participant.selectedTournament.getNumofMembers()-1; i++) {
            Label label = new Label("Team member " + (i + 2) + " id:");
            grid.add(label, 0, i + 2); // Add the label to the grid pane at column 0 and row i
            TextField textField = new TextField();
            grid.add(textField, 1, i + 2); // Add the text field to the grid pane at column 1 and row i
            idFields.add(textField); // Add the text field to the array list

            textField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            });
        }
        


    }

    @FXML
    void submitBtn(ActionEvent event) {
        Tournament selectedTournament = controller_DashBoard_participant.selectedTournament;
        // validate team name 
        teamName = teamNameField.getText();
        if(teamName.isBlank()){
            showAlert("Please enter a valid name for team.");
            return; // Stop and exit the event handler
        }
        
        // Loop through the text fields and validate the input
        for (TextField textField : idFields) {
            String input = textField.getText(); // Get the input from the text field
                try {
                    String id = input; // Try to parse the input as an integer
                    ids.add(id);  // Do something with the id
                } catch (NumberFormatException e) {
                // If the input is not an integer, show an alert message
                showAlert("Please enter a valid integer for team member id.");
                return; // Stop the loop and exit the event handler
            }
        }
        /// Creating team with team leader and name 
        Team team = new Team(selectedTournament,teamName ,LoginPage.getParticipantUser());
        /// Validate Ids and add team members
        for (String id : ids) {
            Paricipant paricipant = SystemData.getParticipant(id);
            if(paricipant==null){
                showAlert("Entered ID doesn't match ID in system.");
                return;
            }
            else{
                team.getPlayers().add(paricipant);
                System.out.println("Player added " + paricipant.getUserID()+" " + paricipant.getLastName());
            }
        }
        //// Check If any player is already registered
        if(selectedTournament.playersAreAlreadyRegistered(team)){
            showAlert("A Participant or more is already registered in the tournament");
            return;
        }
        //// All inputs are valid
        //// Adding team inside Participant and updating data
        for (Paricipant paricipant : team.getPlayers()) {
            paricipant.getTeams().add(team);
            SystemData.updateParticipant(paricipant);
        }
        //// Adding team inside Tournament
        selectedTournament.getRegisteredTeams().add(team);
        //// HERE if tournament is filled close registeration
        if(selectedTournament.getNumOfTeamsIsFixed() && selectedTournament.getRegisteredTeams().size() ==
                selectedTournament.getNumOfTeams()){

                selectedTournament.setIsOpenRegisteration(false);
        }
        ////updating data 
        new SystemData().updateTournament("savedTournaments.dat",selectedTournament.getName(),selectedTournament);
        
        //// Confirm and send email
        showInfoAlert("Team registration form submitted");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Invalid Input");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void backBtn(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DashBoard_participant.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
            
    }
}


