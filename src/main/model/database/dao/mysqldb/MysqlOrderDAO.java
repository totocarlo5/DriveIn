package main.model.database.dao.mysqldb;

import main.model.database.dao.DBManager;
import main.model.database.dao.OrderDAO;
import main.model.database.dto.OrderDTO;
import main.model.database.dto.ReservationDTO;
import main.model.database.dto.ShowDTO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MysqlOrderDAO extends OrderDAO {

    MysqlOrderDAO(DBManager dbManager) {
        super(dbManager);
    }

    @Override
    protected OrderDTO buildDTO(ResultSet result) throws SQLException {
        return new OrderDTO(
                result.getInt("Id"),
                new ReservationDTO(
                        result.getInt("postazione_prenotazione"),
                        new ShowDTO(
                                result.getDate("data") == null ? null : result.getDate("data").toLocalDate(),
                                result.getTime("ora") == null ? null : result.getTime("ora").toLocalTime()
                        )
                ),
                result.getFloat("totale"),
                result.getBoolean("consegnata")
        );
    }

    @Override
    public int save(OrderDTO order) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "inserisci_ordinazione")) {
            statement.setInt(1, order.getIdOrder());
            statement.setInt(2, order.getReservation().getLocation());
            statement.setDate(3, Date.valueOf(order.getReservation().getShow().getDate()));
            statement.setTime(4, Time.valueOf(order.getReservation().getShow().getTime()));
            statement.setFloat(5, order.getTotal());
            statement.setBoolean(6, order.isDelivered());
            return executeUpdate(statement);
        }
    }

    @Override
    public List<OrderDTO> load(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_ordinazione")) {
            statement.setInt(1, (Integer) primaryKey[0]);
            statement.setInt(2, (Integer) primaryKey[1]);
            statement.setDate(3, Date.valueOf((LocalDate) primaryKey[2]));
            statement.setTime(4, Time.valueOf((LocalTime) primaryKey[3]));
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    public int update(OrderDTO order) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "aggiorna_ordinazione")) {
            statement.setInt(1, order.getIdOrder());
            statement.setInt(2, order.getReservation().getLocation());
            statement.setDate(3, Date.valueOf(order.getReservation().getShow().getDate()));
            statement.setTime(4, Time.valueOf(order.getReservation().getShow().getTime()));
            statement.setFloat(5, order.getTotal());
            statement.setBoolean(6, order.isDelivered());
            return executeUpdate(statement);
        }
    }

    @Override
    public int delete(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "cancella_ordinazione")) {
            statement.setInt(1, (Integer) primaryKey[0]);
            statement.setInt(2, (Integer) primaryKey[1]);
            statement.setDate(3, Date.valueOf((LocalDate) primaryKey[2]));
            statement.setTime(4, Time.valueOf((LocalTime) primaryKey[3]));
            return executeUpdate(statement);
        }
    }

    @Override
    public List<OrderDTO> getReservationOrders(int location, LocalDate date, LocalTime time) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_ordinazioni_per_prenotazione")) {
            statement.setInt(1, location);
            statement.setDate(2, Date.valueOf(date));
            statement.setTime(3, Time.valueOf(time));
            return getFormattedResult(executeQuery(statement));
        }
    }
}
