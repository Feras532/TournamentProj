package admin;
import java.io.FileInputStream;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;

public class controller_DashBoard_admin{
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


    //*********************************************************************//
    @FXML
    void viewTournament(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    // in view tournament we have 3 buttons: ongoing, past, future.----------------
    @FXML
    void ongoing(ActionEvent event) {

    }

    @FXML
    void past(ActionEvent event) {

    }

    @FXML
    void future(ActionEvent event) {

    }

    //*********************************************************************//



    @FXML
    void scene_addGame(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("addGame.fxml"));
        borderpane.setCenter(view);

    }

    @FXML
    void createTournament(ActionEvent event) throws IOException {
        AnchorPane view = FXMLLoader.load(getClass().getResource("createTournaments.fxml"));
        borderpane.setCenter(view);
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
    void addNewGameScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("newGameScene.fxml"));
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
    private void handleIndividualCheck(){
        if(individualCheck.isSelected())
        teamCheck.setSelected(false);
    }
    @FXML
    private void handleTeamCheck(){
        if(teamCheck.isSelected())
        individualCheck.setSelected(false);
    }

   @FXML
   public void createGame(ActionEvent event) throws IOException  {
        
        try{
        String gameName = gameTextField.getText();
        int minNum = Integer.parseInt(minTextField.getText());
        int maxNum = Integer.parseInt(maxTextField.getText());
        Boolean isTeamGame = null;

        if(teamCheck.isSelected())
        isTeamGame = true;
        if(individualCheck.isSelected())
        isTeamGame = false;

        if((gameName.isEmpty())){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("Game Name is not intialized");
            checkAlert.showAndWait();
        }
        if(isTeamGame == null){
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
        else{
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
      
       
        }
        catch(NumberFormatException e){
            Alert gameAlert = new Alert(AlertType.WARNING);
            gameAlert.setTitle("Error");
            gameAlert.setHeaderText("Only Numbers can be Entered in min/max team size");
            gameAlert.showAndWait();
        
        } 
      

    }



 

    @FXML
     void initialize() {
        
      
        assert btn_logout != null
                : "fx:id=\"btn_logout\" was not injected: check your FXML file 'DashBoard_admin.fxml'.";
              
    }


}
