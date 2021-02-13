package main.model.database.entity;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Reservation {
    private final ReadOnlyIntegerProperty location;
    private final ObjectProperty<User> user;
    private final ObjectProperty<Show> show;
    private final ReadOnlyBooleanProperty userArrivedToCinema;
    private final ListProperty<Order> orders;

    protected Reservation(Integer location, User user, Show show, Boolean userArrivedToCinema) {
        this.location = new SimpleIntegerProperty(this, "location", location);
        this.user = new SimpleObjectProperty<>(this, "user", user);
        this.show = new SimpleObjectProperty<>(this, "show", show);
        this.userArrivedToCinema = new SimpleBooleanProperty(this, "userArrivedToCinema", userArrivedToCinema);
        orders = new SimpleListProperty<>(this, "orders");
    }

    public Integer getLocation() {
        return location.get();
    }

    public ReadOnlyIntegerProperty locationProperty() {
        return location;
    }

    public User getUser() throws SQLException {
        return user.get();
    }

    public ObjectProperty<User> userProperty() {
        return user;
    }

    public void setUser(User user) {
        this.user.set(user);
    }

    public Show getShow() throws SQLException {
        return show.get();
    }

    public ObjectProperty<Show> showProperty() {
        return show;
    }

    public void setShow(Show show) {
        this.show.set(show);
    }

    public boolean isUserArrivedToCinema() {
        return userArrivedToCinema.get();
    }

    public ReadOnlyBooleanProperty userArrivedToCinemaProperty() {
        return userArrivedToCinema;
    }

    public ObservableList<Order> getOrders() throws SQLException {
        return orders.get();
    }

    public ListProperty<Order> ordersProperty() {
        return orders;
    }

    public void setOrders(ObservableList<Order> orders) {
        this.orders.set(orders);
    }
}
