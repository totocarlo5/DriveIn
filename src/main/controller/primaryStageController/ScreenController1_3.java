package main.controller.primaryStageController;

import com.sun.javafx.scene.control.VirtualScrollBar;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import main.controller.Paths;
import main.model.database.entity.Movie;
import main.model.database.entity.Show;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class ScreenController1_3 extends PrimaryStageController implements EventHandler<MouseEvent> {

    @FXML
    GridPane gridPane;

    @FXML
    private void initialize() {
        /*try {
            for (Show show: model.getReservableShows()) {

            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }*/

        
    }

    @Override
    public void handle(MouseEvent mouseEvent) {
    }

    @FXML
    private void back() { goToScreen(Paths.$1_MAIN_SCREEN); }
}
