package dao.interfaces;

import model.Role;

import java.util.List;

public interface IRoleDAO extends DAOInterface<Role> {
    Role FindOneByName(String name);

    Role FindOneById(String id);

    List<Role> FindAll();
}
