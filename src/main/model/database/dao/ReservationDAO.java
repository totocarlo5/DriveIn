package main.model.database.dao;

import main.model.database.dto.ReservationDTO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public abstract class ReservationDAO extends AbstractDAO<ReservationDTO> {

    protected ReservationDAO(DBManager dbManager) {
        super(dbManager);
    }

    //query varie

    //carica tutte le prenotazioni di un utente
    protected abstract List<ReservationDTO> getUserReservations(String username) throws SQLException;

    //carica tutte le prenotazioni di uno spettacolo
    protected abstract List<ReservationDTO> getShowReservations(LocalDate date, LocalTime time) throws SQLException;
}
