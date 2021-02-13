package main.model.database.dao.mysqldb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.database.Mapping;
import main.model.database.dto.MovieDTO;
import main.model.database.dto.ReservationDTO;
import main.model.database.entity.Movie;
import main.model.database.entity.Reservation;
import main.model.database.entity.Show;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MysqlShowProxy extends Show {
    private Boolean isALreadyLoaded;

    public MysqlShowProxy(Movie movie, LocalDate date, LocalTime time, Float price) {
        super(movie, date, time, price);
        isALreadyLoaded = false;
    }

    @Override
    public ObservableList<Reservation> getReservations() throws SQLException {

        if (!isALreadyLoaded) {
            MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
            MysqlReservationDAO mysqlReservationDAO = (MysqlReservationDAO) mysqlDAOFactory.getReservationDAO();
            List<ReservationDTO> reservationsDTO = mysqlReservationDAO.getShowReservations(getDate(), getTime());
            List<Reservation> reservations = Mapping.fromReservationDTOToReservation(reservationsDTO, Mapping.DBName.MY_SQL);
            setReservations(FXCollections.observableList(reservations));
            isALreadyLoaded = true;
        }

        return super.getReservations();
    }

    @Override
    public Movie getMovie() throws SQLException {
        MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
        MysqlMovieDAO mysqlMovieDAO = (MysqlMovieDAO) mysqlDAOFactory.getMovieDAO();
        MovieDTO movieDTO = mysqlMovieDAO.load(super.getMovie().getIdMovie()).get(0);
        Movie movie = Mapping.fromMovieDTOToMovie(movieDTO, Mapping.DBName.MY_SQL);
        setMovie(movie);
        return super.getMovie();
    }
}
