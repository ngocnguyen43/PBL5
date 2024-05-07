package utils.mapper.implement;

import model.Stat;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SoldTicketsMapper implements IMapper<Stat> {
    private final Logger logger = Logger.getLogger(SoldTicketsMapper.class.getName());

    @Override
    public Stat mapRow(ResultSet result) {
        Stat stat = new Stat();
        try {
            stat.setSold(result.getInt("sold"));
            stat.setAvailable(result.getInt("available"));
            return stat;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
