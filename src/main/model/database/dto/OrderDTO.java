package main.model.database.dto;

public class OrderDTO {
    private Integer idOrder;
    private ReservationDTO reservation;
    private Float total;
    private Boolean delivered;

    public OrderDTO() {
        this(0, null);
    }

    public OrderDTO(int idOrder, ReservationDTO reservation) {
        this(idOrder, reservation, 0, false);
    }

    public OrderDTO(int idOrder, ReservationDTO reservation, float total, boolean delivered) {
        this.idOrder = idOrder;
        this.reservation = reservation;
        this.total = total;
        this.delivered = delivered;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public OrderDTO setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
        return this;
    }

    public ReservationDTO getReservation() {
        return reservation;
    }

    public OrderDTO setReservation(ReservationDTO reservation) {
        this.reservation = reservation;
        return this;
    }

    public Float getTotal() {
        return total;
    }

    public OrderDTO setTotal(Float total) {
        this.total = total;
        return this;
    }

    public Boolean isDelivered() {
        return delivered;
    }

    public OrderDTO setDelivered(Boolean delivered) {
        this.delivered = delivered;
        return this;
    }
}