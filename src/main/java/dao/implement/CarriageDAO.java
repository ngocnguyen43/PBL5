package dao.implement;

import dao.interfaces.ICarriageDAO;
import model.Carriage;
import utils.mapper.implement.CarriageMapper;

import java.sql.SQLException;
import java.util.List;

public class CarriageDAO extends AbstractDAO<Carriage> implements ICarriageDAO {


    @Override
    public void CreateOne(Carriage model) throws SQLException {
        String sql = "INSERT INTO carriages (`carriage_id`,`carriage_name`,`train_id`,`total_seats`) VALUES (?,?,?,?)";
        insert(sql, model.getId(), model.getName(), model.getTrainId(), model.getTotalSeats());
    }

    @Override
    public List<Carriage> FindAll() {
        String sql = "SELECT * FROM carriages";
        return query(sql, new CarriageMapper());
    }

    @Override
    public void UpdateOne(Carriage carriage) throws SQLException {
        String sql = "UPDATE carriages SET carriage_name = ?,total_seats = ? WHERE carriage_id = ? ";
        update(sql, carriage.getName(), carriage.getTotalSeats(), carriage.getId());
    }

    @Override
    public Carriage FindOne(String id) {
        String sql = "SELECT * FROM carriages WHERE carriage_id = ?";
        List<Carriage> carriages = query(sql, new CarriageMapper(), id);
        return carriages.isEmpty() ? null : carriages.get(0);
    }

    @Override
    public void DeleteOne(String id) {
        String sql = "DELETE FROM carriages WHERE train_id = ?";
        delete(sql, id);
    }

    @Override
    public void BulkCreate(List<Carriage> carriages) throws SQLException {
        String sql = "INSERT INTO carriages (carriage_id, carriage_name, total_seats,train_id) VALUES (?,?,?,?)";
        List<Object[]> objects = carriages.stream().map(element -> new Object[]{
                element.getId(),
                element.getName(),
                element.getTotalSeats(),
                element.getTrainId(),
        }).toList();
        bulkCreate(sql, objects);
    }
}
