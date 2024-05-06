package dao.implement;

import dao.interfaces.IUserDAO;
import model.User;
import utils.mapper.implement.RoleNameMapper;
import utils.mapper.implement.UserMapper;

import java.sql.SQLException;
import java.util.List;

public class UserDAO extends AbstractDAO<User> implements IUserDAO {

    @Override
    public void CreateOne(User user) throws SQLException {
        String sql = "INSERT INTO users (user_id," + "email," + "full_name," + "phone_number," + "created_at," + "updated_at," + "deleted_at," + "username," + "password," + "role_id )" + " VALUES(?,?,?,?,?,?,?,?,?,?)";
        insert(sql, user.getUserId(), user.getEmail(), user.getFullName(), user.getPhoneNumber(), user.getCreatedAt(), user.getUpdatedAt(), user.getDeletedAt(), user.getUsername(), user.getPassword(), user.getRoleId());

    }

    @Override
    public User FindOneByUsername(String username, boolean withPassword) {
        String sql = "SELECT * FROM users WHERE username = ?";
        List<User> users = query(sql, new UserMapper(withPassword), username);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User FindOneByUserId(String userId, boolean withPassword) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        List<User> users = query(sql, new UserMapper(withPassword), userId);
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

    @Override
    public String FindMyRole(String userId) {
        String sql = "SELECT roles.role_name from roles INNER JOIN users ON users.role_id = roles.role_id WHERE users.user_id = ?";
        List<String> roles = query(sql, new RoleNameMapper(), userId);
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public void UpdateOne(User user) throws SQLException {
        String sql = "UPDATE users SET full_name = ? ,phone_number = ?,email = ?  WHERE user_id = ?";
        update(sql, user.getFullName(), user.getPhoneNumber(), user.getEmail(), user.getUserId());
    }

}
