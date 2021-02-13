package main.model;

public class UsernameAlreadyUsedException extends Exception {

    public UsernameAlreadyUsedException(String message) {
        super(message);
    }
}
