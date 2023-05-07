package participant;
import Login.LoginPage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ProfileController {

    @FXML
    private Label emailStringField;

    @FXML
    private Label firstNameField;

    @FXML
    private Label lastNameField;

    @FXML
    private Label userIdField;

    @FXML
    void initialize() {
        userIdField.setText(LoginPage.participantUser.getUserID());
        lastNameField.setText(LoginPage.participantUser.getLastName());
        firstNameField.setText(LoginPage.participantUser.getFirstName());
        emailStringField.setText(LoginPage.participantUser.getEmailString());
    }
}
