package main;

import javafx.application.Application;
import javafx.stage.Stage;
import main.controller.primaryStageController.PrimaryStageController;
import main.model.Model;

public class Main extends Application {

    private static final Model model = new Model();

    @Override
    public void start(Stage primaryStage) throws Exception{
        PrimaryStageController primaryStageController = new PrimaryStageController(primaryStage, model);
        primaryStageController.openPrimaryStage();
    }

    public static void main(String... args) { launch(args); }

    @Override
    public String toString() {
        return super.toString();
    }
}
