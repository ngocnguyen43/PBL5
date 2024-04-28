package dao.implement;

import dao.interfaces.ITrainDAO;
import model.Train;
import utils.mapper.implement.TrainMapper;

import java.sql.SQLException;
import java.util.List;

public class TrainDAO extends AbstractDAO<Train> implements ITrainDAO {
    @Override
    public void CreateOne(Train model) throws SQLException {
        String sql = "INSERT INTO trains (`train_id`,`train_name`,`total_carriage`) VALUES (?,?,?)";
        insert(sql, model.getId(), model.getName(), model.getTotalCarriage());
    }

    @Override
    public List<Train> FindAll() {
        String sql = "SELECT * FROM trains";
        return query(sql, new TrainMapper());
    }

    @Override
    public void UpdateOne(Train train) throws SQLException {
        String sql = "UPDATE trains SET train_name = ?,total_carriage = ?, status = ? WHERE train_id = ? ";
        update(sql, train.getName(), train.getTotalCarriage(), train.getStatus(), train.getId());
    }

    @Override
    public Train FindOne(String id) {
        String sql = "SELECT * FROM trains WHERE train_id = ?";
        List<Train> trains = query(sql, new TrainMapper(), id);
        return trains.isEmpty() ? null : trains.get(0);
    }

    @Override
    public void DeleteOne(String id) {
        String sql = "DELETE FROM trains WHERE train_id = ?";
        delete(sql, id);
    }
}
