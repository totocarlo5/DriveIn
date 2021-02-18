package main.model.database;

import main.model.database.dao.mysqldb.*;
import main.model.database.dto.*;
import main.model.database.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface Mapping {

    enum DBName {
        MY_SQL
    }

    static User fromUserDTOToUser(UserDTO userDTO, DBName dbName) {
        User user = null;
        if (dbName == DBName.MY_SQL)
            user = new MysqlUserProxy (
                    userDTO.getUsername(),
                    userDTO.getPassword(),
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    userDTO.getFiscalCode(),
                    userDTO.getBirthDate(),
                    userDTO.getPhone()
            );
        return user;
    }

    static List<User> fromUserDTOToUser(List<UserDTO> usersDTO, DBName dbName) {
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO: usersDTO) {
            User user = fromUserDTOToUser(userDTO, dbName);
            users.add(user);
        }
        return users;
    }

    static UserDTO fromUserToUserDTO(User user) {
       return new UserDTO(
                user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
                user.getFiscalCode(), user.getBirthDate(), user.getPhone()
        );
    }

    static List<UserDTO> fromUserToUserDTO(List<User> users) {
        List<UserDTO> usersDTO = new ArrayList<>();
        for (User user: users) {
            UserDTO userDTO = fromUserToUserDTO(user);
            usersDTO.add(userDTO);
        }
        return usersDTO;
    }

    static Show fromShowDTOToShow(ShowDTO showDTO, DBName dbName) {
        Show show = null;
        Movie movie = fromMovieDTOToMovie(showDTO.getMovie(), DBName.MY_SQL);
        if (dbName == DBName.MY_SQL)
            show = new MysqlShowProxy(
                    movie,
                    showDTO.getDate(),
                    showDTO.getTime(),
                    showDTO.getPrice()
            );
        return show;
    }

    static List<Show> fromShowDTOToShow(List<ShowDTO> showsDTO, DBName dbName) {
        List<Show> shows = new ArrayList<>();
        for (ShowDTO showDTO: showsDTO) {
            Show show = fromShowDTOToShow(showDTO, dbName);
            shows.add(show);
        }
        return shows;
    }

    static ShowDTO fromShowToShowDTO(Show show) throws SQLException {
        MovieDTO movieDTO = fromMovieToMovieDTO(show.getMovie());
        return new ShowDTO(
                movieDTO,
                show.getDate(),
                show.getTime(),
                show.getPrice()
        );
    }

    static List<ShowDTO> fromShowToShowDTO(List<Show> shows) throws SQLException {

        List<ShowDTO> showsDTO = new ArrayList<>();
        for (Show show: shows) {
            ShowDTO showDTO = fromShowToShowDTO(show);
            showsDTO.add(showDTO);
        }
        return showsDTO;
    }

    static Product fromProductDTOToProduct(ProductDTO productDTO, DBName dbName) {
        Product product = null;
        if (dbName == DBName.MY_SQL)
            product = new MysqlProductProxy(
                    productDTO.getProductName(),
                    productDTO.getPrice(),
                    productDTO.getCategory()
            );
        return product;
    }

    static List<Product> fromProductDTOToProduct(List<ProductDTO> productsDTO, DBName dbName) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO: productsDTO) {
            Product product = fromProductDTOToProduct(productDTO, dbName);
            products.add(product);
        }
        return products;
    }

    static ProductDTO fromProductToProductDTO(Product product) {
        return new ProductDTO(
                product.getProductName(),
                product.getPrice(),
                product.getCategory()
        );
    }

    static List<ProductDTO> fromProductToProductDTO(List<Product> products) {
        List<ProductDTO> productsDTO = new ArrayList<>();
        for (Product product: products) {
            ProductDTO productDTO = fromProductToProductDTO(product);
            productsDTO.add(productDTO);
        }
        return productsDTO;
    }

    static Reservation fromReservationDTOToReservation(ReservationDTO reservationDTO, DBName dbName) {
        Reservation reservation = null;
        User user = fromUserDTOToUser(reservationDTO.getUser(), dbName);
        Show show = fromShowDTOToShow(reservationDTO.getShow(), dbName);
        if (dbName == DBName.MY_SQL)
            reservation = new MysqlReservationProxy(
                    reservationDTO.getLocation(),
                    user,
                    show,
                    reservationDTO.isUserArrivedToCinema()
            );
        return reservation;
    }

    static List<Reservation> fromReservationDTOToReservation(List<ReservationDTO> reservationsDTO, DBName dbName) {
        List<Reservation> reservations = new ArrayList<>();
        for (ReservationDTO reservationDTO: reservationsDTO) {
            Reservation reservation = fromReservationDTOToReservation(reservationDTO, dbName);
            reservations.add(reservation);
        }
        return reservations;
    }

    static ReservationDTO fromReservationToReservationDTO(Reservation reservation) throws SQLException {
        UserDTO userDTO = fromUserToUserDTO(reservation.getUser());
        ShowDTO showDTO = fromShowToShowDTO(reservation.getShow());
        return new ReservationDTO(
                reservation.getLocation(),
                userDTO,
                showDTO,
                reservation.isUserArrivedToCinema()
        );
    }

    static List<ReservationDTO> fromReservationToReservationDTO(List<Reservation> reservations) throws SQLException {
        List<ReservationDTO> reservationsDTO = new ArrayList<>();
        for (Reservation reservation: reservations) {
            ReservationDTO reservationDTO = fromReservationToReservationDTO(reservation);
            reservationsDTO.add(reservationDTO);
        }
        return reservationsDTO;
    }

    static Order fromOrderDTOToOrder(OrderDTO orderDTO, DBName dbName) {
       Order order = null;
        Reservation reservation = fromReservationDTOToReservation(orderDTO.getReservation(), dbName);
        if (dbName == DBName.MY_SQL)
            order = new MysqlOrderProxy(
                    orderDTO.getIdOrder(),
                    reservation,
                    orderDTO.getTotal(),
                    orderDTO.isDelivered()
            );
        return order;
    }

    static List<Order> fromOrderDTOToOrder(List<OrderDTO> ordersDTO, DBName dbName) {
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDTO: ordersDTO) {
            Order order = fromOrderDTOToOrder(orderDTO, dbName);
            orders.add(order);
        }
        return orders;
    }

    static OrderDTO fromOrderToOrderDTO(Order order) throws SQLException {
        ReservationDTO reservationDTO = fromReservationToReservationDTO(order.getReservation());
        return new OrderDTO(
                order.getIdOrder(),
                reservationDTO,
                order.getTotal(),
                order.isDelivered()
        );
    }

    static List<OrderDTO> fromOrderToOrderDTO(List<Order> orders) throws SQLException {
        List<OrderDTO> ordersDTO = new ArrayList<>();
        for (Order order: orders) {
            OrderDTO orderDTO = fromOrderToOrderDTO(order);
            ordersDTO.add(orderDTO);
        }
        return ordersDTO;
    }

    static ProductOrderMapping fromProductOrderMappingDTOToProductOrderMapping(ProductOrderMappingDTO productOrderMappingDTO, DBName dbName) {
        Order order = fromOrderDTOToOrder(productOrderMappingDTO.getOrder(), dbName);
        Product product = fromProductDTOToProduct(productOrderMappingDTO.getProduct(), dbName);
        return  new MysqlProductOrderMappingProxy(
                order,
                product,
                productOrderMappingDTO.getQuantity()
        );
    }

    static List<ProductOrderMapping> fromProductOrderMappingDTOToProductOrderMapping(List<ProductOrderMappingDTO> productOrderMappingsDTO, DBName dbName) {
        List<ProductOrderMapping> productOrderMappings = new ArrayList<>();
        for (ProductOrderMappingDTO productOrderMappingDTO: productOrderMappingsDTO) {
            ProductOrderMapping productOrderMapping = fromProductOrderMappingDTOToProductOrderMapping(productOrderMappingDTO, dbName);
            productOrderMappings.add(productOrderMapping);
        }
        return productOrderMappings;
    }

    static ProductOrderMappingDTO fromProductOrderMappingToProductOrderMappingDTO(ProductOrderMapping productOrderMapping) throws SQLException {
        OrderDTO orderDTO = fromOrderToOrderDTO(productOrderMapping.getOrder());
        ProductDTO productDTO = fromProductToProductDTO(productOrderMapping.getProduct());
        return new ProductOrderMappingDTO(
                orderDTO,
                productDTO,
                productOrderMapping.getQuantity()
        );
    }

    static List<ProductOrderMappingDTO> fromProductOrderMappingToProductOrderMappingDTO(List<ProductOrderMapping> productOrderMappings) throws SQLException {
        List<ProductOrderMappingDTO> productOrderMappingsDTO = new ArrayList<>();
        for (ProductOrderMapping productOrderMapping: productOrderMappings) {
            ProductOrderMappingDTO productOrderMappingDTO = fromProductOrderMappingToProductOrderMappingDTO(productOrderMapping);
            productOrderMappingsDTO.add(productOrderMappingDTO);
        }
        return productOrderMappingsDTO;
    }

    static Movie fromMovieDTOToMovie(MovieDTO movieDTO, DBName dbName) {
        Movie movie = null;
        if (dbName == DBName.MY_SQL)
            movie = new MysqlMovieProxy(
                    movieDTO.getIdMovie(),
                    movieDTO.getTitle(),
                    movieDTO.getGenre(),
                    movieDTO.getExitDate(),
                    movieDTO.getDirection(),
                    movieDTO.getActors(),
                    movieDTO.getCountry(),
                    movieDTO.getDuration(),
                    movieDTO.getRelease(),
                    movieDTO.getScreenplay(),
                    movieDTO.getPhotography(),
                    movieDTO.getEditing(),
                    movieDTO.getMusic(),
                    movieDTO.getProduction(),
                    movieDTO.getStory(),
                    movieDTO.getPoster()
            );
        return movie;
    }

    static List<Movie> fromMovieDTOToMovie(List<MovieDTO> moviesDTO, DBName dbName) {
        List<Movie> movies = new ArrayList<>();
        for (MovieDTO movieDTO: moviesDTO) {
            Movie movie = fromMovieDTOToMovie(movieDTO, dbName);
            movies.add(movie);
        }
        return movies;
    }

    static MovieDTO fromMovieToMovieDTO(Movie movie) {
        return new MovieDTO(
                movie.getIdMovie(),
                movie.getTitle(),
                movie.getGenre(),
                movie.getExitDate(),
                movie.getDirection(),
                movie.getActors(),
                movie.getCountry(),
                movie.getDuration(),
                movie.getRelease(),
                movie.getScreenplay(),
                movie.getPhotography(),
                movie.getEditing(),
                movie.getMusic(),
                movie.getProduction(),
                movie.getStory(),
                movie.getPoster()
        );
    }

    static List<MovieDTO> fromMovieToMovieDTO(List<Movie> movies) {
        List<MovieDTO> moviesDTO = null;
        for (Movie movie: movies) {
            MovieDTO movieDTO = fromMovieToMovieDTO(movie);
            moviesDTO.add(movieDTO);
        }
        return moviesDTO;
    }
}
