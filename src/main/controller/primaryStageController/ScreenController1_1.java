package main.controller.primaryStageController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.controller.Paths;
import main.model.PasswordNotMatchException;
import main.model.UserNotFoundException;

import java.sql.SQLException;


public class ScreenController1_1 extends PrimaryStageController {
    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private Label generalError;

    @FXML
    private void login()  {
        try {
            Boolean imputIsValid = true;

            if (userName.getText().isEmpty()) {
                generalError.setText("Inserire l'username");
                imputIsValid = false;
            } else
                generalError.setText("");

            if (password.getText().equals("")) {
                generalError.setText("Inserire la password");
                imputIsValid = false;
            } else
                generalError.setText("");

            if (imputIsValid) {
                model.logInUser(userName.getText(), password.getText());
                goToScreen(Paths.$1_1_1_LOGGED_SCREEN);
            }
        } catch (PasswordNotMatchException | UserNotFoundException exception) {
            generalError.setText(exception.getMessage());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void back() { goToScreen(Paths.$1_MAIN_SCREEN); }
}