package main.dao.interfaces;

import main.utils.mapper.interfaces.IMapper;

import java.util.ArrayList;
import java.util.List;

public interface DAOInterface<T> {
    ArrayList<T> selectAll();

     T selectById(T t);

    int insert(T t);

    int insertAll(ArrayList<T> arr);

    int delete(T t);

    int deleteAll(ArrayList<T> arr);

    int update(T t);
    void update(String sql, Object... params);

    void insert(String sql, Object... params);

    void delete(String sql, Object... params);

    <T> List<T> query(String sql, IMapper<T> mapper, Object... parameters);

}

