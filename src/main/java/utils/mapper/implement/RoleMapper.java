package utils.mapper.implement;

import model.Role;
import utils.mapper.interfaces.IMapper;

import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoleMapper implements IMapper<Role> {
    private final Logger logger = Logger.getLogger(Role.class.getName());

    @Override
    public Role mapRow(ResultSet result) {
        Role role = new Role();
        try {
            role.setRoleId(result.getString("role_id"));
            role.setRoleName(result.getString("role_name"));
            return role;
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
        }
        return null;
    }
}
