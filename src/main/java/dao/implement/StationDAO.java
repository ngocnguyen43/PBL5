package dao.implement;

import dao.interfaces.IStationDAO;
import model.Station;
import utils.mapper.implement.StationMapper;

import java.sql.SQLException;
import java.util.List;

public class StationDAO extends AbstractDAO<Station> implements IStationDAO {
    @Override
    public List<Station> FindAll() {
        String sql = "SELECT * FROM stations";
        return query(sql, new StationMapper());
    }

    @Override
    public List<Station> FindAllByName(String name) {
        String sql = "SELECT * FROM stations WHERE station_poin like ?";
        return query(sql, new StationMapper(), "%" + name + "%");
    }

    @Override
    public Station FindOneById(String id) {
        String sql = "SELECT * FROM stations WHERE station_id = ?";
        List<Station> stations = query(sql, new StationMapper(), id);
        return stations.isEmpty() ? null : stations.get(0);
    }

    @Override
    public void CreateOne(Station station) throws SQLException {
        String sql = "INSERT INTO stations (station_id,station_poin) VALUES (?,?)";
        insert(sql, station.getStationId(), station.getStationPoint());
    }
}
