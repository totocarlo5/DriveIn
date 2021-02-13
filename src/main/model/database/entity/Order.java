package main.model.database.entity;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Order {
    private final ReadOnlyIntegerProperty idOrder;
    private final ObjectProperty<Reservation> reservation;
    private final ReadOnlyFloatProperty total;
    private final ReadOnlyBooleanProperty delivered;
    private final ListProperty<ProductOrderMapping> products;

    protected Order(Integer idOrder, Reservation reservation, Float total, Boolean delivered) {
        this.idOrder = new SimpleIntegerProperty(this, "idOrder", idOrder);
        this.reservation = new SimpleObjectProperty<>(this, "reservation", reservation);
        this.total = new SimpleFloatProperty(this, "total", total);
        this.delivered = new SimpleBooleanProperty(this, "delivered", delivered);
        products = new SimpleListProperty<>(this, "products");
    }

    public int getIdOrder() {
        return idOrder.get();
    }

    public ReadOnlyIntegerProperty idOrderProperty() {
        return idOrder;
    }

    public Reservation getReservation() throws SQLException {
        return reservation.get();
    }

    public ObjectProperty<Reservation> reservationProperty() {
        return reservation;
    }

    public float getTotal() {
        return total.get();
    }

    public void setReservation(Reservation reservation) {
        this.reservation.set(reservation);
    }

    public ReadOnlyFloatProperty totalProperty() {
        return total;
    }

    public boolean isDelivered() {
        return delivered.get();
    }

    public ReadOnlyBooleanProperty deliveredProperty() {
        return delivered;
    }

    public ObservableList<ProductOrderMapping> getProducts() throws SQLException {
        return products.get();
    }

    public ListProperty<ProductOrderMapping> productsProperty() {
        return products;
    }

    public void setProducts(ObservableList<ProductOrderMapping> products) {
        this.products.set(products);
    }
}
