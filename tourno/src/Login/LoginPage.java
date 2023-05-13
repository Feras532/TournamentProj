package Login;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginPage {
    private Stage stage;
    private Scene scene;

    @FXML
    private ResourceBundle resources;
    private static Admin adminUser = new Admin("Admin");
    // 7032,8809,student,Abdulmjeed.alothman222@gmail.com,"TURAIKI, DHAFER"
    private static Paricipant participantUser;
    
    @FXML
    private URL location;
    
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    public void LoginAdmin(ActionEvent event) throws Exception {
  
        // Parent root = FXMLLoader.load(getClass().getResource("/admin/DashBoard_admin.fxml"));
        // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        // scene = new Scene(root);
        // stage.setScene(scene);
        // stage.setMaximized(false);
        // stage.setMaximized(true);
        // stage.show();


        Parent newPage = FXMLLoader.load(getClass().getResource("/admin/DashBoard_admin.fxml"));
        Scene newScene = new Scene(newPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(newScene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        appStage.setX(bounds.getMinX());
        appStage.setY(bounds.getMinY());
        appStage.setWidth(bounds.getWidth());
        appStage.setHeight(bounds.getHeight());

        appStage.show();
    }

    @FXML
    public void LoginParticipant(ActionEvent event) throws Exception {
  
        Parent newPage = FXMLLoader.load(getClass().getResource("/participant/DashBoard_par.fxml"));
        Scene newScene = new Scene(newPage);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(newScene);

        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        appStage.setX(bounds.getMinX());
        appStage.setY(bounds.getMinY());
        appStage.setWidth(bounds.getWidth());
        appStage.setHeight(bounds.getHeight());

        appStage.show();
    }

    @FXML
    void LoginBtn(ActionEvent event) {
        try{
            //API URL
            String urlString = " https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn";
            String usernameString = usernameField.getText();
            String username = "?username=" + usernameString;
            String password = "&password=" + passwordField.getText();
            urlString = urlString + username + password;

            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
        
            // Make the request and get the response status code
            int statusCode = connection.getResponseCode();
        
            if (statusCode == 200) {
                // Success, User Found
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                //Close the scanner
                scanner.close();

                System.out.println(informationString);

                //JSON to Object
                JSONParser parse = new JSONParser();
                JSONObject dataObject = (JSONObject) parse.parse(String.valueOf(informationString));
                

                if (dataObject.get("type").equals("admin")) {
                    // Admin login
                    adminUser = new Admin(usernameString);
                    LoginAdmin(event);
                } else {
                    // Participant login
                    participantUser = SystemData.getParticipant(usernameString);
                    LoginParticipant(event);
                }
                

            } else if (statusCode == 403) {
                // Username or Password is wrong
                showAlert("Username or Password is wrong");
            } else if (statusCode == 400) {
                // Missing parameters
                showAlert("Missing parameters");
            }   
        }catch(Exception e){
        e.printStackTrace();
        }
    }
    

    @FXML
    void initialize() {
        if(! doesFileExist("savedParticipants.dat")){
            SystemData.loadParicipantsCSV("StudentDetails.csv");
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Login Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static Paricipant getParticipantUser() {
        return participantUser;
    }

    public static void setParticipantUser(Paricipant participantUser) {
        LoginPage.participantUser = participantUser;
    }

    public static Admin getAdminUser() {
        return adminUser;
    }

    public static void setAdminUser(Admin adminUser) {
        LoginPage.adminUser = adminUser;
    }

    public static boolean doesFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
