package admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import Classes.Game;
import Classes.SystemData;
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

public class controller_DashBoard_addGame {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private BorderPane borderpane;
    @FXML
    private TableView<Game> gameTable;
    @FXML
    private TableColumn<Game, String> nameOfGame;
    @FXML
    private TableColumn<Game, String> sizeOfGame;
    @FXML
    private TableColumn<Game, String> typeOfgame;

   

//😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂😂
    @FXML
    void scene_addGame(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("addGame.fxml"));
        borderpane.setCenter(view);

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
        ObservableList<Game> observableList = FXCollections.observableArrayList();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("savedGames.dat"))) {
            ArrayList<Game> list = (ArrayList<Game>) ois.readObject();
            observableList.addAll(list);
            nameOfGame.setCellValueFactory(new PropertyValueFactory<Game, String>("name"));
            typeOfgame.setCellValueFactory(new PropertyValueFactory<Game, String>("typeOfgame"));
            sizeOfGame.setCellValueFactory(new PropertyValueFactory<Game, String>("sizeOfGame"));
            
            gameTable.setItems(observableList);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(observableList.isEmpty()){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Could not found");
            alert.setHeaderText("No games found");
    
            Optional<ButtonType> result = alert.showAndWait();}

              

    }
}
