package main.model.database.dto;


public class ReservationDTO {
    private Integer location;
    private UserDTO user;
    private ShowDTO show;
    private Boolean userArrivedToCinema;

    public ReservationDTO() {
        this(0, null);
    }

    public ReservationDTO(int location, ShowDTO show) {
        this(location, null, show, false);
    }

    public ReservationDTO(int location, UserDTO user, ShowDTO show, boolean userArrivedToCinema) {
        this.location = location;
        this.user = user;
        this.show = show;
        this.userArrivedToCinema = userArrivedToCinema;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ShowDTO getShow() {
        return show;
    }

    public void setShow(ShowDTO show) {
        this.show = show;
    }

    public Boolean isUserArrivedToCinema() {
        return userArrivedToCinema;
    }

    public void setUserArrivedToCinema(Boolean userArrivedToCinema) {
        this.userArrivedToCinema = userArrivedToCinema;
    }
}
