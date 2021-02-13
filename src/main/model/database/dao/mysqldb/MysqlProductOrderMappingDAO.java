package main.model.database.dao.mysqldb;

import main.model.database.dao.DBManager;
import main.model.database.dao.ProductOrderMappingDAO;
import main.model.database.dto.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MysqlProductOrderMappingDAO extends ProductOrderMappingDAO {

    MysqlProductOrderMappingDAO(DBManager dbManager) {
        super(dbManager);
    }

    @Override
    protected ProductOrderMappingDTO buildDTO(ResultSet result) throws SQLException {
        return new ProductOrderMappingDTO(
                new OrderDTO(
                        result.getInt("id_ordinazione"),
                        new ReservationDTO(
                                result.getInt("postazione_prenotazione"),
                                new ShowDTO(
                                        result.getDate("data") == null ? null : result.getDate("data").toLocalDate(),
                                        result.getTime("ora") == null ? null : result.getTime("ora").toLocalTime()
                                )
                        )
                ),
                new ProductDTO(result.getString("prodotto")),
                result.getInt("quantità")
        );
    }

    @Override
    public int save(ProductOrderMappingDTO productForOrder) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "inserisci_prodotto_per_ordinazione")) {
            statement.setInt(1, productForOrder.getOrder().getIdOrder());
            statement.setInt(2, productForOrder.getOrder().getReservation().getLocation());
            statement.setDate(3, Date.valueOf(productForOrder.getOrder().getReservation().getShow().getDate()));
            statement.setTime(4, Time.valueOf(productForOrder.getOrder().getReservation().getShow().getTime()));
            statement.setString(5, productForOrder.getProduct().getProductName());
            statement.setInt(6, productForOrder.getQuantity());
            return executeUpdate(statement);
        }
    }

    @Override
    public List<ProductOrderMappingDTO> load(Object... primaryKey) throws SQLException {
        try( Connection connection = openConnection();
             PreparedStatement statement = prepareStatement(connection, "leggi_prodotto_per_ordinazione")) {
            statement.setInt(1, (Integer) primaryKey[0]);
            statement.setInt(2, (Integer) primaryKey[1]);
            statement.setDate(3, Date.valueOf((LocalDate) primaryKey[2]));
            statement.setTime(4, Time.valueOf((LocalTime) primaryKey[3]));
            statement.setString(5, (String) primaryKey[4]);
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    public int update(ProductOrderMappingDTO productForOrder) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "aggiorna_prodotto_per_ordinazione")) {
            statement.setInt(1, productForOrder.getOrder().getIdOrder());
            statement.setInt(2, productForOrder.getOrder().getReservation().getLocation());
            statement.setDate(3, Date.valueOf(productForOrder.getOrder().getReservation().getShow().getDate()));
            statement.setTime(4, Time.valueOf(productForOrder.getOrder().getReservation().getShow().getTime()));
            statement.setString(5, productForOrder.getProduct().getProductName());
            statement.setInt(6, productForOrder.getQuantity());
            return executeUpdate(statement);
        }
    }

    @Override
    public int delete(Object... primaryKey) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "cancella_prodotto_per_ordinazione");) {
            statement.setInt(1, (Integer) primaryKey[0]);
            statement.setInt(2, (Integer) primaryKey[1]);
            statement.setDate(3, Date.valueOf((LocalDate) primaryKey[2]));
            statement.setTime(4, Time.valueOf((LocalTime) primaryKey[3]));
            statement.setString(5, (String) primaryKey[4]);
            return executeUpdate(statement);
        }
    }

    @Override
    public List<ProductOrderMappingDTO> getOrderProducts(Integer idOrder) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_prodotti_per_ordinazione")) {
            statement.setInt(1, idOrder);
            return getFormattedResult(executeQuery(statement));
        }
    }

    @Override
    public List<ProductOrderMappingDTO> getProductOrders(String productName) throws SQLException {
        try(Connection connection = openConnection();
            PreparedStatement statement = prepareStatement(connection, "leggi_ordinazioni_per_prodotto")) {
            statement.setString(1, productName);
            return getFormattedResult(executeQuery(statement));
        }
    }
}
