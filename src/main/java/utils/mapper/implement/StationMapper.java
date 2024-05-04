package utils.mapper.implement;

import model.Station;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StationMapper implements IMapper<Station> {
    private final Logger logger = Logger.getLogger(StationMapper.class.getName());

    @Override
    public Station mapRow(ResultSet result) {
        Station station = new Station();
        try {
            station.setStationId(result.getString("station_id"));
            station.setStationPoint(result.getString("station_poin"));
            return station;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }

}
