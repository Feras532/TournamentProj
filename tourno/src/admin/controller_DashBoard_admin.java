package admin;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import Classes.Elimination;
import Classes.Game;
import Classes.RoundRobin;
import Classes.SystemData;
import Classes.Team;
import Classes.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class controller_DashBoard_admin {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private ResourceBundle resources;

    @FXML
    private BorderPane borderpane;

    @FXML
    private URL location;

    @FXML
    private Button btn_logout;

    @FXML /// implement logout button with conformation message.
    public void logout(ActionEvent event) throws Exception {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout Confirmation");
        alert.setHeaderText("Are you sure you want to logout?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            // handle logout
            Parent root = FXMLLoader.load(getClass().getResource("/Login/LoginPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    // 👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇
    @FXML
    private TableView<Tournament> tableView;
    @FXML
    private TableColumn<Tournament, String> name;
    @FXML
    private TableColumn<Tournament, String> game;
    @FXML
    private TableColumn<Tournament, String> type;
    @FXML
    private TableColumn<Tournament, String> status;
    @FXML
    private TableColumn<Tournament, String> registeredToMax;

    @FXML
    void viewTournament(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void ongoing(ActionEvent event) {

        // Brother I am just testing one tournament in the table, so later we will
        // figure it how to deal with binary.
        Game LeagueOfLegends = new Game("LeagueOfLegends", null, 0, 0);
        Game Fifa = new Game("Fifa 15", null, 0, 0);
        Team[] teams = new Team[2];
        teams[0] = new Team(null, null, null, 0, 0, 0, 0, 0, 0, 0);
        teams[1] = new Team(null, null, null, 0, 0, 0, 0, 0, 0, 0);

        ObservableList<Tournament> list = FXCollections.observableArrayList(
                new Elimination("Elimination test", LeagueOfLegends, 0, null, null, null, null, 32, true, true, teams),
                new RoundRobin("Round robin test", Fifa, 0, null, null, null, null, 16, false, false, teams)

        );

        name.setCellValueFactory(new PropertyValueFactory<Tournament, String>("name"));
        game.setCellValueFactory(new PropertyValueFactory<Tournament, String>("game"));
        status.setCellValueFactory(new PropertyValueFactory<Tournament, String>("status"));
        type.setCellValueFactory(new PropertyValueFactory<Tournament, String>("type"));
        registeredToMax.setCellValueFactory(new PropertyValueFactory<Tournament, String>("registeredToMax"));
        tableView.setItems(list);

    }

    @FXML
    void past(ActionEvent event) {

    }

    @FXML
    void future(ActionEvent event) {

    }

    // ☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️



    
    // 👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇

    @FXML
    void scene_addingGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newGameScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    void returnfromGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void scene_addGame(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("addGame.fxml"));
        borderpane.setCenter(view);

    }
    // ☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️


    // 👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇

    @FXML
    void createTournament(ActionEvent event) {

    }

    @FXML
    void manageTournament(ActionEvent event) {

    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("profile.fxml"));
        borderpane.setCenter(view);
    }

    @FXML
    private CheckBox individualCheck;

    @FXML
    private CheckBox teamCheck;

    @FXML
    private TextField maxTextField;

    @FXML
    private TextField minTextField;

    @FXML
    private Button createBtn;

    @FXML
    private TextField gameTextField;

    @FXML
    private void handleIndividualCheck() {
        if (individualCheck.isSelected())
            teamCheck.setSelected(false);
    }

    @FXML
    private void handleTeamCheck() {
        if (teamCheck.isSelected())
            individualCheck.setSelected(false);
    }

    @FXML
    public void createGame(ActionEvent event) throws IOException {

        try {
            String gameName = gameTextField.getText();
            int minNum = Integer.parseInt(minTextField.getText());
            int maxNum = Integer.parseInt(maxTextField.getText());
            Boolean isTeamGame = null;

            if (teamCheck.isSelected())
                isTeamGame = true;
            if (individualCheck.isSelected())
                isTeamGame = false;

            if ((gameName.isEmpty())) {
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("Game Name is not intialized");
                checkAlert.showAndWait();
            }
            if (isTeamGame == null) {
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("Please check Team type individual or team-based");
                checkAlert.showAndWait();
            } else if (minNum > maxNum) {
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("the minimum number of teams is more than the maximum number of teams");
                checkAlert.showAndWait();
            } else {
                Game game = new Game(gameName, isTeamGame, minNum, maxNum);
                new SystemData().addNewGame(game);
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Success");
                checkAlert.setHeaderText("a game was created successfully");
                checkAlert.showAndWait();

                Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }

        } catch (NumberFormatException e) {
            Alert gameAlert = new Alert(AlertType.WARNING);
            gameAlert.setTitle("Error");
            gameAlert.setHeaderText("Only Numbers can be Entered in min/max team size");
            gameAlert.showAndWait();

        }

    }

    /// Create Tournament

    @FXML
    void initialize() {
        assert btn_logout != null
                : "fx:id=\"btn_logout\" was not injected: check your FXML file 'DashBoard_admin.fxml'.";

    }

}
