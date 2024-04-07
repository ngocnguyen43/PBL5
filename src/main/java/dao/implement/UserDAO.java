package dao.implement;

import dao.interfaces.IUserDAO;
import model.User;
import utils.mapper.implement.UserMapper;

import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    public void CreateOne(User user) throws SQLException {
        String sql = "INSERT INTO users (user_id," + "role_id," + "username," + "password)" + " VALUES(?,?,?,?)";
        insert(sql, user.getUserId(), user.getRoleId(), user.getUsername(), user.getPassword());

    }

    @Override
    public User FindOneByUsername(String username,boolean withPassword) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = query(sql, new UserMapper(withPassword), username);
        return users.isEmpty() ? null : users.get(0);
    }
}
