package dao.implement;

import dao.interfaces.IUserPermissionDAO;
import model.UserPermission;

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
}
