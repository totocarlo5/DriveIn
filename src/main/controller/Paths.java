package main.controller;

public enum Paths {
    //path per le schermate della finestra principale
    $1_MAIN_SCREEN("../../view/screenshots/primaryStageScreenshots/1-mainScreen.fxml"),
    $1_1_LOGIN_SCREEN("../../view/screenshots/primaryStageScreenshots/1_1-loginScreen.fxml"),
    $1_1_1_LOGGED_SCREEN("../../view/screenshots/primaryStageScreenshots/1_1_1-loggedScreen.fxml"),
    $1_1_1_1_CREATE_RESERVATION("../../view/screenshots/primaryStageScreenshots/1_1_1_1-createReservation.fxml"),
    $1_1_1_2_SUMMARY_RESERVATION("../../view/screenshots/primaryStageScreenshots/1_1_1_2-summaryReservation.fxml"),
    $1_1_1_3_CREATE_ORDER("../../view/screenshots/primaryStageScreenshots/1_1_1_3-createOrder.fxml"),
    $1_1_1_4_DELETE_ORDER("../../view/screenshots/primaryStageScreenshots/1_1_1_4-deleteOrder.fxml"),
    $1_1_1_5_DELETE_REGISTRATION("../../view/screenshots/primaryStageScreenshots/1_1_1_5-deleteRegistration.fxml"),
    $1_2_SIGN_IN_SCREEN("../../view/screenshots/primaryStageScreenshots/1_2-signInScreen.fxml"),
    $1_3_SHOWS_LIST("../../view/screenshots/primaryStageScreenshots/1_3-showsList.fxml"),

    //path per l'icona della finestra principale
    PRIMARY_STAGE_ICON("../../view/icons/iconaProgetto.png"),

    //path per la finestra di inserimento della postazione
    INSERT_LOCATION_STAGE("../../view/screenshots/otherStageScreenshots/insertLocationStage.fxml"),

    //path per la finsetra di popup
    POPUP_STAGE("../../view/screenshots/otherStageScreenshots/popupStage.fxml");


    private final String path;
    
    Paths(String path) {
        this.path = path; 
    }
    
    public String getPath() {
        return this.path;
    }
}
