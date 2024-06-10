package dto;

import model.Schedule;

import java.util.List;

public class FindAllSchedulesDto {
    List<Schedule> departures;
    List<Schedule> returns;

    public List<Schedule> getDepartures() {
        return departures;
    }

    public void setDepartures(List<Schedule> departures) {
        this.departures = departures;
    }

    public List<Schedule> getReturns() {
        return returns;
    }

    public void setReturns(List<Schedule> returns) {
        this.returns = returns;
    }
}
