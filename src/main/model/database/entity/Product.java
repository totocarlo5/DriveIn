package main.model.database.entity;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class Product {
    private final ReadOnlyStringProperty productName;
    private final ReadOnlyFloatProperty price;
    private final ReadOnlyStringProperty category;
    private final ListProperty<ProductOrderMapping> orders;

    protected Product(String productName, Float price, String category) {
        this.productName = new SimpleStringProperty(this, "productName", productName);
        this.price = new SimpleFloatProperty(this,"price", price);
        this.category = new SimpleStringProperty(this, "category", category);
        orders = new SimpleListProperty<>(this, "orders");
    }

    public String getProductName() {
        return productName.get();
    }

    public ReadOnlyStringProperty productNameProperty() {
        return productName;
    }

    public float getPrice() {
        return price.get();
    }

    public ReadOnlyFloatProperty priceProperty() {
        return price;
    }

    public String getCategory() {
        return category.get();
    }

    public ReadOnlyStringProperty categoryProperty() {
        return category;
    }

    public ObservableList<ProductOrderMapping> getOrders() throws SQLException {
        return orders.get();
    }

    public ListProperty<ProductOrderMapping> ordersProperty() {
        return orders;
    }

    public void setOrders(ObservableList<ProductOrderMapping> orders) {
        this.orders.set(orders);
    }
}
