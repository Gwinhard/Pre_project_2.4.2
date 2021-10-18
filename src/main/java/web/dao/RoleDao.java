package web.dao;

import web.model.Role;

import java.util.Set;

public interface RoleDao {
    Role getRoleByName(String roleName);

    Role getRoleById(long id);

    Set<Role> getAllRoles();
}
