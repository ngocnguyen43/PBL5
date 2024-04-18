package dao.implement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.interfaces.IScheduleDAO;
import model.Schedules;
import utils.mapper.implement.ScheduleMapper;

import java.sql.SQLException;
import java.util.List;

public class SchedulesDAO extends AbstractDAO<Schedules> implements IScheduleDAO {
    @Override
    public List<Schedules> FindAll() {
        String sql = "SELECT * FROM schedules";
        return query(sql, new ScheduleMapper(false));
    }

    @Override
    public void CreateOne(Schedules schedules) throws SQLException, JsonProcessingException {
        String sql = "INSERT INTO schedules (schedule_id," +
                "provider_id," +
                "trip_code," +
                "departure_id," +
                "arrival_id," +
                "estimated_travel_time," +
                "seat_capacity," +
                "seat_price,notes," +
                "photo,created_at," +
                "updated_at,arrival_at," +
                "start_at) VALUES  (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(schedules));

        insert(sql, schedules.getScheduleId(), schedules.getProviderId(), schedules.getTripCode(), schedules.getDeparturePoint(), schedules.getArrivalPoint(), schedules.getEstimatedTravelTime(), schedules.getSeatCapacity(), schedules.getSeatPrice(), schedules.getNotes(), schedules.getPhoto(), schedules.getCreatedAt(), schedules.getUpdatedAt(), schedules.getArrivalAt(), schedules.getStartAt());
    }
}
