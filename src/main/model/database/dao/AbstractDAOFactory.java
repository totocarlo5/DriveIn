package main.model.database.dao;

public interface AbstractDAOFactory {

    UserDAO getUserDAO();

    ShowDAO getShowDAO();

    ReservationDAO getReservationDAO();

    OrderDAO getOrderDAO();

    ProductDAO getProductDAO();

    ProductOrderMappingDAO getProductForOrderDAO();

    MovieDAO getMovieDAO();
}
