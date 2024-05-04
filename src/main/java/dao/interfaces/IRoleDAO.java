package dao.interfaces;

import model.Role;

public interface IRoleDAO extends DAOInterface<Role> {
    Role FindOneByName(String name);
    Role FindOneById(String id);
}
