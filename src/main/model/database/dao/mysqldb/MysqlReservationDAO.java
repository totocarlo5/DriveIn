package main.model.database.dao.mysqldb;

import main.model.database.dao.DBManager;
import main.model.database.dao.ReservationDAO;
import main.model.database.dto.ReservationDTO;
import main.model.database.dto.ShowDTO;
import main.model.database.dto.UserDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MysqlReservationDAO extends ReservationDAO {

    MysqlReservationDAO(DBManager dbManager) {
        super(dbManager);
    }

    @Override
    protected ReservationDTO buildDTO(ResultSet result) throws SQLException {
        return new ReservationDTO(
                result.getInt("postazione"),
                new UserDTO(result.getString("utente")),
                new ShowDTO(
                        result.getDate("data") == null ? null : result.getDate("data").toLocalDate(),
                        result.getTime("ora") == null ? null : result.getTime("ora").toLocalTime()
                ),
                result.getBoolean("utente_presente")
        );
    }

    @Override
    public int save(ReservationDTO reservation) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "inserisci_prenotazione")) {
            statement.setInt(1, reservation.getLocation());
            statement.setDate(2, Date.valueOf(reservation.getShow().getDate()));
            statement.setTime(3, Time.valueOf(reservation.getShow().getTime()));
            statement.setString(4, reservation.getUser().getUsername());
            return executeUpdate(statement);
        }
    }

    @Override
    public List<ReservationDTO> load(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_prenotazione")) {
            statement.setInt(1, (Integer) primaryKey[0]);
            statement.setDate(2, Date.valueOf((LocalDate) primaryKey[1]));
            statement.setTime(3, Time.valueOf((LocalTime) primaryKey[2]));
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    public int update(ReservationDTO reservation) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "aggiorna_prenotazione")) {
            statement.setInt(1, reservation.getLocation());
            statement.setDate(2, Date.valueOf(reservation.getShow().getDate()));
            statement.setTime(3, Time.valueOf(reservation.getShow().getTime()));
            statement.setString(4, reservation.getUser().getUsername());
            return executeUpdate(statement);
        }
    }

    @Override
    public int delete(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection,"cancella_prenotazione");) {
            statement.setInt(1, (Integer) primaryKey[0]);
            statement.setDate(2, Date.valueOf((LocalDate) primaryKey[1]));
            statement.setTime(3, Time.valueOf((LocalTime) primaryKey[2]));
            return executeUpdate(statement);
        }
    }

    protected List<ReservationDTO> getUserReservations(String username) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_prenotazioni_per_utente")) {
            statement.setString(1, username);
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    protected List<ReservationDTO> getShowReservations(LocalDate date, LocalTime time) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_prenotazioni_per_spettacolo");) {
            statement.setDate(1, Date.valueOf(date));
            statement.setTime(2, Time.valueOf(time));
            return getFormattedResult(executeQuery(statement));
        }
    }
}
