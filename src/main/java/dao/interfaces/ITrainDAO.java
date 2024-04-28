package dao.interfaces;

import model.Train;

import java.sql.SQLException;
import java.util.List;

public interface ITrainDAO extends DAOInterface<Train> {
    void CreateOne(Train model) throws SQLException;

    List<Train> FindAll();

    void UpdateOne(Train train) throws SQLException;

    Train FindOne(String id);

    void DeleteOne(String id);
}
