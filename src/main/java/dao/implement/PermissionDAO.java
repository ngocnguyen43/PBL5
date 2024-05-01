package dao.implement;

import dao.interfaces.IPermissionDAO;
import model.Permission;
import utils.mapper.implement.PermissionMapper;

import java.util.Arrays;
import java.util.List;

public class PermissionDAO extends AbstractDAO<Permission> implements IPermissionDAO {
    @Override
    public List<Permission> FindAllPermissions(String[] userPermissions) {
        String[] sqls = new String[userPermissions.length];
        Arrays.fill(sqls, "SELECT * FROM permissions WHERE permission_name = ?");
        String sql = String.join(" UNION ", sqls);
        return query(sql, new PermissionMapper(), (Object[]) userPermissions);
    }
}
