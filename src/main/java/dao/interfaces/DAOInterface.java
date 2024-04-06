package dao.interfaces;

import utils.mapper.interfaces.IMapper;

import java.util.List;

public interface DAOInterface<T> {
    void update(String sql, Object... params);

    void insert(String sql, Object... params);

    void delete(String sql, Object... params);

    <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters);

}

