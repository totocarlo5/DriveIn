package main.controller.primaryStageController;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import main.controller.Paths;
import main.model.PasswordNotMatchException;
import main.model.UserNotFoundException;
import org.controlsfx.control.textfield.CustomPasswordField;
import org.controlsfx.control.textfield.CustomTextField;
import org.controlsfx.control.textfield.TextFields;
import org.controlsfx.glyphfont.FontAwesome;
import org.controlsfx.glyphfont.Glyph;
import org.controlsfx.glyphfont.GlyphFont;
import org.controlsfx.glyphfont.GlyphFontRegistry;

import java.sql.SQLException;


public class ScreenController1_1 extends PrimaryStageController {

    @FXML
    private VBox vBox;

    private CustomTextField userName;

    private CustomPasswordField password;

    private Label usernameError;

    private Label passwordError;

    @FXML
    private void login()  {
        try {
            Boolean imputIsValid = true;

            if (userName.getText().isEmpty()) {
                usernameError.setText("Inserire l'username");
                imputIsValid = false;
            } else
                usernameError.setText("");

            if (password.getText().equals("")) {
                passwordError.setText("Inserire la password");
                imputIsValid = false;
            } else
                passwordError.setText("");

            if (imputIsValid) {
                model.logInUser(userName.getText(), password.getText());
                goToScreen(Paths.$1_1_1_LOGGED_SCREEN);
            }
        } catch (UserNotFoundException exception) {
            usernameError.setText(exception.getMessage());
        } catch (PasswordNotMatchException exception) {
            passwordError.setText(exception.getMessage());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @FXML
    private void back() { goToScreen(Paths.$1_MAIN_SCREEN); }

    @FXML
    private void initialize() {
        userName = (CustomTextField) TextFields.createClearableTextField();
        userName.setFocusTraversable(false);
        userName.setPrefWidth(800);
        Glyph userGlyph = GlyphFontRegistry.font("FontAwesome").create("USER");
        userGlyph.getStyleClass().add("glyph");
        userName.setLeft(userGlyph);

        password = (CustomPasswordField) TextFields.createClearablePasswordField();
        password.setFocusTraversable(false);
        password.setPrefWidth(800);
        Glyph passwordGlyph = GlyphFontRegistry.font("FontAwesome").create("LOCK");
        passwordGlyph.getStyleClass().add("glyph");
        password.setLeft(passwordGlyph);

        usernameError = new Label();
        usernameError.getStyleClass().add("exceptionLabel");

        passwordError = new Label();
        passwordError.getStyleClass().add("exceptionLabel");

        vBox.getChildren().addAll(userName, usernameError, password, passwordError);
    }
}