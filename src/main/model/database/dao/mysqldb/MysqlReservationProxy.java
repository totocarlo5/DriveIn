package main.model.database.dao.mysqldb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.database.Mapping;
import main.model.database.dto.OrderDTO;
import main.model.database.dto.ShowDTO;
import main.model.database.dto.UserDTO;
import main.model.database.entity.Order;
import main.model.database.entity.Reservation;
import main.model.database.entity.Show;
import main.model.database.entity.User;

import java.sql.SQLException;
import java.util.List;

public class MysqlReservationProxy extends Reservation {
    private Boolean isALreadyLoaded;

    public MysqlReservationProxy(Integer location, User user, Show show, Boolean userArrivedToCinema) {
        super(location, user, show, userArrivedToCinema);
        isALreadyLoaded = false;
    }

    @Override
    public ObservableList<Order> getOrders() throws SQLException {
        if (!isALreadyLoaded) {
            MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
            MysqlOrderDAO mysqlOrderDAO = (MysqlOrderDAO) mysqlDAOFactory.getOrderDAO();
            List<OrderDTO> ordersDTO = mysqlOrderDAO.getReservationOrders(getLocation(), super.getShow().getDate(), super.getShow().getTime());
            List<Order> orders = Mapping.fromOrderDTOToOrder(ordersDTO, Mapping.DBName.MY_SQL);
            setOrders(FXCollections.observableList(orders));
            isALreadyLoaded = true;
        }
        return super.getOrders();
    }

    @Override
    public User getUser() throws SQLException {
        MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
        MysqlUserDAO mysqlUserDAO = (MysqlUserDAO) mysqlDAOFactory.getUserDAO();
        UserDTO userDTO = mysqlUserDAO.load(super.getUser().getUsername()).get(0);
        User user = Mapping.fromUserDTOToUser(userDTO, Mapping.DBName.MY_SQL);
        setUser(user);
        return super.getUser();
    }

    @Override
    public Show getShow() throws SQLException {
        MysqlDAOFactory mysqlDAOFactory = new MysqlDAOFactory();
        MysqlShowDAO mysqlShowDAO = (MysqlShowDAO) mysqlDAOFactory.getShowDAO();
        ShowDTO showDTO = mysqlShowDAO.load(super.getShow().getDate(), super.getShow().getTime()).get(0);
        Show show = Mapping.fromShowDTOToShow(showDTO, Mapping.DBName.MY_SQL);
        setShow(show);
        return super.getShow();
    }
}
