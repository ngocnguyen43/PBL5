package utils.mapper.implement;

import model.Seat;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeatMapper implements IMapper<Seat> {
    private final Logger logger = Logger.getLogger(SeatMapper.class.getName());

    @Override
    public Seat mapRow(ResultSet result) {
        Seat seat = new Seat();
        try {
            seat.setSeatId(result.getString("seat_id"));
            seat.setCarriageId(result.getString("carriage_id"));
            seat.setSeatNumber(result.getInt("seat_number"));
            seat.setPrice(result.getBigDecimal("price"));
            return seat;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
