package dao.implement;

import dao.interfaces.IScheduleRequestDAO;
import model.ScheduleRequest;
import utils.mapper.implement.ScheduleRequestMapper;

import java.sql.SQLException;
import java.util.Arrays;
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
        return query(sql, new ScheduleRequestMapper());
    }

    @Override
    public void UpdateStatus(String requestId, String scheduleId, String status) throws SQLException {
        String scheduleRequestSql = "UPDATE schedule_requests SET status = ? WHERE request_id = ?";
        String scheduleSql = "UPDATE schedules SET status = ? WHERE schedule_id = ?";
        List<Object[]> scheduleRequest = Arrays.asList(new Object[][]{
                {
                        status,
                        requestId,
                }
        });
        List<Object[]> schedule = Arrays.asList(new Object[][]{
                {
                        status,
                        scheduleId,
                }
        });
        bulkCreate(Arrays.asList(scheduleRequestSql, scheduleSql), Arrays.asList(scheduleRequest, schedule));
    }

    @Override
    public ScheduleRequest FindOneById(String id) {
        String sql = " SELECT schedule_requests.*,schedules.* FROM schedule_requests JOIN schedules ON schedule_requests.schedule_id = schedules.schedule_id WHERE schedule_requests.request_id =?";
        List<ScheduleRequest> scheduleRequests = query(sql, new ScheduleRequestMapper(true), id);
        return scheduleRequests.isEmpty() ? null : scheduleRequests.get(0);
    }

}
