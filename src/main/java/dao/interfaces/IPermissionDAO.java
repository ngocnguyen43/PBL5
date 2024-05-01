package dao.interfaces;

import model.Permission;

import java.util.List;

public interface IPermissionDAO extends DAOInterface<Permission> {
    List<Permission> FindAllPermissions(String[] userPermissions);
}
