package dao.implement;

import dao.interfaces.IScheduleRequestDAO;
import model.ScheduleRequest;
import utils.mapper.implement.ScheduleRequetMapper;

import java.sql.SQLException;
import java.util.List;

public class ScheduleRequestDAO extends AbstractDAO<ScheduleRequest> implements IScheduleRequestDAO {
    @Override
    public void CreateOne(ScheduleRequest request) throws SQLException {
        String sql = "INSERT INTO schedule_request (request_id,schedule_id,created_at,updated_at,type) VALUES (?,?,?,?,?)";
        insert(sql, request.getRequestId(), request.getScheduleId(), request.getCreatedAt(), request.getUpdatedAt(),request.getType());
    }

    @Override
    public List<ScheduleRequest> FindAll() {
        String sql = "SELECT * FROM schedule_request ORDER BY created_at DESC";
        return query(sql, new ScheduleRequetMapper());
    }
}
