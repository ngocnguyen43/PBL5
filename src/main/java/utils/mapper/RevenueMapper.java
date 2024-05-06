package utils.mapper;

import model.Revenue;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RevenueMapper implements IMapper<Revenue> {
    private final Logger logger = Logger.getLogger(RevenueMapper.class.getName());

    @Override
    public Revenue mapRow(ResultSet result) {
        Revenue revenue = new Revenue();
        try {
            revenue.setRevenue(result.getFloat("revenue"));
            revenue.setOrderDate(result.getLong("order_date"));
            return revenue;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
