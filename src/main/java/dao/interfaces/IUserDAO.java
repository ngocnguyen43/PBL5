package dao.interfaces;

import model.User;

import java.sql.SQLException;

public interface IUserDAO extends DAOInterface<User> {
//    User FindOneByEmail(String email);
    void CreateOne(User user) throws SQLException;

    User FindOneByUsername(String username,boolean withPassword);
    void DeleteOneById(String userId) throws SQLException;
}
