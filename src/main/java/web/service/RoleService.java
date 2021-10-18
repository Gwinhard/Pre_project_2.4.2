package web.service;

import web.model.Role;

import java.util.Set;

public interface RoleService {
    Role getRoleByName(String roleName);

    Set<Role> getAllRoles();

    Role getRoleById(long id);
}
