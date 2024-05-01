package utils.mapper.implement;

import model.Permission;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PermissionMapper implements IMapper<Permission> {
    private final Logger logger = Logger.getLogger(PermissionMapper.class.getName());


    @Override
    public Permission mapRow(ResultSet result) {
        Permission permission = new Permission();
        try {
            permission.setPermissionId(result.getString("permission_id"));

            permission.setPermissionName(result.getString("permission_name"));
            return permission;
        } catch (Exception e) {
            this.logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
