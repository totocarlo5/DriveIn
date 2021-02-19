package main.controller.primaryStageController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.controller.Paths;
import main.model.Model;

import java.io.IOException;

public class PrimaryStageController {
    static Stage primaryStage;

    static Model model;

    public PrimaryStageController() {}

    public PrimaryStageController(Stage primaryStage, Model model) {
        PrimaryStageController.primaryStage = primaryStage;
        PrimaryStageController.model = model;
    }

    public void goToScreen(Paths nextScreenPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(nextScreenPath.getPath()));
            PrimaryStageController.primaryStage.getScene().setRoot(root);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void openPrimaryStage() {
        try {
            PrimaryStageController.primaryStage.setTitle("Drive In");
            PrimaryStageController.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(Paths.PRIMARY_STAGE_ICON.getPath())));
            Parent root = FXMLLoader.load(getClass().getResource(Paths.$1_MAIN_SCREEN.getPath()));
            PrimaryStageController.primaryStage.setScene(new Scene(root, 400, 400));
            PrimaryStageController.primaryStage.setFullScreen(true);
            PrimaryStageController.primaryStage.setFullScreenExitHint("");
            PrimaryStageController.primaryStage.show();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    void closePrimaryStage() { PrimaryStageController.primaryStage. close(); }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
