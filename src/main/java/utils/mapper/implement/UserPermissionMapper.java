package utils.mapper.implement;

import model.UserPermission;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPermissionMapper implements IMapper<UserPermission> {
    private final Logger logger = Logger.getLogger(UserPermissionMapper.class.getName());

    @Override
    public UserPermission mapRow(ResultSet result) {
        UserPermission userPermission = new UserPermission();
        try {
            userPermission.setId(result.getString("id"));
            userPermission.setPermissionId(result.getString("permission_id"));
            userPermission.setUserId(result.getString("user_id"));
            userPermission.setStatus(result.getString("status"));
            return userPermission;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
