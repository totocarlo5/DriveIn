package main.model.database.dao.mysqldb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.database.Mapping;
import main.model.database.dto.ReservationDTO;
import main.model.database.entity.Reservation;
import main.model.database.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class MysqlUserProxy extends User {
    private Boolean isAlreadyLoaded;

    public MysqlUserProxy(String username, String password, String firstName, String lastName, String fiscalCode, LocalDate birthDate, String phone) {
        super(username, password, firstName, lastName, fiscalCode, birthDate, phone);
        isAlreadyLoaded = false;
    }

    @Override
    public ObservableList<Reservation> getReservations() throws SQLException {
        if (!isAlreadyLoaded) {
            MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
            MysqlReservationDAO mysqlReservationDAO = (MysqlReservationDAO) mysqlDAOFactory.getReservationDAO();
            List<ReservationDTO> reservationsDTO = mysqlReservationDAO.getUserReservations(getUsername());
            List<Reservation> reservations = Mapping.fromReservationDTOToReservation(reservationsDTO, Mapping.DBName.MY_SQL);
            setReservations(FXCollections.observableList(reservations));
            isAlreadyLoaded = true;
        }
        return super.getReservations();
    }
}
