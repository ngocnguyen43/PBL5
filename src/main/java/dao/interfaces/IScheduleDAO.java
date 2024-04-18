package dao.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Schedules;

import java.sql.SQLException;
import java.util.List;

public interface IScheduleDAO extends DAOInterface<Schedules> {

    List<Schedules> FindAll();
    void CreateOne(Schedules schedules) throws SQLException, JsonProcessingException;
}
