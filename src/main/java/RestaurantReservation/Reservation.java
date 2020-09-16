package RestaurantReservation;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Reservation_table")
public class Reservation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long reservationId;
    private Long ownerId;
    private String customerName;
    private String reservationDate;
    private String status;

    @PostPersist
    public void onPostPersist(){
        ReservationRequested reservationRequested = new ReservationRequested();

        reservationRequested.setId(this.getReservationId());
        reservationRequested.setReservationId(this.getReservationId());
        reservationRequested.setOwnerId(this.getOwnerId());
        reservationRequested.setReservationDate(this.getReservationDate());
        reservationRequested.setCustomerName(this.getCustomerName());

        BeanUtils.copyProperties(this, reservationRequested);
        reservationRequested.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate(){

        if(this.getStatus().equals("Canceled")) {
            ReservationCanceled reservationCanceled = new ReservationCanceled();
            BeanUtils.copyProperties(this, reservationCanceled);
            reservationCanceled.publishAfterCommit();
        }
        else if(this.getStatus().equals("Confirmed"))
        {
            ReservationConfirmed reservationConfirmed = new ReservationConfirmed();
            BeanUtils.copyProperties(this, reservationConfirmed);
            reservationConfirmed.publishAfterCommit();
        }

    }


    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}