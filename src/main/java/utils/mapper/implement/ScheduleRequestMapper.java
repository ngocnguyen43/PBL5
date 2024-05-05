package utils.mapper.implement;

import model.Schedule;
import model.ScheduleRequest;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleRequestMapper implements IMapper<ScheduleRequest> {

    private final Logger logger = Logger.getLogger(ScheduleRequestMapper.class.getName());
    private boolean withSchedule;

    public ScheduleRequestMapper(boolean withSchedule) {
        this.withSchedule = withSchedule;
    }

    public ScheduleRequestMapper() {

    }

    @Override
    public ScheduleRequest mapRow(ResultSet result) {
        ScheduleRequest scheduleRequest = new ScheduleRequest();
        try {

            scheduleRequest.setRequestId(result.getString("request_id"));
            scheduleRequest.setScheduleId(result.getString("schedule_id"));
            scheduleRequest.setStatus(result.getString("status"));
            scheduleRequest.setType(result.getString("type"));
            scheduleRequest.setCreatedAt(result.getString("created_at"));
            scheduleRequest.setUpdatedAt(result.getString("updated_at"));
            if (this.withSchedule) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(result.getString("schedule_id"));
                schedule.setUserId(result.getString("user_id"));
                schedule.setTripCode(result.getString("trip_code"));
                schedule.setDeparturePoint(result.getString("departure_id"));
                schedule.setArrivalPoint(result.getString("arrival_id"));
                schedule.setArrivalAt(result.getString("arrival_at"));
                schedule.setStartAt(result.getString("start_at"));
                schedule.setEstimatedTravelTime(result.getFloat("estimated_travel_time"));
                schedule.setNotes(result.getString("notes"));
                schedule.setCreatedAt(result.getString("created_at"));
                schedule.setUpdatedAt(result.getString("updated_at"));
                schedule.setPhoto(result.getString("photo"));
                schedule.setStatus(result.getString("status"));
                schedule.setTrainId(result.getString("train_id"));
                scheduleRequest.setSchedule(schedule);
            }
            return scheduleRequest;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
