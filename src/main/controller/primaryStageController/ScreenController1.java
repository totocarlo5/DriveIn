package main.controller.primaryStageController;

import javafx.fxml.FXML;
import main.controller.Paths;

public class ScreenController1 extends PrimaryStageController {

    @FXML
    private void login() { goToScreen(Paths.$1_1_LOGIN_SCREEN); }

    @FXML
    private void signIn() { goToScreen(Paths.$1_2_SIGN_IN_SCREEN); }

    @FXML
    private void showsList() { goToScreen(Paths.$1_3_SHOWS_LIST); }

    @FXML
    private void exit() { closePrimaryStage(); }
}
