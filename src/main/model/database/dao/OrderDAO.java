package main.model.database.dao;

import main.model.database.dto.OrderDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public abstract class OrderDAO extends AbstractDAO<OrderDTO> {

    protected OrderDAO(DBManager dbManager) {
        super(dbManager);
    }

    //ulteriori query

    //carica tutte le ordinazioni di una prenotazione
    protected abstract List<OrderDTO> getReservationOrders(int location, LocalDate date, LocalTime time) throws SQLException;
}
