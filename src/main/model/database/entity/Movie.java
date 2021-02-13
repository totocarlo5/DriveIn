package main.model.database.entity;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;

import java.sql.SQLException;
import java.time.LocalDate;

public class Movie {
    private final ReadOnlyIntegerProperty idMovie;
    private final ReadOnlyStringProperty title;
    private final ReadOnlyStringProperty genre;
    private final ReadOnlyObjectProperty<LocalDate> exitDate;
    private final ReadOnlyStringProperty direction;
    private final ReadOnlyStringProperty actors;
    private final ReadOnlyStringProperty country;
    private final ReadOnlyIntegerProperty duration;
    private final ReadOnlyStringProperty release;
    private final ReadOnlyStringProperty screenplay;
    private final ReadOnlyStringProperty photography;
    private final ReadOnlyStringProperty editing;
    private final ReadOnlyStringProperty music;
    private final ReadOnlyStringProperty production;
    private final ReadOnlyStringProperty story;
    private final ReadOnlyObjectProperty<Image> poster;
    private final ListProperty<Show> shows;

    protected Movie(Integer id, String title, String genre, LocalDate exitDate, String direction, String actors,
                    String country, Integer duration, String release, String screenplay, String photography,
                    String editing, String music, String production, String story, Image poster) {

        this.idMovie = new SimpleIntegerProperty(this, "idMovie", id);
        this.title = new SimpleStringProperty(this, "title,", title);
        this.genre = new SimpleStringProperty(this, "genre", genre);
        this.exitDate = new SimpleObjectProperty<>(this, "exitDate", exitDate);
        this.direction = new SimpleStringProperty(this, "direction", direction);
        this.actors = new SimpleStringProperty(this, "actors", actors);
        this.country = new SimpleStringProperty(this, "country", country);
        this.duration = new SimpleIntegerProperty(this, "duration", duration);
        this.release = new SimpleStringProperty(this, "release", release);
        this.screenplay = new SimpleStringProperty(this, "screenplay", screenplay);
        this.photography = new SimpleStringProperty(this, "photography", photography);
        this.editing = new SimpleStringProperty(this, "editing", editing);
        this.music = new SimpleStringProperty(this, "music", music);
        this.production = new SimpleStringProperty(this, "production", production);
        this.story = new SimpleStringProperty(this, "story", story);
        this.poster = new SimpleObjectProperty<>(this, "poster", poster);
        shows = new SimpleListProperty<>(this, "shows");
    }

    public int getIdMovie() {
        return idMovie.get();
    }

    public ReadOnlyIntegerProperty idMovieProperty() {
        return idMovie;
    }

    public String getTitle() {
        return title.get();
    }

    public ReadOnlyStringProperty titleProperty() {
        return title;
    }

    public String getGenre() {
        return genre.get();
    }

    public ReadOnlyStringProperty genreProperty() {
        return genre;
    }

    public LocalDate getExitDate() {
        return exitDate.get();
    }

    public ReadOnlyObjectProperty<LocalDate> exitDateProperty() {
        return exitDate;
    }

    public String getDirection() {
        return direction.get();
    }

    public ReadOnlyStringProperty directionProperty() {
        return direction;
    }

    public String getActors() {
        return actors.get();
    }

    public ReadOnlyStringProperty actorsProperty() {
        return actors;
    }

    public String getCountry() {
        return country.get();
    }

    public ReadOnlyStringProperty countryProperty() {
        return country;
    }

    public int getDuration() {
        return duration.get();
    }

    public ReadOnlyIntegerProperty durationProperty() {
        return duration;
    }

    public String getRelease() {
        return release.get();
    }

    public ReadOnlyStringProperty releaseProperty() {
        return release;
    }

    public String getScreenplay() {
        return screenplay.get();
    }

    public ReadOnlyStringProperty screenplayProperty() {
        return screenplay;
    }

    public String getPhotography() {
        return photography.get();
    }

    public ReadOnlyStringProperty photographyProperty() {
        return photography;
    }

    public String getEditing() {
        return editing.get();
    }

    public ReadOnlyStringProperty editingProperty() {
        return editing;
    }

    public String getMusic() {
        return music.get();
    }

    public ReadOnlyStringProperty musicProperty() {
        return music;
    }

    public String getProduction() {
        return production.get();
    }

    public ReadOnlyStringProperty productionProperty() {
        return production;
    }

    public String getStory() {
        return story.get();
    }

    public ReadOnlyStringProperty storyProperty() {
        return story;
    }

    public ObservableList<Show> getShows() throws SQLException {
        return shows.get();
    }

    public ListProperty<Show> showsProperty() {
        return shows;
    }

    public void setShows(ObservableList<Show> shows) {
        this.shows.set(shows);
    }

    public Image getPoster() {
        return poster.get();
    }

    public ReadOnlyObjectProperty<Image> posterProperty() {
        return poster;
    }
}
