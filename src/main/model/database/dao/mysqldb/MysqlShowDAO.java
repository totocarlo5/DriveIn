package main.model.database.dao.mysqldb;

import main.model.database.dao.DBManager;
import main.model.database.dao.ShowDAO;
import main.model.database.dto.MovieDTO;
import main.model.database.dto.ShowDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MysqlShowDAO extends ShowDAO {

    MysqlShowDAO(DBManager dbManager) {
        super(dbManager);
    }

    @Override
    protected ShowDTO buildDTO(ResultSet result) throws SQLException {
        return new ShowDTO(
                new MovieDTO(result.getInt("Id_film")),
                result.getDate("data") == null ? null : result.getDate("data").toLocalDate(),
                result.getTime("ora") == null ? null : result.getTime("ora").toLocalTime(),
                result.getFloat("prezzo")
        );
    }

    @Override
    public int save(ShowDTO show) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "inserisci_spettacolo")) {
            statement.setInt(1, show.getMovie().getIdMovie());
            statement.setDate(2, Date.valueOf(show.getDate()));
            statement.setTime(3, Time.valueOf(show.getTime()));
            statement.setFloat(4, show.getPrice());
            return executeUpdate(statement);
        }
    }

    @Override
    public List<ShowDTO> load(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection()) {
            PreparedStatement statement;
            if (primaryKey.length == 0)
                statement = prepareStatement(connection, "leggi_tutti_gli_spettacoli");
            else {
                statement = prepareStatement(connection, "leggi_spettacolo");
                statement.setDate(1, Date.valueOf((LocalDate) primaryKey[0]));
                statement.setTime(2, Time.valueOf((LocalTime) primaryKey[1]));
            }
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    public int update(ShowDTO show) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "aggiorna_spettacolo")) {
            statement.setInt(1, show.getMovie().getIdMovie());
            statement.setDate(2, Date.valueOf(show.getDate()));
            statement.setTime(3, Time.valueOf(show.getTime()));
            statement.setFloat(4, show.getPrice());
            return executeUpdate(statement);
        }
    }

    @Override
    public int delete(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "cancella_spettacolo")) {
            statement.setDate(1, Date.valueOf((LocalDate) primaryKey[0]));
            statement.setTime(2, Time.valueOf((LocalTime) primaryKey[1]));
            return executeUpdate(statement);
        }
    }

    @Override
    protected List<ShowDTO> getFilmShows(Integer idMovie) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_spettacoli_per_film")) {
            statement.setInt(1, idMovie);
            return getFormattedResult(executeQuery(statement));
        }
    }
}
