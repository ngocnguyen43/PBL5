package utils.mapper.implement;

import model.SeatStatus;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeatStatusMapper implements IMapper<SeatStatus> {

    private final Logger logger = Logger.getLogger(SeatStatusMapper.class.getName());

    @Override
    public SeatStatus mapRow(ResultSet result) {
        SeatStatus seatStatus = new SeatStatus();
        try {
            seatStatus.setSeatNumber(result.getInt("seat_number"));
            seatStatus.setStatus(result.getString("status"));
            return seatStatus;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
