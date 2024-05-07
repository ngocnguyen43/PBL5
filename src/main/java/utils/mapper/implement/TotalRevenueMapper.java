package utils.mapper.implement;

import model.Stat;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TotalRevenueMapper implements IMapper<Stat> {
    private final Logger logger = Logger.getLogger(TotalRevenueMapper.class.getName());

    @Override
    public Stat mapRow(ResultSet result) {
        Stat stat = new Stat();
        try {
            stat.setTotal(result.getBigDecimal("total"));
            stat.setRevenue(result.getBigDecimal("revenue"));
            return stat;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
