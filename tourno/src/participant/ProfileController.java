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
        userIdField.setText(LoginPage.getParticipantUser().getUserID());
        lastNameField.setText(LoginPage.getParticipantUser().getLastName());
        firstNameField.setText(LoginPage.getParticipantUser().getFirstName());
        emailStringField.setText(LoginPage.getParticipantUser().getEmailString());
    }
}
