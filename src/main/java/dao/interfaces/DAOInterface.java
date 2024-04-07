package dao.interfaces;

import utils.mapper.interfaces.IMapper;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {
    void update(String sql, Object... params);

    void insert(String sql, Object... params) throws SQLException;

    void delete(String sql, Object... params);

    <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters);

}

