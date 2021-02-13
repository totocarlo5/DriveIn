package main.controller.primaryStageController;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import main.controller.Paths;
import main.controller.otherStageController.PopupStageController;
import main.model.database.entity.Reservation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;

public class ScreenController1_1_1 extends PrimaryStageController {

    @FXML
    private Label title;

    private Integer location;

    @FXML
    private void createReservation() {
        goToScreen(Paths.$1_1_1_1_CREATE_RESERVATION);
    }

    @FXML
    private void summaryReservation() {
        try {
            ObservableList<Reservation> userManageableReservations = model.getUserLoggedManagebleReservations();
            if (!userManageableReservations.isEmpty())
                goToScreen(Paths.$1_1_1_2_SUMMARY_RESERVATION);
            else {
                PopupStageController popupStageController = new PopupStageController(PopupStageController.PopupMessage.POPUP_MESSAGE_2);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.POPUP_STAGE.getPath()));
                loader.setController(popupStageController);
                loader.load();
                popupStageController.openPopupStage();
            }
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

        @FXML
    private void createOrder() {
        if (LocalTime.now().isBefore(LocalTime.of(15, 00)) || LocalTime.now().isAfter(LocalTime.of(23, 00))) {
            PopupStageController popupStageController = new PopupStageController(PopupStageController.PopupMessage.POPUP_MESSAGE_3);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.POPUP_STAGE.getPath()));
            loader.setController(popupStageController);
            popupStageController.openPopupStage();
        }
    }

    @FXML
    private void deleteOrder() {
    }

    @FXML
    private void deleteRegistration() {
        goToScreen(Paths.$1_1_1_5_DELETE_REGISTRATION);
    }

    @FXML
    private void logOut() {
        model.logOutUser();
        goToScreen(Paths.$1_MAIN_SCREEN);
    }

    @FXML
    private void initialize() {
        title.setText("Benvenuto: " + model.getUserLoggedFirstName() + " " + model.getUserLoggedLastName());
    }

    public void setLocation(Integer location) { this.location = location; }
}
