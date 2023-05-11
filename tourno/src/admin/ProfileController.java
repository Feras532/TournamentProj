package admin;

import Login.LoginPage;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProfileController {

    @FXML
    private Text usernameText;
    @FXML
    void initialize(){
        usernameText.setText(LoginPage.getAdminUser().getUserID());
    }
}
