package dao.implement;

import dao.interfaces.IUserPermissionDAO;
import model.UserPermission;
import utils.mapper.implement.UserPermissionMapper;

import java.sql.SQLException;
import java.util.List;

public class UserPermissionDAO extends AbstractDAO<UserPermission> implements IUserPermissionDAO {

    @Override
    public void BulkCreateUserPermissions(List<UserPermission> userPermissions) throws SQLException {
        String sql = "INSERT INTO user_permissions (id, user_id, permission_id) VALUES (?,?,?)";
        List<Object[]> objects = userPermissions.stream().map(element -> new Object[]{
                element.getId(),
                element.getUserId(),
                element.getPermissionId()
        }).toList();
        bulkCreate(sql, objects);
    }

    @Override
    public UserPermission FindOneByUserIdAndRoleName(String userId, String roleName) {
        String sql = "SELECT * FROM user_permissions JOIN permissions ON permissions.permission_id = user_permissions.permission_id WHERE user_permissions.user_id = ? AND permissions.permission_name = ?";
        List<UserPermission> userPermissions = query(sql, new UserPermissionMapper(), userId, roleName);
        return userPermissions.isEmpty() ? null : userPermissions.get(0);
    }
}
