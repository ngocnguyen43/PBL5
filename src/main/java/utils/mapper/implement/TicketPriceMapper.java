package utils.mapper.implement;

import utils.mapper.interfaces.IMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicketPriceMapper implements IMapper<BigDecimal> {
    private final Logger logger = Logger.getLogger(TicketPriceMapper.class.getName());

    @Override
    public BigDecimal mapRow(ResultSet result) {

        try {
            return result.getBigDecimal("total");
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
