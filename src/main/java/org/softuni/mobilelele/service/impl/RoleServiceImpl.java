package org.softuni.mobilelele.service.impl;

import org.softuni.mobilelele.model.entity.Role;
import org.softuni.mobilelele.model.enums.UserRoleEnum;
import org.softuni.mobilelele.repository.RoleRepository;
import org.softuni.mobilelele.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findRoleByName(String roleName) {
        return this.roleRepository.findByRole(UserRoleEnum.USER);
    }
}
