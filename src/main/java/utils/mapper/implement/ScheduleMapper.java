package utils.mapper.implement;

import model.Schedule;
import model.Train;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScheduleMapper implements IMapper<Schedule> {
    private final Logger logger = Logger.getLogger(Schedule.class.getName());
    private final Boolean shouldShowDate;
    private final Boolean shouldIncludeTrain;

    public ScheduleMapper(Boolean shouldShowDate, Boolean shouldIncludeTrain) {
        this.shouldShowDate = shouldShowDate;
        this.shouldIncludeTrain = shouldIncludeTrain;
    }

    @Override
    public Schedule mapRow(ResultSet result) {
        Schedule schedule = new Schedule();
        try {
            schedule.setScheduleId(result.getString("schedule_id"));
            schedule.setUserId(result.getString("user_id"));
            schedule.setTripCode(result.getString("trip_code"));
            schedule.setDeparturePoint(result.getString("departure_id"));
            schedule.setArrivalPoint(result.getString("arrival_id"));
            schedule.setArrivalAt(result.getString("arrival_at"));
            schedule.setStartAt(result.getString("start_at"));
            schedule.setEstimatedTravelTime(result.getFloat("estimated_travel_time"));
            schedule.setNotes(result.getString("notes"));
            if (shouldShowDate) {
                schedule.setCreatedAt(result.getString("created_at"));
                schedule.setUpdatedAt(result.getString("updated_at"));
            } else {
                schedule.setCreatedAt(null);
                schedule.setUpdatedAt(null);
            }
            if (shouldIncludeTrain) {
                Train train = new Train();
                train.setName(result.getString("train_name"));
                schedule.setTrain(train);
            }
            schedule.setPhoto(result.getString("photo"));
            schedule.setStatus(result.getString("status"));
            schedule.setTrainId(result.getString("train_id"));
            return schedule;
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
