package dao.implement;

import dao.interfaces.IStationDAO;
import model.Station;
import utils.mapper.implement.StationMapper;

import java.util.List;

public class StationDAO extends AbstractDAO<Station> implements IStationDAO {
    @Override
    public List<Station> FindAll() {
        String sql = "SELECT * FROM stations";
        return query(sql, new StationMapper());
    }
}
