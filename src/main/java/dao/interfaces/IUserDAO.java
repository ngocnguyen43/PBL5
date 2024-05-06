package dao.interfaces;

import model.User;

import java.sql.SQLException;

public interface IUserDAO extends DAOInterface<User> {
    //    User FindOneByEmail(String email);
    void CreateOne(User user) throws SQLException;

    User FindOneByUsername(String username, boolean withPassword);

    User FindOneByUserId(String userId, boolean withPassword);

    void DeleteOneById(String userId) throws SQLException;

    User FindOneByUsernameOrEmail(String username, String email, boolean withPassword);

    String FindMyRole(String userId);

    void UpdateOne(User user) throws SQLException;

}
