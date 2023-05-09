package admin;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.scene.Node;
import Classes.SystemData;
import Classes.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class controller_manageTournament {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button deleteTournBtn;

    @FXML
    private TextField durationField;

    @FXML
    private Button editTournBtn;

    @FXML
    private TextField endField;

    @FXML
    private ComboBox<String> enterTournamentCombo;

    @FXML
    private TextField nameField;

    @FXML
    private TextField startDateField;
    @FXML
    private CheckBox durationBox;
    @FXML
    private CheckBox isActiveBox;

    @FXML
    private CheckBox isOpenBox;

    @FXML
    void isActiveHandler(ActionEvent event) {
        if(isActiveBox.isSelected())
        isOpenBox.setSelected(false);;
    }

    @FXML
    void isOpenHandler(ActionEvent event) {
        if(isOpenBox.isSelected())
        isActiveBox.setSelected(false);;
    }

    
    @FXML
    void handleDurationBox(ActionEvent event) {
        if(durationBox.isSelected())
        durationField.setVisible(true);
        else 
        durationField.setVisible(false);
    }


    @FXML
    void deleteTournamentBtn(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
        String tournamentCombo = enterTournamentCombo.getValue();
        Tournament tournament = checkClass(tournamentCombo);
        if (tournament == null){
            Alert checkAlert = new Alert(AlertType.WARNING);
            checkAlert.setTitle("Error");
            checkAlert.setHeaderText("tournament is not intialized");
            checkAlert.showAndWait();
            return;
        }
        new SystemData().deleteTournament(tournament);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("The tournament was deleted successfully");
        alert.showAndWait();
        Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setMaximized(true);
        stage.show();
    }

    @FXML
    void editTournamentBtn(ActionEvent event) throws FileNotFoundException, ClassNotFoundException, IOException {
        try {
            int duration;
            String tournamentName = nameField.getText();
            String startDate = startDateField.getText();
            String endDate = endField.getText();
            String tournamentCombo = enterTournamentCombo.getValue();
            Tournament tournament = checkClass(tournamentCombo);
            Tournament tournament2 = checkClass(tournamentCombo);
    
            if (tournament2 == null) {
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("tournament is not initialized");
                checkAlert.showAndWait();
                return;
            } else if (checkDuplication(tournamentName) == false) {
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("Tournament Name is already initialized");
                checkAlert.showAndWait();
                return;
            }
    
            if (durationBox.isSelected()) {
                duration = Integer.parseInt(durationField.getText());
            } else {
                duration = tournament2.getNumOfDays();
            }
    
            if (isActiveBox.isSelected() && tournament2.getIsActive() == true) {
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("tournament is already active");
                checkAlert.showAndWait();
                return;
            }
    
            if (isActiveBox.isSelected()) {
                tournament2.setIsActive(true);
                tournament2.setIsOpenRegisteration(false);
            }
            
            if (isOpenBox.isSelected() && tournament2.getIsOpenRegisteration() == true) {
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("tournament is already Open For Registration");
                checkAlert.showAndWait();
                return;
            }
    
            if (isOpenBox.isSelected() && !tournament2.getIsActive()) {
                tournament2.setIsOpenRegisteration(true);
            }
    
            if (duration != tournament2.getNumOfDays()) {
                duration = Integer.parseInt(durationField.getText());
            }
    
            if (!tournamentName.isEmpty()) {
                tournament2.setName(tournamentName);
            }
    
            if (tournament2 != null) {
                tournament2.setNumOfDays(duration);
            }
    
            if (!startDate.isEmpty()) {
                tournament2.setStartDate(startDate);
            }
    
            if (!endDate.isEmpty()) {
                tournament2.setEndDate(endDate);
            }
    
            if (isOpenBox.isSelected() && tournament2.getIsActive()) {
                Alert checkAlert = new Alert(AlertType.WARNING);
                checkAlert.setTitle("Error");
                checkAlert.setHeaderText("tournament is already active and cannot be open for registration");
                checkAlert.showAndWait();
                return;
            }
    
            new SystemData().editTournament(tournament, tournament2);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Changes were made Successfully");
            alert.showAndWait();
            Parent root = FXMLLoader.load(getClass().getResource("DashBoard_admin.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setMaximized(false);
        stage.setMaximized(true);
            stage.show();
        } catch (NumberFormatException e) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("The duration is required and it needs to be inputted as numbers");
            alert.showAndWait();
        }
        
        
    }
    

    @FXML
    void tournamentBox(ActionEvent event){

        }
    

    Tournament checkClass(String tournament) throws FileNotFoundException, IOException, ClassNotFoundException{ // a class to get the object from the String of the game
        try (FileInputStream fis = new FileInputStream("savedTournaments.dat");
        ObjectInputStream ois = new ObjectInputStream(fis)) {
       ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();
       for(Tournament s: list){
        if(s.getName().equals(tournament)) //if the name of the tournament = the object tournament name return the > object
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

    @FXML
    void initialize() throws IOException {
      try (FileInputStream fis = new FileInputStream("savedTournaments.dat");
       ObjectInputStream ois = new ObjectInputStream(fis)) {
      ArrayList<Tournament> list = (ArrayList<Tournament>) ois.readObject();
      ArrayList<String> list1 = new ArrayList<String>();
      for (Tournament s : list) {
          list1.add(s.getName());
      }
      for(String s: list1)
      enterTournamentCombo.getItems().addAll(s);
      
  } catch (IOException | ClassNotFoundException e) {
   Alert alert = new Alert(AlertType.INFORMATION);
   alert.setTitle("Could not found");
   alert.setHeaderText("No Tournaments available, Please try creating a new tournament");
   alert.showAndWait();
}
  } 

}
