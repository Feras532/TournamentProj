package admin;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Optional;

import Classes.Elimination;
import Classes.Paricipant;
import Classes.RoundRobin;
import Classes.SystemData;
import Classes.Team;
import Classes.Tournament;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class futureController {
    @FXML
    private Label durationRounds;
    @FXML
    private ImageView back;

    @FXML
    private Label date;

    @FXML
    private Label registered;

    @FXML
    private ImageView start;

    @FXML
    private Label tourName;
    @FXML
    private Label maximumTeams;

    @FXML
    void initialize() throws IOException {
        Tournament tournament = controller_DashBoard_admin.selectedTournament;
        buttonsInteraction(start);
        buttonsInteraction(back);
        tourName.setText(tournament.getName());
        date.setText("Starting: " + tournament.getStartdate() + ", Ending: " + tournament.getEndDate());
        registered.setText(tournament.getNumRegisteredTeams() + "");
        maximumTeams.setText(tournament.getNumOfTeams() + "");
        durationRounds.setText(tournament.getNumOfDays()+" Days.");

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

    private Stage stage;
    private Scene scene;

    @FXML
    void clickedReturn(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    @FXML
    void clickedStart(MouseEvent event) throws IOException {
        Tournament tournament = controller_DashBoard_admin.selectedTournament;
        // try {
        //     // create an ObjectInputStream to read the serialized data from the file
        //     ObjectInputStream input = new ObjectInputStream(new FileInputStream("savedParticipants.dat"));
        //     // cast the object read from the file to an ArrayList<Participant>
        //     ArrayList<Paricipant> participants = (ArrayList<Paricipant>) input.readObject();
        //     ArrayList<Team> teams = new ArrayList<>();
        //     int i = 0;
        //     // iterate over the participants and register them in the tournament
        //     for (Paricipant participant : participants) {
        //         // create a new team with the participant as its captain
        //         Team team = new Team(tournament, participant.getLastName(), participant);
        //         // add the team to the tournament's registered teams
        //         teams.add(team);
        //         if (i == 4){
        //             break;
        //         }
        //         i++;
                
        //     }
        //     System.out.println(teams.size());
            // save the updated tournament to the file
            // tournament.setRegisteredTeams(teams);
            save(tournament);
        
        //// =======================================================
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Start Tournament Confirmation");
        alert.setHeaderText("Are you sure you want to start the tournament?");
        alert.setContentText("note: Tournament will be displayed under 'Ongoing'. ");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            if (tournament instanceof Elimination) {

                Elimination elimination = (Elimination) tournament;
                if (elimination.getNumRegisteredTeams() >= 2) {
                    elimination.generateMatches();
                    elimination.setIsActive(true);
                    elimination.setIsOpenRegisteration(false);
                    elimination.setRoundDates();
                    Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);

                    stage.show();
                    save(elimination);
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Start Tournament Error");
                    alert.setHeaderText("Registered teams are less than 2");
                    result = alert.showAndWait();
                }
                // use methods of Elimination class on elimination object
            } else if (tournament instanceof RoundRobin) {
                RoundRobin roundRobin = (RoundRobin) tournament;
                if (roundRobin.getNumRegisteredTeams() >= 2) {
                    roundRobin.generateMatches();
                    roundRobin.setRoundDates();
                    tournament.setIsActive(true);
                    tournament.setIsOpenRegisteration(false);
                    Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                    save(roundRobin);
                }
                else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Start Tournament Error");
                    alert.setHeaderText("Registered teams are less than 2");
                    result = alert.showAndWait();
                }
            }

        }

    }

    // static Tournament tournament = controller_DashBoard_admin.selectedTournament;

    static void save(Tournament tournament) {

        new SystemData().updateTournament("savedTournaments.dat", tournament.getName(), tournament);
        System.out.println("tournament is saved with number of teams" + tournament.getNumRegisteredTeams());

    }
}
