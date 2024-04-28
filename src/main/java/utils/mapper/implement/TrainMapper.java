package utils.mapper.implement;

import model.Train;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainMapper implements IMapper<Train> {

    private final Logger logger = Logger.getLogger(TrainMapper.class.getName());

    @Override
    public Train mapRow(ResultSet result) {
        Train train = new Train();
        try {
            train.setId(result.getString("train_id"));
            train.setName(result.getString("train_name"));
            train.setTotalCarriage(result.getInt("total_carriage"));
            train.setStatus(result.getString("status"));
            return train;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
