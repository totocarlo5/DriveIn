package main.model.database.entity;

import javafx.beans.property.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.time.LocalDate;

public class User {
    private final ReadOnlyStringProperty username;
    private final ReadOnlyStringProperty password;
    private final ReadOnlyStringProperty firstName;
    private final ReadOnlyStringProperty lastName;
    private final ReadOnlyStringProperty fiscalCode;
    private final ReadOnlyObjectProperty<LocalDate> birthDate;
    private final ReadOnlyStringProperty phone;
    private final ListProperty<Reservation> reservations;

    protected User(String username, String password, String firstName, String lastName, String fiscalCode,
                LocalDate birthDate, String phone) {
        this.username = new SimpleStringProperty(this, "username", username);
        this.password = new SimpleStringProperty(this, "password", password);
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.lastName = new SimpleStringProperty(this, "lastName", lastName);
        this.fiscalCode = new SimpleStringProperty(this, "fiscalCode", fiscalCode);
        this.birthDate = new SimpleObjectProperty<>(this, "birthDate", birthDate);
        this.phone = new SimpleStringProperty(this, "phone", phone);
        reservations = new SimpleListProperty<>(this, "reservation");
    }

    public String getUsername() {
        return username.get();
    }

    public ReadOnlyStringProperty usernameProperty() {
        return username;
    }

    public String getPassword() {
        return password.get();
    }

    public ReadOnlyStringProperty passwordProperty() {
        return password;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public ReadOnlyStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public ReadOnlyStringProperty lastNameProperty() {
        return lastName;
    }

    public String getFiscalCode() {
        return fiscalCode.get();
    }

    public ReadOnlyStringProperty fiscalCodeProperty() {
        return fiscalCode;
    }

    public LocalDate getBirthDate() {
        return birthDate.get();
    }

    public ReadOnlyObjectProperty<LocalDate> birthDateProperty() {
        return birthDate;
    }

    public String getPhone() {
        return phone.get();
    }

    public ReadOnlyStringProperty phoneProperty() {
        return phone;
    }

    public ObservableList<Reservation> getReservations() throws SQLException {
        return reservations.get();
    }

    public ListProperty<Reservation> reservationsProperty() {
        return reservations;
    }

    public void setReservations(ObservableList<Reservation> reservations) {
        this.reservations.set(reservations);
    }
}

