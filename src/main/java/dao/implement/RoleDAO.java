package dao.implement;

import dao.interfaces.IRoleDAO;
import model.Role;
import utils.mapper.implement.RoleMapper;

import java.util.List;

public class RoleDAO extends AbstractDAO<Role> implements IRoleDAO {
    @Override
    public Role FindOneByName(String name) {
        String sql = "SELECT * FROM roles WHERE role_name = ?";
        List<Role> roles = query(sql, new RoleMapper(), name);
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public Role FindOneById(String id) {
        String sql = "SELECT * FROM roles WHERE role_id = ?";
        List<Role> roles = query(sql, new RoleMapper(), id);
        return roles.isEmpty() ? null : roles.get(0);
    }

    @Override
    public List<Role> FindAll() {
        String sql = "SELECT * FROM roles";
        return query(sql, new RoleMapper());
    }
}
