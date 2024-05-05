package dao.interfaces;

import utils.mapper.interfaces.IMapper;

import java.sql.SQLException;
import java.util.List;

public interface DAOInterface<T> {
    void update(String sql, Object... params) throws SQLException;

    void insert(String sql, Object... params) throws SQLException;

    void bulkCreate(String sql, List<Object[]> params) throws SQLException;

    void bulkCreate(List<String> sql, List<List<Object[]>> params) throws SQLException;

    void delete(String sql, Object... params);

    <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters);

    <T> List<T> query(String sql, IMapper<T> mapper, List<Object[]> params);
}

