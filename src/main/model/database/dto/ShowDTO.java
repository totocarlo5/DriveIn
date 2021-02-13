package main.model.database.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowDTO {
    private MovieDTO movie;
    private LocalDate date;
    private LocalTime time;
    private Float price;

    private ShowDTO() {
        this(null, null);
    }

    public ShowDTO(LocalDate date, LocalTime time) {
        this(null, date, time, 0);
    }

    public ShowDTO(MovieDTO movie, LocalDate date, LocalTime time,  float price) {
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public MovieDTO getMovie() {
        return movie;
    }

    public void setMovie(MovieDTO movie) {
        this.movie = movie;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
