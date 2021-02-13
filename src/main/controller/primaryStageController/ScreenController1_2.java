package main.controller.primaryStageController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.controller.Paths;
import main.controller.otherStageController.PopupStageController;
import main.model.UsernameAlreadyUsedException;
import main.model.database.dao.mysqldb.MysqlUserProxy;

import java.io.IOException;
import java.sql.SQLException;

public class ScreenController1_2 extends PrimaryStageController {

    @FXML
    private TextField firstName;

    @FXML
    private Label errorFirstName;

    @FXML
    private TextField lastName;

    @FXML
    private Label errorLastName;

    @FXML
    private DatePicker birthDate;

    @FXML
    private TextField fiscalCode;

    @FXML
    private Label errorFiscalCode;

    @FXML
    private TextField phone;

    @FXML
    private TextField username;

    @FXML
    private Label errorUsername;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorPassword;

    @FXML
    private PasswordField password2;

    @FXML
    private Label errorPassword2;

    @FXML
    private void signIn() {

        try {
            boolean imputIsValid = true;

            //controlla se è stato inserito il nome
            if (firstName.getText().isEmpty()) {
                errorFirstName.setText("Inserire il nome");
                imputIsValid = false;
            } else
                errorFirstName.setText("");

            //controlla se è stato inserito il cognome
            if (lastName.getText().isEmpty()) {
                errorLastName.setText("Inserisci il cognome");
                imputIsValid = false;
            } else
                errorLastName.setText("");

            //controlla se è stato inserito il codice fiscale
            if (fiscalCode.getText().isEmpty()) {
                errorFiscalCode.setText("Inserire il codice fiscale");
                imputIsValid = false;
            } else
                errorFiscalCode.setText("");

            //controlla se è stato inserito l'username
            if (username.getText().isEmpty()) {
                errorUsername.setText("Inserisci l'username");
                imputIsValid = false;
            } else
                errorUsername.setText("");

            //controlla se è stato inserita la password
            if (password.getText().isEmpty()) {
                errorPassword.setText("Inserire la password");
                imputIsValid = false;
            } else
                errorPassword.setText("");

            //controlla se è stata confermata la password
            if (password2.getText().isEmpty()) {
                errorPassword2.setText("Reinserire la password");
                imputIsValid = false;
            } else
                errorPassword2.setText("");

            //controlla se la password inserita e quella confermata sono uguali
            if (!password.getText().equals(password2.getText())) {
                errorPassword2.setText("La password reinserita non corrisponde a quella inserita sopra");
                imputIsValid = false;
            } else
                errorPassword2.setText("");

            //se tutti i dati inserti sono corretti creo l'oggetto user da salvare nel database e lo inizializzo con i dati inseriti
            if (imputIsValid) {
               model.registerUser(
                       new MysqlUserProxy(
                               username.getText(),
                               password.getText(),
                               firstName.getText(),
                               lastName.getText(),
                               fiscalCode.getText(),
                               birthDate.getValue(),
                               phone.getText().isEmpty() ? null : this.phone.getText()
                       )
               );

               //apro una finestra di popup per comunicare l'avvenuta registrazione
               PopupStageController popupStageController = new PopupStageController(PopupStageController.PopupMessage.POPUP_MESSAGE_1);
               FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.POPUP_STAGE.getPath()));
               loader.setController(popupStageController);
               loader.load();
               popupStageController.openPopupStage();
            }
        } catch (UsernameAlreadyUsedException exception) {
            errorUsername.setText(exception.getMessage());
        } catch (SQLException | IOException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void back() { goToScreen(Paths.$1_MAIN_SCREEN); }
}
