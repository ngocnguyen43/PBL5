package utils.mapper.implement;

import model.Carriage;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarriageMapper implements IMapper<Carriage> {
    private final Logger logger = Logger.getLogger(CarriageMapper.class.getName());

    @Override
    public Carriage mapRow(ResultSet result) {
        Carriage carriage = new Carriage();
        try {
            carriage.setId(result.getString("carriage_id"));
            carriage.setName(result.getString("carriage_name"));
            carriage.setTrainId(result.getString("train_id"));
            carriage.setTotalSeats(result.getInt("total_seats"));
            return carriage;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
