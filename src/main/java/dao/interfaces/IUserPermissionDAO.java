package dao.interfaces;

import model.UserPermission;

import java.sql.SQLException;
import java.util.List;

public interface IUserPermissionDAO extends DAOInterface<UserPermission> {
    void BulkCreateUserPermissions(List<UserPermission> userPermissions) throws SQLException;

    UserPermission FindOneByUserIdAndRoleName(String userId, String roleName);
}
