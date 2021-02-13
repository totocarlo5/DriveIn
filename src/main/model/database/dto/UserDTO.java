package main.model.database.dto;

import java.time.LocalDate;

public class UserDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String fiscalCode;
    private LocalDate birthDate;
    private String phone;

    private UserDTO() {
        this(null);
    }

    public UserDTO(String username) {
        this(username, null, null, null, null, null, null);
    }

    public UserDTO(String username, String password, String firstName, String lastName, String fiscalCode, LocalDate birthDate, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fiscalCode = fiscalCode;
        this.birthDate = birthDate;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

