package org.softuni.mobilelele.service;

import org.softuni.mobilelele.model.entity.Role;

public interface RoleService {
    Role findRoleByName(String roleName);
}
