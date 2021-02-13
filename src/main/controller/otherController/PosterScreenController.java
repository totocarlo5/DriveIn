package main.controller.otherController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import main.model.database.entity.Show;

public class PosterScreenController extends VBox {

    @FXML
    private ImageView poster;

    @FXML
    private Text titleText;

    @FXML
    private Text linkMovieProfileText;

    @FXML
    private Text genreText;

    @FXML
    private Text showDateText;

    @FXML
    private Text timeDateText;

    private Show show;

    public PosterScreenController(Show show) {
        this.show = show;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../../view/screenshots/otherScreenshots/posterView.fxml"));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
    }

    @FXML
    void initialize() {


    }
}
