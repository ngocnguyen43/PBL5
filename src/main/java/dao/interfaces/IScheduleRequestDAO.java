package dao.interfaces;

import model.ScheduleRequest;

import java.sql.SQLException;
import java.util.List;

public interface IScheduleRequestDAO extends DAOInterface<ScheduleRequest> {
    void CreateOne(ScheduleRequest request) throws SQLException;
    List<ScheduleRequest> FindAll();
    void UpdateStatus(String id,String status) throws SQLException;
    ScheduleRequest FindOneById(String id);
}
