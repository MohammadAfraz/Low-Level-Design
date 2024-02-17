import com.demo.bookmyshow.exceptions.SeatNotAvailableException;
import com.demo.bookmyshow.model.*;
import com.demo.bookmyshow.utils.PaymentSettler;

public class BookingManager {
    Storage storage =  Storage.Instance;
    PaymentSettler paymentSettler = PaymentSettler.INSTANCE;
    public Booking doBooking(UserDetails userDetails, Booking booking, Payment payment){
        validateSeatsAvailable(booking);
        booking.setStatus(BookingStatus.IN_PROGRESS);
        if(payment.doPayment(booking.getPrice())){
            booking.setStatus(BookingStatus.COMPLETED);
            storage.setReferenceNumber(booking.getBookingId(), payment.getReferenceNumber());
        }
        //Notify User using userDetails
        return booking;
    }

    public Booking cancelBooking(Booking booking){
        String paymentReferenceNumber = storage.getReferenceNumber(booking.getBookingId());
        paymentSettler.refundPayment(paymentReferenceNumber);
        booking.setStatus(BookingStatus.CANCELLED);
        return booking;
    }

    private void validateSeatsAvailable(Booking booking){
        Show show = storage.getShow(booking.getShowId());
        for (String seatId : booking.getSeatIds()) {
            if(!show.getScreen().getSeatsMap().get(seatId).isBooked()){
                throw new SeatNotAvailableException("Seat is no longer available");
            }
        }
    }
}
