package main.model.database.entity;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.sql.SQLException;

public class ProductOrderMapping {
    private final ObjectProperty<Order> order;
    private final ObjectProperty<Product> product;
    private final ReadOnlyIntegerProperty quantity;

    protected ProductOrderMapping(Order order, Product product, Integer quantity) {
        this.order = new SimpleObjectProperty<>(this, "order", order);
        this.product = new SimpleObjectProperty<>(this, "product", product);
        this.quantity = new SimpleIntegerProperty(this, "quantity", quantity);
    }

    public Order getOrder() throws SQLException {
        return order.get();
    }

    public ObjectProperty<Order> orderProperty() {
        return order;
    }

    public void setOrder(Order order) {
        this.order.set(order);
    }

    public Product getProduct() throws SQLException {
        return product.get();
    }

    public void setProduct(Product product) {
        this.product.set(product);
    }

    public ObjectProperty<Product> productProperty() {
        return product;
    }

    public int getQuantity() {
        return quantity.get();
    }

    public ReadOnlyIntegerProperty quantityProperty() {
        return quantity;
    }
}
