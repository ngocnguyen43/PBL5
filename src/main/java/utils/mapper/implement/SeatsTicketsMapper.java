package utils.mapper.implement;

import model.SeatsTickets;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SeatsTicketsMapper implements IMapper<SeatsTickets> {
    private final Logger logger = Logger.getLogger(SeatsTicketsMapper.class.getName());

    @Override
    public SeatsTickets mapRow(ResultSet result) {
        SeatsTickets seatsTickets = new SeatsTickets();
        try {
            seatsTickets.setSeatId(result.getString("seat_id"));
            seatsTickets.setTicketId(result.getString("ticket_id"));
            seatsTickets.setScheduleId(result.getString("schedule_id"));
            seatsTickets.setCarriageId(result.getString("carriage_id"));
            seatsTickets.setStatus(result.getString("status"));
            return seatsTickets;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
