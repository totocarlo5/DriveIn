package main.model;

public class PasswordNotMatchException extends Exception {

    public PasswordNotMatchException(String message) {
        super(message);
    }
}
