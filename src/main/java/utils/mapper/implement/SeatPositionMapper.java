package utils.mapper.implement;

import model.SeatPositions;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeatPositionMapper implements IMapper<SeatPositions> {
    private final Logger logger = Logger.getLogger(SeatPositionMapper.class.getName());

    @Override
    public SeatPositions mapRow(ResultSet result) {
        SeatPositions seatPositions = new SeatPositions();
        try {
            seatPositions.setSeatId(result.getString("seat_id"));
            seatPositions.setScheduleId(result.getString("schedule_id"));
            seatPositions.setSeatNumber(result.getInt("seat_number"));
            seatPositions.setPrice(result.getBigDecimal("price"));
            seatPositions.setStatus(result.getString("status"));
            return seatPositions;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
