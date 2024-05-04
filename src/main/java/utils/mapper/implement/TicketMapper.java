package utils.mapper.implement;

import model.Ticket;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketMapper implements IMapper<Ticket> {
    private final Logger logger = Logger.getLogger(SeatsTicketsMapper.class.getName());

    @Override
    public Ticket mapRow(ResultSet result) {
        Ticket ticket = new Ticket();
        try {
            ticket.setTicketId(result.getString("seat_id"));
            ticket.setOrderId(result.getString("order_id"));
            ticket.setPhoto(result.getString("photo"));
            ticket.setExtraFee(result.getBigDecimal("extra_fee"));
            ticket.setPrice(result.getBigDecimal("price"));
            ticket.setTotalPrice(result.getBigDecimal("total_price"));
            return ticket;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
