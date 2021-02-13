package main.controller.primaryStageController;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.controller.Paths;

public class ScreenController1_1_1_1 extends PrimaryStageController implements EventHandler<ActionEvent> {
    private TableCell<Button, Button> cell;

    @FXML
    private TableView table;

    @FXML
    private TableColumn movieColumn;

    @FXML
    private TableColumn dateColumn;

    @FXML
    private TableColumn timeColumn;

    @FXML
    private TableColumn locationColumn;

    @FXML
    private TableColumn selectLocationColumn;

    @FXML
    private TableColumn numberLocationColumn;

    @FXML
    private TableColumn priceColumn;

    @FXML
    private TableColumn reserveColumn;


    public void back() { goToScreen(Paths.$1_1_1_LOGGED_SCREEN); }

    public void makeReservation() { }

    public void chooseLocation() { }

    @Override
    public void handle(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        switch (button.getId()) {
            case "reserveShow":
                makeReservation();
                break;
            case "selectLocation":
                chooseLocation();
                break;
            }
    }

    @FXML
    private void initialize() { }
}
