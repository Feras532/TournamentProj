package admin;

import javafx.scene.layout.Region;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Classes.Elimination;
import Classes.RoundRobin;
import Classes.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
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
            stage.sizeToScene();

            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);

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

    public static Tournament selectedTournament;

    @FXML // this method to move scene after selecting a row from the table view.
    void selectTournament_fromSchedule(MouseEvent event) throws IOException, ClassNotFoundException {

        Tournament selected = tableView.getSelectionModel().getSelectedItem();
        if (selected instanceof Elimination) {
            FileInputStream fileIn = new FileInputStream("savedTournaments.dat");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ArrayList<Tournament> tournaments = (ArrayList<Tournament>) in.readObject();
            for (Tournament s : tournaments) {
                if (s.getName().equals(selected.getName())) {
                    selectedTournament = (Elimination) s; // save the seleceted tournament from the tableView in
                                                          // "selectedTournament"
                    // so I can work on this variable in controllerElimination.java
                    System.out.println(
                            selectedTournament.getName() + " teams : " + selectedTournament.getNumRegisteredTeams());

                    break;
                }
            }
            in.close();
            fileIn.close();
            if (selectedTournament.getIsActive() && selectedTournament.getIsOpenRegisteration() == false) {
                Parent root = FXMLLoader.load(getClass().getResource("elimination/elimination.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                // stage.setMaximized(true);
                stage.show();
            } else if (selectedTournament.getIsActive() == false && selectedTournament.getIsCompleted() == false) {
                Parent root = FXMLLoader.load(getClass().getResource("future.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                Parent root = FXMLLoader.load(getClass().getResource("elimination/elimination.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                // stage.setMaximized(true);
                stage.show();
            }
        } else if (selected instanceof RoundRobin) {
            System.out.println("put roundrobin method here");
            ////////////////////
        }

    }

    @FXML
    void ongoing(ActionEvent event) {

        ObservableList<Tournament> observableList = FXCollections.observableArrayList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedTournaments.dat"))) {
            ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();

            for (Tournament s : list) { // display the active tournaments only
                if (s.getIsActive() == true && s.getIsOpenRegisteration() == false && s.getIsCompleted() == false)
                    observableList.add(s);
            }
            // observableList.addAll(list);
            name.setCellValueFactory(new PropertyValueFactory<Tournament, String>("name"));
            game.setCellValueFactory(new PropertyValueFactory<Tournament, String>("game"));
            status.setCellValueFactory(new PropertyValueFactory<Tournament, String>("status"));
            type.setCellValueFactory(new PropertyValueFactory<Tournament, String>("type"));
            registeredToMax.setCellValueFactory(new PropertyValueFactory<Tournament, String>("registeredToMax"));
            tableView.setItems(observableList);
            if (observableList.isEmpty())
                alertNotFound();// if the table is empty

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void past(ActionEvent event) {

        ObservableList<Tournament> observableList = FXCollections.observableArrayList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedTournaments.dat"))) {
            ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();

            for (Tournament s : list) { // display the active tournaments only
                if (s.getIsActive() == false && s.getIsOpenRegisteration() == false && s.getIsCompleted() == true)
                    observableList.add(s);
            }
            // observableList.addAll(list);
            name.setCellValueFactory(new PropertyValueFactory<Tournament, String>("name"));
            game.setCellValueFactory(new PropertyValueFactory<Tournament, String>("game"));
            status.setCellValueFactory(new PropertyValueFactory<Tournament, String>("status"));
            type.setCellValueFactory(new PropertyValueFactory<Tournament, String>("type"));
            registeredToMax.setCellValueFactory(new PropertyValueFactory<Tournament, String>("registeredToMax"));
            tableView.setItems(observableList);
            if (observableList.isEmpty())
                alertNotFound();// if the table is empty

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void future(ActionEvent event) {
        ObservableList<Tournament> observableList = FXCollections.observableArrayList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedTournaments.dat"))) {
            ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();

            for (Tournament s : list) { // display the active tournaments only
                if (s.getIsActive() == false && s.getIsCompleted() == false)
                    observableList.add(s);
            }
            // observableList.addAll(list);
            name.setCellValueFactory(new PropertyValueFactory<Tournament, String>("name"));
            game.setCellValueFactory(new PropertyValueFactory<Tournament, String>("game"));
            status.setCellValueFactory(new PropertyValueFactory<Tournament, String>("status"));
            type.setCellValueFactory(new PropertyValueFactory<Tournament, String>("type"));
            registeredToMax.setCellValueFactory(new PropertyValueFactory<Tournament, String>("registeredToMax"));
            tableView.setItems(observableList);

            if (observableList.isEmpty())
                alertNotFound(); // if the table is empty
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void alertNotFound() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Could not found");
        alert.setHeaderText("No tournament found");

        Optional<ButtonType> result = alert.showAndWait();

    }
    // ☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️☝️

    // 👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇

    // 👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇👇

    private void loadView(String fxmlPath) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource(fxmlPath));
        view.setMinWidth(0);
        view.setMinHeight(0);
        // view.setPrefWidth(600);
        // view.setPrefHeight(800);
        view.setMaxWidth(Double.MAX_VALUE);
        view.setMaxHeight(Double.MAX_VALUE);
        borderpane.setCenter(view);
        borderpane.setAlignment(view, Pos.CENTER);

    }

    @FXML
    void viewTournament(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void createTournament(ActionEvent event) throws IOException {
        loadView("createTournaments.fxml");
    }

    @FXML
    void manageTournament(ActionEvent event) throws IOException {
        loadView("manageTournaments.fxml");
    }

    @FXML
    void profile(ActionEvent event) throws IOException {
        loadView("profile.fxml");
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
        loadView("addGame.fxml");

    }

    @FXML
    void scene_addingGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newGameScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void initialize() {
        fake_CSS_styler(btn_logout);
        fake_CSS_styler(btn_AddNewGame);
        fake_CSS_styler(btn_createTournament);
        fake_CSS_styler(btn_manage);
        fake_CSS_styler(btn_profile);
        fake_CSS_styler(btn_viewTournament);

    }

    @FXML
    private Button btn_AddNewGame;
    @FXML
    private Button btn_createTournament;
    @FXML
    private Button btn_manage;
    @FXML
    private Button btn_profile;
    @FXML
    private Button btn_viewTournament;

    public void fake_CSS_styler(Button btn) {
        btn.setOnMouseEntered(event -> {
            btn.setStyle("-fx-background-color: #86a6b5;");
        });
        btn.setOnMouseExited(event -> {
            btn.setStyle("-fx-background-color:  #5b6e78");
        });

    }

}
