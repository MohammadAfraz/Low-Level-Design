import com.demo.bookmyshow.exceptions.SeatNotAvailableException;
import com.demo.bookmyshow.model.*;
import com.demo.bookmyshow.utils.PaymentSettler;

import java.util.Map;

public class BookingManager {
    Storage storage =  Storage.Instance;
    PaymentSettler paymentSettler = PaymentSettler.INSTANCE;
    public Booking doBooking(UserDetails userDetails, Booking booking, Payment payment){
        validateSeatsAvailability(booking);
        booking.setStatus(BookingStatus.IN_PROGRESS);
        if(payment.doPayment(booking.getPrice())){
            markSeatsAsBooked(booking);
            booking.setStatus(BookingStatus.COMPLETED);
            storage.setReferenceNumber(booking.getBookingId(), payment.getReferenceNumber());
        }
        else{
            markSeatsAsAvailable(booking);
            booking.setStatus(BookingStatus.PAYMENT_FAILED);
        }
        //Notify User using userDetails
        return booking;
    }

    public Booking cancelBooking(Booking booking){
        String paymentReferenceNumber = storage.getReferenceNumber(booking.getBookingId());
        paymentSettler.refundPayment(paymentReferenceNumber);
        markSeatsAsAvailable(booking);
        booking.setStatus(BookingStatus.CANCELLED);
        return booking;
    }

    /** Only one thread can enter below method at a time to check availability of seats.
        If available it will block those seats
     */
    private synchronized void validateSeatsAvailability(Booking booking){
        Show show = storage.getShow(booking.getShowId());
        Map<String, Seat> seatsMap = show.getScreen().getSeatsMap();
        for (String seatId : booking.getSeatIds()) {
            if(!SeatStatus.AVAILABLE.equals(seatsMap.get(seatId).getSeatStatus())){
                throw new SeatNotAvailableException("Seat is no longer available! Please try booking other seats.");
            }
        }
        for (String seatId : booking.getSeatIds()) {
            seatsMap.get(seatId).setSeatStatus(SeatStatus.BLOCKED);
        }
    }

    private void markSeatsAsBooked(Booking booking){
        Show show = storage.getShow(booking.getShowId());
        Map<String, Seat> seatsMap = show.getScreen().getSeatsMap();
        for (String seatId : booking.getSeatIds()) {
            seatsMap.get(seatId).setSeatStatus(SeatStatus.BOOKED);
        }
    }

    private void markSeatsAsAvailable(Booking booking){
        Show show = storage.getShow(booking.getShowId());
        Map<String, Seat> seatsMap = show.getScreen().getSeatsMap();
        for (String seatId : booking.getSeatIds()) {
            seatsMap.get(seatId).setSeatStatus(SeatStatus.AVAILABLE);
        }
    }
}
