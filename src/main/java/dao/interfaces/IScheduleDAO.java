package dao.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Schedule;

import java.sql.SQLException;
import java.util.List;

public interface IScheduleDAO extends DAOInterface<Schedule> {

    List<Schedule> FindAll();

    List<Schedule> FindAll(String startAt, String arrivalAt, String start, String arrival, boolean isReturn);

    List<Schedule> FindAllConflicts(String trainId, String startAt, String arrivalAt);

    void CreateOne(Schedule schedule) throws SQLException, JsonProcessingException;

    void UpdateStatus(String id, String status) throws SQLException;

    Schedule FindOne(String id);
}
