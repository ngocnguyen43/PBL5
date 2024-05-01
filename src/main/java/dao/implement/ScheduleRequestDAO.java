package dao.implement;

import dao.interfaces.IScheduleRequestDAO;
import model.ScheduleRequest;
import utils.mapper.implement.ScheduleRequetMapper;

import java.sql.SQLException;
import java.util.List;

public class ScheduleRequestDAO extends AbstractDAO<ScheduleRequest> implements IScheduleRequestDAO {
    @Override
    public void CreateOne(ScheduleRequest request) throws SQLException {
        String sql = "INSERT INTO schedule_requests (request_id,schedule_id,created_at,updated_at,type) VALUES (?,?,?,?,?)";
        insert(sql, request.getRequestId(), request.getScheduleId(), request.getCreatedAt(), request.getUpdatedAt(), request.getType());
    }

    @Override
    public List<ScheduleRequest> FindAll() {
        String sql = "SELECT * FROM schedule_requests ORDER BY created_at DESC";
        return query(sql, new ScheduleRequetMapper());
    }

    @Override
    public void UpdateStatus(String id, String status) throws SQLException {
        String sql = "UPDATE schedule_requests SET status = ? WHERE id = ?";
        update(sql, status, id);
    }

    @Override
    public ScheduleRequest FindOneById(String id) {
        String sql = "SELECT * FROM schedule_requests WHERE request_id = ?";
        List<ScheduleRequest> scheduleRequests = query(sql, new ScheduleRequetMapper());
        return scheduleRequests.isEmpty() ? null : scheduleRequests.get(0);
    }
}
