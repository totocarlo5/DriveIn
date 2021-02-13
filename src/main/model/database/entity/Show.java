package main.model.database.entity;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Show {
    private final ObjectProperty<Movie> movie;
    private final ReadOnlyObjectProperty<LocalDate> date;
    private final ReadOnlyObjectProperty<LocalTime> time;
    private final ReadOnlyFloatProperty price;
    private final ListProperty<Reservation> reservations;

    protected Show(Movie movie, LocalDate date, LocalTime time, Float price) {
        this.movie = new SimpleObjectProperty<>(this, "movie", movie);
        this.date = new SimpleObjectProperty<>(this, "date", date);
        this.time = new SimpleObjectProperty<>(this, "time", time);
        this.price = new SimpleFloatProperty(this, "price", price);
        this.reservations = new SimpleListProperty<>(this, "reservations");
    }

    public Movie getMovie() throws SQLException {
        return movie.get();
    }

    public ObjectProperty<Movie> movieProperty() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie.set(movie);
    }

    public LocalDate getDate() {
        return date.get();
    }

    public ReadOnlyObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public LocalTime getTime() {
        return time.get();
    }

    public ReadOnlyObjectProperty<LocalTime> timeProperty() {
        return time;
    }

    public Float getPrice() {
        return price.get();
    }

    public ReadOnlyFloatProperty priceProperty() {
        return price;
    }

    public ObservableList<Reservation> getReservations() throws SQLException {
        return reservations.get();
    }

    public ListProperty<Reservation> reservationsProperty() {
        return reservations;
    }

    public void setReservations(ObservableList<Reservation> reservations) {
        this.reservations.set(reservations);
    }
}
