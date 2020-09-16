package RestaurantReservation;

public class ReservationRequested extends AbstractEvent {

    private Long id;
    private Long ownerId;
    private String customerName;
    private String reservationDate;
    private Long reservationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerID) {
        this.ownerId = ownerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationID) {
        this.reservationId = reservationId;
    }
}