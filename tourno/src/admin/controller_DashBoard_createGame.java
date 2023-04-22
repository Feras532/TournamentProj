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

public class controller_DashBoard_createGame{
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private BorderPane borderpane;

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
        if (individualCheck.isSelected()){
            teamCheck.setSelected(false);
            minTextField.setVisible(false);
            maxTextField.setVisible(false);
        }
    }

    @FXML
    private void handleTeamCheck() {
        if (teamCheck.isSelected()){
            individualCheck.setSelected(false);
            minTextField.setVisible(true);
            maxTextField.setVisible(true);
        }
        else{
            minTextField.setVisible(false);
            maxTextField.setVisible(false);
        }

    }

    @FXML
    public void createGame(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException    {

        try{
            String gameName = gameTextField.getText();
            int minNum = 0;
            int maxNum = 0;
            Boolean isTeamGame = null;
    
            if(teamCheck.isSelected()){
            isTeamGame = true;
            minNum = Integer.parseInt(minTextField.getText());
            maxNum = Integer.parseInt(maxTextField.getText());    
        }
            if(individualCheck.isSelected()){
            isTeamGame = false;
            minNum =1;
            maxNum =1;
                    }
           
            if((gameName.isEmpty())){
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("Game Name is not intialized");
                checkAlert.showAndWait();
            }
           
            else if(isTeamGame == null){
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("Please check Team type individual or team-based");
                checkAlert.showAndWait();
            }
            else if(minNum>maxNum){
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("the minimum number of teams is more than the maximum number of teams");
                checkAlert.showAndWait();
            }
           else if(checkDuplication(gameName) == false){
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                 checkAlert.setHeaderText("Game Name is already intialized");
                 checkAlert.showAndWait();
             }
             else if(minNum == 0 || maxNum ==0){
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                 checkAlert.setHeaderText("min and max number of members should be more than 0");
                 checkAlert.showAndWait();
             }
        
            else {
                if(isTeamGame &  minNum == 1 & maxNum == 1){
                    isTeamGame = false;
                }
                Game game = new Game(gameName, isTeamGame, minNum, maxNum);
                new SystemData().addNewGame(game);
                Alert checkAlert = new Alert(AlertType.INFORMATION);
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
        catch(FileNotFoundException e){

        }

    }

    public Boolean checkDuplication(String game) throws IOException, ClassNotFoundException{
        Boolean duplicate = true;
        try (FileInputStream fis = new FileInputStream("savedGames.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
       ArrayList<Game> list = (ArrayList<Game>) ois.readObject();
       for(Game s: list){
        if(s.getName().equals(game)){ //if the name of the game = the object game name return boolean
        duplicate= false;
            break;
}
        else{
        duplicate = true;
    }
    }

        
        }
        catch(FileNotFoundException e){
            FileOutputStream fileOut = new FileOutputStream("savedGames.dat");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.close();
            fileOut.close();
        }
        return duplicate;
    }
}