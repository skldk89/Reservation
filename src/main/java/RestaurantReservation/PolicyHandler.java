package RestaurantReservation;

import RestaurantReservation.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    ReservationRepository reservationRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationCanceled_CancelReservation(@Payload ReservationCanceled reservationCanceled){

        if(reservationCanceled.isMe()){
            System.out.println("##### listener CancelReservation : " + reservationCanceled.toJson());

            reservationRepository.findById(Long.valueOf(reservationCanceled.getReservationId())).ifPresent((Reservation)->{

                Reservation.setOwnerId(reservationCanceled.getOwnerId());
                Reservation.setReservationId(reservationCanceled.getReservationId());
                Reservation.setStatus("Canceled");

                reservationRepository.save(Reservation);
            });
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReservationApporved_ConfirmReservation(@Payload ReservationApporved reservationApporved){

        if(reservationApporved.isMe()){
            System.out.println("##### listener ConfirmReservation : " + reservationApporved.toJson());

            reservationRepository.findById(Long.valueOf(reservationApporved.getReservationId())).ifPresent((Reservation) -> {
                Reservation.setOwnerId(reservationApporved.getOwnerId());
                Reservation.setReservationId(reservationApporved.getReservationId());
                Reservation.setStatus("Confirmed");

                reservationRepository.save(Reservation);
            });
        }
    }

}
