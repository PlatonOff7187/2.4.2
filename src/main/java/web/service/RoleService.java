package web.service;

import web.model.Role;

import java.util.List;

public interface RoleService {

    Role getRoleByName(String userName);
    void saveRole(Role role);
    List<Role> getRoles();
    public Role getRoleById(int id);
}
