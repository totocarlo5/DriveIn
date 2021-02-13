package main.model.database.dto;

import javafx.scene.image.Image;

import java.time.LocalDate;

public class MovieDTO {
    private Integer idMovie;
    private String title;
    private String genre;
    private LocalDate exitDate;
    private String direction;
    private String actors;
    private String country;
    private Integer duration;
    private String release;
    private String screenplay;
    private String photography;
    private String editing;
    private String music;
    private String production;
    private String story;
    private Image poster;

    public MovieDTO() {
        this(0);
    }

    public MovieDTO(int idMovie) {
        this(idMovie, null, null, null, null, null, null, 0,
                null, null, null, null, null, null, null, null
        );
    }

    public MovieDTO(int idMovie, String title, String genre, LocalDate exitDate, String direction,
                    String actors, String country, int duration, String release, String screenplay,
                    String photography, String editing, String music, String production, String story, Image poster) {

        this.idMovie = idMovie;
        this.title = title;
        this.genre = genre;
        this.exitDate = exitDate;
        this.direction = direction;
        this.actors = actors;
        this.country = country;
        this.duration = duration;
        this.release = release;
        this.screenplay = screenplay;
        this.photography = photography;
        this.editing = editing;
        this.music = music;
        this.production = production;
        this.story = story;
        this.poster = poster;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public MovieDTO setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MovieDTO setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public MovieDTO setGenre(String genre) {
        this.genre = genre;
        return this;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public MovieDTO setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
        return this;
    }

    public String getDirection() {
        return direction;
    }

    public MovieDTO setDirection(String direction) {
        this.direction = direction;
        return this;
    }

    public String getActors() {
        return actors;
    }

    public MovieDTO setActors(String actors) {
        this.actors = actors;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public MovieDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public Integer getDuration() {
        return duration;
    }

    public MovieDTO setDuration(Integer duration) {
        this.duration = duration;
        return this;
    }

    public String getRelease() {
        return release;
    }

    public MovieDTO setRelease(String release) {
        this.release = release;
        return this;
    }

    public String getScreenplay() {
        return screenplay;
    }

    public MovieDTO setScreenplay(String screenplay) {
        this.screenplay = screenplay;
        return this;
    }

    public String getPhotography() {
        return photography;
    }

    public MovieDTO setPhotography(String photography) {
        this.photography = photography;
        return this;
    }

    public String getEditing() {
        return editing;
    }

    public MovieDTO setEditing(String editing) {
        this.editing = editing;
        return this;
    }

    public String getMusic() {
        return music;
    }

    public MovieDTO setMusic(String music) {
        this.music = music;
        return this;
    }

    public String getProduction() {
        return production;
    }

    public MovieDTO setProduction(String production) {
        this.production = production;
        return this;
    }

    public String getStory() {
        return story;
    }

    public MovieDTO setStory(String story) {
        this.story = story;
        return this;
    }

    public Image getPoster() {
        return poster;
    }

    public MovieDTO setPoster(Image poster) {
        this.poster = poster;
        return this;
    }
}
