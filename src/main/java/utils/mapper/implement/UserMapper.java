package utils.mapper.implement;

import model.User;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserMapper implements IMapper<User> {
    Logger logger = Logger.getLogger(UserMapper.class.getName());
    private boolean withPassword = false;

    public UserMapper(boolean withPassword) {
        this.withPassword = withPassword;
    }

    public UserMapper() {
    }

    @Override
    public User mapRow(ResultSet result) {
        User user = new User();
        try {
            user.setUserId(result.getString("user_id"));
            user.setUsername(result.getString("username"));
            if (this.withPassword) {
                user.setPassword(result.getString("password"));
            }
            user.setEmail(result.getString("email"));
            user.setPhoto(result.getString("photo"));
            user.setFullName(result.getString("full_name"));
            user.setPhoneNumber(result.getString("phone_number"));
            user.setCreatedAt(result.getString("created_at"));
            user.setUpdatedAt(result.getString("updated_at"));
            user.setDeletedAt(result.getString("deleted_at"));
            return user;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
