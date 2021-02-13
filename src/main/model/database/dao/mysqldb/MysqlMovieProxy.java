package main.model.database.dao.mysqldb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import main.model.database.Mapping;
import main.model.database.dto.ShowDTO;
import main.model.database.entity.Movie;
import main.model.database.entity.Show;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MysqlMovieProxy extends Movie {

    public MysqlMovieProxy(Integer idMovie, String title, String genre, LocalDate exitDate, String direction,
                           String actors, String country, Integer duration, String release, String screenplay,
                           String photography, String editing, String music, String production, String story, Image poster) {

        super(idMovie, title, genre, exitDate, direction, actors, country, duration, release, screenplay, photography,
                editing, music, production, story, poster);
    }

    @Override
    public ObservableList<Show> getShows() throws SQLException {
        MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
        MysqlShowDAO mysqlShowDAO = (MysqlShowDAO) mysqlDAOFactory.getShowDAO();
        List<ShowDTO> showsDTO = mysqlShowDAO.getFilmShows(getIdMovie());
        List<Show> shows = Mapping.fromShowDTOToShow(showsDTO, Mapping.DBName.MY_SQL);
        setShows(FXCollections.observableList(shows));
        return super.getShows();
    }
}
