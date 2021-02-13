package main.model.database.dao.mysqldb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.database.Mapping;
import main.model.database.dto.ProductOrderMappingDTO;
import main.model.database.dto.ReservationDTO;
import main.model.database.entity.Order;
import main.model.database.entity.ProductOrderMapping;
import main.model.database.entity.Reservation;

import java.sql.SQLException;
import java.util.List;

public class MysqlOrderProxy extends Order {
    private Boolean isAlreadyLoaded;

    public MysqlOrderProxy(Integer idOrder, Reservation reservation, Float total, Boolean delivered) {
        super(idOrder, reservation, total, delivered);
        isAlreadyLoaded = false;
    }

    @Override
    public ObservableList<ProductOrderMapping> getProducts() throws SQLException {
        if (!isAlreadyLoaded) {
            MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
            MysqlProductOrderMappingDAO mysqlProductOrderMappingDAO = (MysqlProductOrderMappingDAO) mysqlDAOFactory.getProductForOrderDAO();;
            List<ProductOrderMappingDTO> orderProductsMappingsDTO = mysqlProductOrderMappingDAO.getOrderProducts(getIdOrder());
            List<ProductOrderMapping> products = Mapping.fromProductOrderMappingDTOToProductOrderMapping(orderProductsMappingsDTO, Mapping.DBName.MY_SQL);
            setProducts(FXCollections.observableList(products));
            isAlreadyLoaded = true;
        }
        return super.getProducts();
    }

    @Override
    public Reservation getReservation() throws SQLException {
        MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
        MysqlReservationDAO mysqlReservationDAO = (MysqlReservationDAO) mysqlDAOFactory.getReservationDAO();
        ReservationDTO reservationDTO = mysqlReservationDAO.load(super.getReservation().getLocation(), super.getReservation().getShow()).get(0);
        Reservation reservation = Mapping.fromReservationDTOToReservation(reservationDTO, Mapping.DBName.MY_SQL);
        setReservation(reservation);
        return super.getReservation();
    }
}
