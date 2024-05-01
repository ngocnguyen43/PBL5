package dao.interfaces;

import model.Seat;

import java.sql.SQLException;
import java.util.List;

public interface ISeatDAO extends DAOInterface<Seat> {
    void BulkCreate(List<Seat> seats) throws SQLException;

    List<Seat> FindAllByScheduleId(String id, String status);
}
