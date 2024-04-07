package utils.mapper.implement;

import model.Booking;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingMapper implements IMapper<Booking> {
    Logger logger = Logger.getLogger(BookingMapper.class.getName());
    @Override
    public Booking mapRow(ResultSet result) {
        Booking booking = new Booking();
        try {
            booking.setBookingId(result.getString("booking_id"));
            booking.setTicketId(result.getString("ticket_id"));
            booking.setUserId(result.getString("user_id"));
            booking.setStatus(result.getString("status"));
            return  booking;
       }catch (Exception e){
           logger.log(Level.WARNING,e.getMessage());
       }
        return null;
    }
}
