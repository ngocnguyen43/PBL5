package dao.implement;

import dao.interfaces.IUserDAO;
import model.User;
import utils.mapper.implement.UserMapper;

import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    public void CreateOne(User user) throws SQLException {
        String sql = "INSERT INTO users (user_id," + "email," + "full_name," + "phone_number," + "created_at," + "updated_at," + "deleted_at," + "username," + "password)" + " VALUES(?,?,?,?,?,?,?,?,?)";
        insert(sql, user.getUserId(), user.getEmail(), user.getFullName(), user.getPhoneNumber(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt(), user.getUsername(), user.getPassword());

    }

    @Override
    public User FindOneByUsername(String username, boolean withPassword) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = query(sql, new UserMapper(withPassword), username);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void DeleteOneById(String userId) throws SQLException {
        String sql = "DELETE from users WHERE user_id =?";
        delete(sql, userId);
    }

    @Override
    public User FindOneByUsernameOrEmail(String username, String email, boolean withPassword) {
        String sql = "SELECT * FROM users WHERE username = ? OR email = ?";
        List<User> users = query(sql, new UserMapper(withPassword), username, email);
        return users.isEmpty() ? null : users.get(0);
    }

}
