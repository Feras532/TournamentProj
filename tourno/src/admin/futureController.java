package admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import Classes.Elimination;
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
    void initialize() throws IOException {

        buttonsInteraction(start);
        buttonsInteraction(back);
        tourName.setText(tournament.getName());
        date.setText("Starting: "+ tournament.getStartdate()+", Ending: "+ tournament.getEndDate());
        registered.setText(tournament.getRegisteredToMax());
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
        // =========================== sample teams registeration number
        // to be deleted:
        ArrayList<Team> teams = new ArrayList<>();
        for (int i = 0; i < 17; i++)
            teams.add(new Team(i));
        tournament.setRegisteredTeams(teams);
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
                elimination.generateMatches();
                tournament.setIsActive(true);
                tournament.setIsOpenRegisteration(false);
                Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                save(elimination);
                // use methods of Elimination class on elimination object
            } else if (tournament instanceof RoundRobin) {
                RoundRobin roundRobin = (RoundRobin) tournament;
                // TODO: by hassan...

            }

        }

    }

    static Tournament tournament = controller_DashBoard_admin.selectedTournament;

    static void save(Tournament tournament) {
        new SystemData().updateTournament("savedTournaments.dat", tournament.getName(), tournament);

    }
}
