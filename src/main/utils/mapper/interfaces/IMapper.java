package main.utils.mapper.interfaces;

import java.sql.ResultSet;

public interface IMapper<T> {
    T mapRow(ResultSet result);
}
