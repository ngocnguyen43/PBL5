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
    public List<Schedule> FindAll(String startAt, String arrivalAt, boolean isReturn) {
        String start = 86400000 + Long.parseLong(startAt) + "";

//        String arrival =  86400000 + Long.parseLong(startAt) + "";
        String sql = """
                SELECT *
                FROM pbl5_1.schedules
                WHERE (CAST(start_at AS UNSIGNED) > CAST(? AS UNSIGNED)
                AND CAST(start_at AS UNSIGNED) < CAST(? AS UNSIGNED)) ;""";
        return query(sql, new ScheduleMapper(true), startAt, start);
    }

    @Override
    public List<Schedule> FindAllConflicts(String startAt, String arrivalAt) {
        String sql = """
                SELECT *
                FROM pbl5_1.schedules
                WHERE (CAST(start_at AS UNSIGNED) > CAST(? AS UNSIGNED)
                AND CAST(start_at AS UNSIGNED) < CAST(? AS UNSIGNED)) OR\s
                (CAST(arrival_at AS UNSIGNED) > CAST(? AS UNSIGNED)
                AND CAST(arrival_at AS UNSIGNED) < CAST(? AS UNSIGNED));""";

        return query(sql, new ScheduleMapper(false), startAt, arrivalAt, startAt, arrivalAt);
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
