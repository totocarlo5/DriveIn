package main.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import main.model.database.Mapping;
import main.model.database.dao.*;
import main.model.database.dao.mysqldb.MysqlDAOFactory;
import main.model.database.dao.mysqldb.MysqlReservationProxy;
import main.model.database.dto.ProductDTO;
import main.model.database.dto.ShowDTO;
import main.model.database.dto.UserDTO;
import main.model.database.entity.Product;
import main.model.database.entity.Reservation;
import main.model.database.entity.Show;
import main.model.database.entity.User;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class Model {
    private User userLogged;

    private final AbstractDAOFactory mysqlDAOFactory;
    private final UserDAO userDAO;
    private final ShowDAO showDAO;
    private final ProductDAO productDAO;
    private final ReservationDAO reservationDAO;
    private final OrderDAO orderDAO;
    private final ProductOrderMappingDAO productOrderMappingDAO;
    private final MovieDAO movieDAO;

    public Model() {
        userLogged = null;
        mysqlDAOFactory = new MysqlDAOFactory();
        userDAO = mysqlDAOFactory.getUserDAO();
        showDAO = mysqlDAOFactory.getShowDAO();
        productDAO = mysqlDAOFactory.getProductDAO();
        reservationDAO = mysqlDAOFactory.getReservationDAO();
        orderDAO = mysqlDAOFactory.getOrderDAO();
        productOrderMappingDAO = mysqlDAOFactory.getProductForOrderDAO();
        movieDAO = mysqlDAOFactory.getMovieDAO();
    }

    public void logInUser(String username, String password) throws UserNotFoundException, PasswordNotMatchException, SQLException {
        List<UserDTO> usersDTO = userDAO.load(username);

        if (usersDTO.isEmpty())
            throw new UserNotFoundException("L'username inserito non è presente");

        if (!usersDTO.get(0).getPassword().equals(password))
            throw new PasswordNotMatchException("La password inserita non è corretta");

        userLogged = Mapping.fromUserDTOToUser(usersDTO, Mapping.DBName.MY_SQL).get(0);
    }

    public void logOutUser() {
        userLogged = null;
    }

    public void registerUser(User user) throws SQLException, UsernameAlreadyUsedException {
        if (isUsernameAlreadyUsed(user.getUsername()))
            throw new UsernameAlreadyUsedException("Username già utilizzato");
        else {
            UserDTO userDTO = Mapping.fromUserToUserDTO(user);
            userDAO.save(userDTO);
        }
    }

    public ObservableList<Show> getReservableShows() throws SQLException {
        List<ShowDTO> showsDTO = showDAO.load();
        ObservableList<Show> reservableShows = FXCollections.observableArrayList();
        for (ShowDTO showDTO: showsDTO) {
            Show show = Mapping.fromShowDTOToShow(showDTO, Mapping.DBName.MY_SQL);
            if (show.getDate().isAfter(LocalDate.now()))
                reservableShows.add(show);
            else if (show.getDate().equals(LocalDate.now()))
                if (show.getTime().isAfter(LocalTime.now()))
                    reservableShows.add(show);
        }
        return reservableShows;
    }

    public ObservableList<Reservation> getReservationsCanBeMade() throws SQLException {
        ObservableList<Show> reservableShows = getReservableShows();
        ObservableList<Reservation> reservationsCanBeMade = FXCollections.observableArrayList();
        for (Show reservableShow: reservableShows) {
            Reservation reservationCanBeMade = new MysqlReservationProxy(null, null, reservableShow, null);
            reservationsCanBeMade.add(reservationCanBeMade);
        }
        return reservationsCanBeMade;
    }

    public ObservableList<Product> getOrderableProducts() throws SQLException {
        List<ProductDTO> productsDTO = productDAO.load();
        ObservableList<Product> orderableProducts = FXCollections.observableArrayList();
        orderableProducts.addAll(Mapping.fromProductDTOToProduct(productsDTO, Mapping.DBName.MY_SQL));
        return orderableProducts;
    }

    public String getUserLoggedFirstName() {
        return userLogged.getFirstName();
    }

    public String getUserLoggedLastName() {
        return  userLogged.getLastName();
    }

    public ObservableList<Reservation> getUserLoggedReservations() throws SQLException {
        return userLogged.getReservations();
    }

    public ObservableList<Reservation> getUserLoggedManagebleReservations() throws SQLException {
        ObservableList<Reservation> manageableReservations = FXCollections.observableArrayList();
        for (Reservation reservation: userLogged.getReservations()) {
            if (reservation.getShow().getDate().isAfter(LocalDate.now()))
                manageableReservations.add(reservation);
            else if (reservation.getShow().getDate().isEqual(LocalDate.now()))
                if (reservation.getShow().getTime().isAfter(LocalTime.now()))
                    manageableReservations.add(reservation);
        }
        return manageableReservations;
    }

    public void deleteUserLoggedRegistration(String password) throws PasswordNotMatchException, SQLException {
        if (!userLogged.getPassword().equals(password))
            throw new PasswordNotMatchException("La password inserita non è corretta");
        else
            userDAO.delete(userLogged.getUsername());
    }

    private Boolean isUsernameAlreadyUsed(String username) throws SQLException {
        UserDTO userDTO = (UserDTO) userDAO.load(username);
        return userDTO != null;
    }
}
