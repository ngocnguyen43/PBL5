package dao.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import dao.interfaces.IScheduleDAO;
import model.Schedule;
import utils.mapper.implement.ScheduleMapper;

import java.sql.SQLException;
import java.util.List;

public class SchedulesDAO extends AbstractDAO<Schedule> implements IScheduleDAO {
    @Override
    public List<Schedule> FindAll() {
        String sql = "SELECT * FROM schedules";
        return query(sql, new ScheduleMapper(false));
    }

    @Override
    public void CreateOne(Schedule schedule) throws SQLException, JsonProcessingException {
        String sql = "INSERT INTO schedules (schedule_id," +
                "user_id," +
                "trip_code," +
                "departure_id," +
                "arrival_id," +
                "estimated_travel_time," +
                "notes," +
                "photo,created_at," +
                "updated_at,arrival_at," +
                "start_at, train_id) VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        insert(sql, schedule.getScheduleId(), schedule.getUserId(), schedule.getTripCode(), schedule.getDeparturePoint(), schedule.getArrivalPoint(), schedule.getEstimatedTravelTime(), schedule.getNotes(), schedule.getPhoto(), schedule.getCreatedAt(), schedule.getUpdatedAt(), schedule.getArrivalAt(), schedule.getStartAt(), schedule.getTrainId());
    }

    @Override
    public void UpdateStatus(String id, String status) throws SQLException {
        String sql = "UPDATE schedules SET status = ? WHERE schedule_id = ?";
        update(sql, status, id);
    }
}
