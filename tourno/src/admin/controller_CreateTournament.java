package admin;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import Classes.Elimination;
import Classes.Game;
import Classes.RoundRobin;
import Classes.SystemData;
import Classes.Tournament;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;
public class controller_CreateTournament {
    private Stage stage;
    private Scene scene;
    private Parent root;

//////////DO NOT TOUCH IT , IT WORKS 🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓
    ///Create Tournament


    @FXML
    private TextField TournamentTextField;

    @FXML
    private CheckBox eliminationBox;

    @FXML
    private CheckBox roundRobinBox;

    @FXML
    private ComboBox<String> enterGameCombo;

    @FXML
    private CheckBox fixedBox;

    @FXML
    private TextField numOfTeamsField;

    @FXML
    private Button createTournBtn;

    @FXML
    private TextField durationField;

    @FXML
    private TextField membersField;

    @FXML
    private DatePicker startPick;

    @FXML
    private DatePicker endPick;

    @FXML
    void gameBox(ActionEvent event) {
        
     

    }

//////////DO NOT TOUCH IT , IT WORKS 🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓

    @FXML
    void createTournamentBtn(ActionEvent event) throws IOException, ClassNotFoundException {  
        try{
        String tournamentName = TournamentTextField.getText();
        String gamecomb = enterGameCombo.getValue();
        int numberOfMembers = Integer.parseInt(membersField.getText());
        int numOfTeams;
        String startDate;
        String endDate;
        if(gamecomb == null){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("game is not intialized");
            checkAlert.showAndWait();
        }
        Game game =checkClass(gamecomb);
        if(startPick.getValue() == null){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("Start Date is not picked");
            checkAlert.showAndWait();
            return;
        }
        else{
         startDate = startPick.getValue().toString();
        }

        if(endPick.getValue() == null){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("End Date is not picked");
            checkAlert.showAndWait();
            return;
        }
        else{
            endDate = endPick.getValue().toString();
        }

       
        int duration = Integer.parseInt(durationField.getText());
        Boolean isFixed = false;
        if(fixedBox.isSelected()){
        numOfTeams = Integer.parseInt(numOfTeamsField.getText());
        isFixed = true;
        }
        else{
        numOfTeams = 0;
        isFixed = false;
    }


        if((tournamentName.isEmpty())){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("Tournament name is not intialized");
            checkAlert.showAndWait();
            return;
        }
        if(checkDateConflict(startDate, endDate) == false){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
             checkAlert.setHeaderText("There is a date conflict");
             checkAlert.showAndWait();
             return; 
        }
        if(checkDuplication(tournamentName) == false) {
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
             checkAlert.setHeaderText("Tournament Name is already intialized");
             checkAlert.showAndWait();
             return;
         }
         if(checkMembers(gamecomb,numberOfMembers) == false){
           return;
         }
        else if((startDate.isEmpty())){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("startdate is not intialized");
            checkAlert.showAndWait();
        }
        else if((endDate.isEmpty())){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("endDate is not intialized");
            checkAlert.showAndWait();
        }
        else if(numOfTeams == 0 & isFixed == true){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("number of teams should be more than 0");
            checkAlert.showAndWait();
        }
         
        else{    
            
            if(eliminationBox.isSelected() && !startDate.isEmpty() && !endDate.isEmpty() && !tournamentName.isEmpty()&& gamecomb != null ){
                Elimination tournament = new Elimination(tournamentName, game, duration, new ArrayList<>(), startDate, endDate, isFixed,numOfTeams,numberOfMembers, true,false, false, new ArrayList<>());
                new SystemData().addNewTournament(tournament);
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Success");
                checkAlert.setHeaderText("an "+ tournament.getType()+" Tournament was created successfully");
                checkAlert.showAndWait(); 
            }
            else if(roundRobinBox.isSelected() && !startDate.isEmpty() && !endDate.isEmpty() && !tournamentName.isEmpty()&& gamecomb != null){
            RoundRobin tournament = new RoundRobin(tournamentName, game, duration, new ArrayList<>(), startDate, endDate, isFixed, numOfTeams,numberOfMembers, true,false, false, new ArrayList<>());
            new SystemData().addNewTournament(tournament);
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Success");
                checkAlert.setHeaderText("a "+ tournament.getType()+" Tournament was created successfully");
                checkAlert.showAndWait(); 
        }
            else if(!eliminationBox.isSelected()&!roundRobinBox.isSelected()){
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("Type of Tournament is not checked");
                checkAlert.showAndWait();
            }
            if((eliminationBox.isSelected()||roundRobinBox.isSelected()) && !startDate.isEmpty() && !endDate.isEmpty() && !tournamentName.isEmpty()&& gamecomb != null){
                Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();  
            }  
        }
      
    }
    catch(NumberFormatException e){
        Alert gameAlert = new Alert(AlertType.WARNING);
        gameAlert.setTitle("Error");
        gameAlert.setHeaderText("Only Numbers can be Entered in number of teams, start/end date/number of members");
        gameAlert.showAndWait();
    }





    }
    //////////DO NOT TOUCH IT , IT WORKS 🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓
    Game checkClass(String game) throws FileNotFoundException, IOException, ClassNotFoundException{ // a class to get the object from the String of the game
        try (FileInputStream fis = new FileInputStream("savedGames.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
       ArrayList<Game> list = (ArrayList<Game>) ois.readObject();
       for(Game s: list){
        if(s.getName().equals(game)) //if the name of the game = the object game name return the > object
        return s;
       }

         
        
    }
        return null;
}

public Boolean checkDuplication(String tournament) throws IOException, ClassNotFoundException{
    Boolean duplicate = true;
    try (FileInputStream fis = new FileInputStream("savedTournaments.dat");
    ObjectInputStream ois = new ObjectInputStream(fis)) {
   ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();
   for(Tournament s: list){
    if(s.getName().equals(tournament)){ //if the name of the game = the object game name return boolean
        duplicate= false;
        break;
}
    else{
    duplicate = true;
}
}

    
    }
    catch(FileNotFoundException e){
        FileOutputStream fileOut = new FileOutputStream("savedTournaments.dat");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.close();
        fileOut.close();
    }
    return duplicate;
}

public boolean checkMembers(String game,int number) throws FileNotFoundException, IOException, ClassNotFoundException{
    Boolean inRange = false;
    int min;
    int max;
    try (FileInputStream fis = new FileInputStream("savedGames.dat");
    ObjectInputStream ois = new ObjectInputStream(fis)) {
   ArrayList<Game> list = (ArrayList<Game>) ois.readObject();
   for(Game s: list){
    if(s.getName().equals(game)){
        if((s.getMinTeamSize() <= number)&(number<= s.getMaxTeamSize())){
            inRange = true;
        }
        else{
            min = s.getMinTeamSize();
            max =s.getMaxTeamSize();
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
             checkAlert.setHeaderText(s.getName() + " number of team members should be between " + min + " and "+ max);
             checkAlert.showAndWait();  
        }
    } 
    
   }
}

            return inRange;
}

public static boolean checkDateConflict(String startDate, String endDate) {
    // Split the input date strings into year, month, and day components
    String[] startComponents = startDate.split("-");
    String[] endComponents = endDate.split("-");
    
    // Parse the components as integers
    int startYear = Integer.parseInt(startComponents[0]);
    int startMonth = Integer.parseInt(startComponents[1]);
    int startDay = Integer.parseInt(startComponents[2]);
    int endYear = Integer.parseInt(endComponents[0]);
    int endMonth = Integer.parseInt(endComponents[1]);
    int endDay = Integer.parseInt(endComponents[2]);

    
    // Compare the dates in "YYYY-MM-DD" order (earlier dates should be smaller)
    if (startYear < endYear || (startYear == endYear && startMonth < endMonth) ||
            (startYear == endYear && startMonth == endMonth && startDay <= endDay)) {
        return true;
    } else {
        return false;
    }
}


    @FXML
    void handleElimination(ActionEvent event) {
        if(eliminationBox.isSelected())
        roundRobinBox.setSelected(false);
    }

    @FXML
    void handleFixed(ActionEvent event) {
        if(fixedBox.isSelected())
        numOfTeamsField.setVisible(true);
        else 
        numOfTeamsField.setVisible(false);

    }

    @FXML
    void handleRoundRobin(ActionEvent event) {
        if(roundRobinBox.isSelected())
        eliminationBox.setSelected(false);
    }
//////////DO NOT TOUCH IT , IT WORKS 🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓🤓
    @FXML
     void initialize() {
       try (FileInputStream fis = new FileInputStream("savedGames.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
       ArrayList<Game> list = (ArrayList<Game>) ois.readObject();
       ArrayList<String> list1 = new ArrayList<String>();
       for (Game s : list) {
           list1.add(s.getName());
       }
       for(String s: list1)
       enterGameCombo.getItems().addAll(s);
       
   } catch (IOException | ClassNotFoundException e) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Could not found");
    alert.setHeaderText("No games available, Please try creating a new Game");
     alert.showAndWait();}
   } 
       
              
    }

