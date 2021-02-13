package main.controller.otherStageController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.controller.Paths;
import main.controller.primaryStageController.PrimaryStageController;

import java.io.IOException;

public class PopupStageController {

    public enum PopupMessage {
        POPUP_MESSAGE_1("Registrazione avvenuta con successo"),
        POPUP_MESSAGE_2("Non ci sono prenotazioni effettuate!"),
        POPUP_MESSAGE_3("Il drive in è chiuso!"),
        POPUP_MESSAGE_4("Non si è abilitati per questa operazione. Rivolgersi alla cassa!"),
        POPUP_MESSAGE_5("Non ci sono ordinazioni effettuate!");

        private final String popupMessage;

        PopupMessage(String popupMessage) {
            this.popupMessage = popupMessage;
        }

        public String getPopupMessage() {
            return popupMessage;
        }
    }

    private PrimaryStageController primaryStageController = new PrimaryStageController();

    private PopupMessage popupMessage;

    @FXML
    private Label title;

    @FXML
    private Label ok;

    @FXML
    private Stage popupStage;

    public PopupStageController(PopupMessage popupMessage) {
        this.popupMessage = popupMessage;
    }

    @FXML
    private void initialize() {
        title.setText(popupMessage.getPopupMessage());
    }

    @FXML
    private void ok() throws IOException {
        if (popupMessage == PopupMessage.POPUP_MESSAGE_1)
            primaryStageController.goToScreen(Paths.$1_MAIN_SCREEN);

        if (popupMessage == PopupMessage.POPUP_MESSAGE_2 || popupMessage == PopupMessage.POPUP_MESSAGE_3 || popupMessage == PopupMessage.POPUP_MESSAGE_4)
            primaryStageController.goToScreen(Paths.$1_1_1_LOGGED_SCREEN);

        closePopupStage();
    }

    public void openPopupStage() {
        popupStage.initModality(Modality.WINDOW_MODAL);
        popupStage.initStyle(StageStyle.TRANSPARENT);
        popupStage.initOwner(primaryStageController.getPrimaryStage());
        popupStage.show();
    }

    private void closePopupStage() {
        popupStage.close();
    }

}
