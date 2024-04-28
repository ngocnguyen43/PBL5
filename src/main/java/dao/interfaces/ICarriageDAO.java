package dao.interfaces;

import model.Carriage;
import model.Train;

import java.sql.SQLException;
import java.util.List;

public interface ICarriageDAO extends DAOInterface<Carriage> {
    void CreateOne(Carriage model) throws SQLException;

    List<Carriage> FindAll();

    void UpdateOne(Carriage carriage) throws SQLException;

    Carriage FindOne(String id);

    void DeleteOne(String id);
}
